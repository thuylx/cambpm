package vn.tki.erp.cambpm.query;

import vn.tki.erp.cambpm.entity.BpmUserOperationLogEntry;

import java.util.Date;

public class BpmUserOperationLogQuery extends BpmQueryImpl<BpmUserOperationLogQuery, BpmUserOperationLogEntry> implements BpmQuery {
    public BpmUserOperationLogQuery entityType(String entityType) {
        addString("entityType", entityType);
        return this;
    }

    public BpmUserOperationLogQuery entityTypeIn(String... entityTypes) {
        addStringArray("entityTypeIn", entityTypes);
        return this;
    }

    public BpmUserOperationLogQuery operationType(String operationType) {
        addString("operationType", operationType);
        return this;
    }

    public BpmUserOperationLogQuery deploymentId(String deploymentId) {
        addString("deploymentId", deploymentId);
        return this;
    }

    public BpmUserOperationLogQuery processDefinitionId(String processDefinitionId) {
        addString("processDefinitionId", processDefinitionId);
        return this;
    }

    public BpmUserOperationLogQuery processDefinitionKey(String processDefinitionKey) {
        addString("processDefinitionKey", processDefinitionKey);
        return this;
    }

    public BpmUserOperationLogQuery processInstanceId(String processInstanceId) {
        addString("processInstanceId", processInstanceId);
        return this;
    }

    public BpmUserOperationLogQuery executionId(String executionId) {
        addString("executionId", executionId);
        return this;
    }

    public BpmUserOperationLogQuery caseDefinitionId(String caseDefinitionId) {
        addString("caseDefinitionId", caseDefinitionId);
        return this;
    }

    public BpmUserOperationLogQuery caseInstanceId(String caseInstanceId) {
        addString("caseInstanceId", caseInstanceId);
        return this;
    }

    public BpmUserOperationLogQuery caseExecutionId(String caseExecutionId) {
        addString("caseExecutionId", caseExecutionId);
        return this;
    }

    public BpmUserOperationLogQuery taskId(String taskId) {
        addString("taskId", taskId);
        return this;
    }

    public BpmUserOperationLogQuery jobId(String jobId) {
        addString("jobId", jobId);
        return this;
    }

    public BpmUserOperationLogQuery jobDefinitionId(String jobDefinitionId) {
        addString("jobDefinitionId", jobDefinitionId);
        return this;
    }

    public BpmUserOperationLogQuery batchId(String batchId) {
        addString("batchId", batchId);
        return this;
    }

    public BpmUserOperationLogQuery userId(String userId) {
        addString("userId", userId);
        return this;
    }

    public BpmUserOperationLogQuery operationId(String operationId) {
        addString("operationId", operationId);
        return this;
    }

    public BpmUserOperationLogQuery property(String property) {
        addString("property", property);
        return this;
    }

    public BpmUserOperationLogQuery afterTimestamp(Date after) {
        addDate("afterTimestamp", after);
        return this;
    }

    public BpmUserOperationLogQuery beforeTimestamp(Date before) {
        addDate("beforeTimestamp", before);
        return this;
    }

    public BpmUserOperationLogQuery orderByTimestamp() {
        add("orderByTimestamp");
        return this;
    }
}
