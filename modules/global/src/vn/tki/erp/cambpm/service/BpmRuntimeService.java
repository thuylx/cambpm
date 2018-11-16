package vn.tki.erp.cambpm.service;


import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.exception.BpmProcessEngineException;

import java.util.Map;
import java.util.UUID;

public interface BpmRuntimeService {
    String NAME = "cambpm_BpmRuntimeService";

    /**
     * Permission required: Create BpmProcessInstance
     * @param processDefinitionKey  process definition key to start
     * @param processVariables      contain process variables tos tart
     * @return process instance id
     * @exception BpmAuthorizationException if not authorized
     * @exception BpmProcessEngineException if start fail
     */
    UUID startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> processVariables);

    /**
     * Permission required: Create BpmProcessInstance
     * @param processDefinitionKey  process definition key to start in camunda engine
     * @param entityKey     String to represent EntityLoadInfo object, created by EntityLoadInfo.toString().
     *                              See cuba javadoc for more detail.
     * @param processVariables      extra camunda process variable to start with
     * @return process instance id
     * @exception BpmAuthorizationException if not authorized
     * @exception BpmProcessEngineException if start fail
     */
    UUID startProcessInstanceByKey(String processDefinitionKey, String entityKey, Map<String, Object> processVariables);

}