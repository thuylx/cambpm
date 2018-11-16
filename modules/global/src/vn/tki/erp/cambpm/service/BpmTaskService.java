package vn.tki.erp.cambpm.service;

import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.exception.BpmProcessEngineException;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface BpmTaskService {
    String NAME = "cambpm_BpmTaskService";

    /**
     * Permission required: DELETE BpmTask
     * @exception BpmAuthorizationException if not authorized
     * @exception BpmProcessEngineException if fail
     */
    void completeTask(UUID taskId);

    /**
     * Permission required: DELETE BpmTask
     * @exception BpmAuthorizationException if not authorized
     * @exception BpmProcessEngineException if fail
     */
    void completeTask(UUID taskId, Map<String, Object> processVariables);

    /**
     * Permission required: Read BpmTask
     * @exception BpmAuthorizationException if not authorized
     * @return Value of task variable
     */
    Object getTaskVariable(UUID taskId, String variableName);

    /**
     * Permission required: MODIFY task owner
     * @param taskId    task id to claim
     * @param assignee  Login name of the user who will be assigned given task id
     * @exception BpmAuthorizationException if not authorized
     * @exception BpmProcessEngineException if fail
     */
    void claimTask(UUID taskId, String assignee);

    /**
     * Permission required: MODIFY Task assignee
     * @exception BpmAuthorizationException if not authorized
     * @exception BpmProcessEngineException if fail
     */
    void unClaimTask(UUID taskId);

    /**
     * Permission required: Read BpmTask
     * @return True if:
     *              - userLoginName is the same with task owner,
     *              - or userRoles and task candidateGroups have common elements.
     *              - user has permission of MODIFY BpmTask.candidateRoles
     *         False otherwise
     * @exception BpmAuthorizationException if not authorized
     * @exception BpmProcessEngineException if fail
     */
    boolean checkClaimable(String userLoginName, Collection<String> userRoles, UUID taskId);

    /**
     * Permission required: Modify BpmTask assignee
     * @exception BpmAuthorizationException if not authorized
     * @exception BpmProcessEngineException if fail
     */
    void delegateTask(UUID taskId, String loginName);

    /**
     * Permission required: READ BpmTask entity
     * @return Candidate groups of the task
     * @exception BpmAuthorizationException if not authorized
     * @exception BpmProcessEngineException if fail
     */
    Collection<String> getCandidateGroups(UUID taskId);
}