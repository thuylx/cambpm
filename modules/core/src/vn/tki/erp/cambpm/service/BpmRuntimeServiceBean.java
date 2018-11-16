package vn.tki.erp.cambpm.service;

import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.security.entity.EntityOp;
import org.camunda.bpm.engine.ProcessEngineException;
import org.springframework.stereotype.Service;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.entity.BpmProcessInstance;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.exception.BpmProcessEngineException;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

@Service(BpmRuntimeService.NAME)
public class BpmRuntimeServiceBean implements BpmRuntimeService {
    @Inject
    private Security security;
    @Inject
    private CamPlatform camPlatform;

    @Override
    public UUID startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> processVariables) {
        if (!security.isEntityOpPermitted(BpmProcessInstance.class, EntityOp.CREATE))
            throw new BpmAuthorizationException("Start process", "CREATE BpmProcessInstance");

        try {
            return UUID.fromString(camPlatform.getProcessEngine().getRuntimeService()
                    .startProcessInstanceByKey(processDefinitionKey, processVariables)
                    .getId());
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Start process failed", e);
        }

    }

    @Override
    public UUID startProcessInstanceByKey(String processDefinitionKey, String entityKey, Map<String, Object> processVariables) {
        if (!security.isEntityOpPermitted(BpmProcessInstance.class, EntityOp.CREATE))
            throw new BpmAuthorizationException("Start process", "CREATE BpmProcessInstance");
        try {
            return UUID.fromString(camPlatform.getProcessEngine().getRuntimeService()
                    .startProcessInstanceByKey(processDefinitionKey, entityKey, processVariables)
                    .getId());
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Start process failed", e);
        }
    }

}