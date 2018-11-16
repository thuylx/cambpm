package vn.tki.erp.cambpm.service;

import com.google.common.base.Strings;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.security.entity.EntityAttrAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.IdentityLinkType;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.exception.BpmProcessEngineException;

import javax.inject.Inject;
import java.util.*;

@Service(BpmTaskService.NAME)
public class BpmTaskServiceBean implements BpmTaskService {
    @Inject
    private Security security;
    @Inject
    private CamPlatform camPlatform;

    @Override
    public void completeTask(UUID taskId) {
        if (!security.isEntityOpPermitted(BpmTask.class, EntityOp.DELETE))
            throw new BpmAuthorizationException("Complete task", "DELETE BpmTask");

        try {
            camPlatform.getProcessEngine().getTaskService().complete(taskId.toString());
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Complete task failed.", e);
        }
    }

    @Override
    public void completeTask(UUID taskId, Map<String, Object> processVariables) {
        if (!security.isEntityOpPermitted(BpmTask.class, EntityOp.DELETE))
            throw new BpmAuthorizationException("Complete task", "DELETE BpmTask");
        try {
            camPlatform.getProcessEngine().getTaskService().complete(taskId.toString(), processVariables);
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Complete task failed.", e);
        }
    }

    @Override
    public Object getTaskVariable(UUID taskId, String variableName) {
        if (!security.isEntityOpPermitted(BpmTask.class, EntityOp.READ))
            throw new BpmAuthorizationException("Get task variable", "READ BpmTask");

        try {
            return camPlatform.getProcessEngine().getTaskService().getVariable(taskId.toString(), variableName);
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Cannot get process variable", e);
        }
    }

    @Override
    public void claimTask(UUID taskId, String assignee) {
        if (!security.isEntityAttrPermitted(BpmTask.class, "owner", EntityAttrAccess.MODIFY))
            throw new BpmAuthorizationException("Claim task", "MODIFY BpmTask owner");

        try {
            camPlatform.getProcessEngine().getTaskService().setOwner(taskId.toString(), assignee);
            camPlatform.getProcessEngine().getTaskService().claim(taskId.toString(), assignee);
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Claim task failed.", e);
        }
    }

    @Override
    public void unClaimTask(UUID taskId) {
        if (!security.isEntityAttrPermitted(BpmTask.class, "assignee", EntityAttrAccess.MODIFY))
            throw new BpmAuthorizationException("Claim task", "MODIFY BpmTask assignee");

        try {
            camPlatform.getProcessEngine().getTaskService().setAssignee(taskId.toString(), null);
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Unclaim task failed.", e);
        }
    }

    @Override
    public boolean checkClaimable(String userLoginName, Collection<String> userRoles, UUID taskId) {
        if (!security.isEntityOpPermitted(BpmTask.class, EntityOp.READ)) {
            throw new BpmAuthorizationException("List task", "READ BpmTask entity");
        }

        try {

            if (security.isEntityAttrPermitted(BpmTask.class, "candidateRoles", EntityAttrAccess.MODIFY)) {
                return true;
            }

            //Check if task owner
            Task task = camPlatform.getProcessEngine().getTaskService().createTaskQuery()
                    .initializeFormKeys()
                    .taskId(taskId.toString())
                    .singleResult();
            if (!Strings.isNullOrEmpty(task.getOwner()) && userLoginName.equals(task.getOwner())) {
                return true;
            }

            //Check if user roles is have common elements with task candidate group
            return checkTaskClaimable(taskId, userRoles);
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Check claimable failed.", e);
        }
    }

    private Boolean checkTaskClaimable(UUID taskId, Collection<String> userRoles) {
        try {
            //Check if user roles is have common elements with task candidate group
            List<IdentityLink> identityLinks = camPlatform.getProcessEngine().getTaskService().getIdentityLinksForTask(taskId.toString());

            for (IdentityLink identityLink : identityLinks) {
                String type = identityLink.getType();
              /* type corresponds to the constants defined in IdentityLinkType.
              "candidate" identifies a candidate relation */

                String groupId = identityLink.getGroupId();

                if (IdentityLinkType.CANDIDATE.equals(type) && groupId != null) {
                    // we have found a candidate group
                    String candidateRole = identityLink.getGroupId();
                    if (userRoles.contains(candidateRole))
                        return true;
                }
            }
            return false;
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Complete task failed.", e);
        }
    }

    @Override
    public void delegateTask(UUID taskId, String loginName) {
        if (!security.isEntityAttrPermitted(BpmTask.class, "assignee", EntityAttrAccess.MODIFY))
            throw new BpmAuthorizationException("Delegate task", "MODIFY BpmTask assignee");

        try {
            camPlatform.getProcessEngine().getTaskService().delegateTask(taskId.toString(), loginName);
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Delegate task failed.", e);
        }
    }

    @Override
    public Collection<String> getCandidateGroups(UUID taskId) {
        if (!security.isEntityOpPermitted(BpmTask.class, EntityOp.READ)) {
            throw new BpmAuthorizationException("List task", "READ BpmTask entity");
        }

        try {

            Collection<String> roles = new HashSet<>();
            List<IdentityLink> identityLinks = camPlatform.getProcessEngine().getTaskService().getIdentityLinksForTask(taskId.toString());

            for (IdentityLink identityLink : identityLinks) {
                String type = identityLink.getType();
              /* type corresponds to the constants defined in IdentityLinkType.
              "candidate" identifies a candidate relation */

                String groupId = identityLink.getGroupId();

                if (IdentityLinkType.CANDIDATE.equals(type) && groupId != null) {
                    // we have found a candidate group
                    roles.add(identityLink.getGroupId());
                }
            }
            return roles;
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Get candidate group failed.", e);
        }
    }
}