package vn.tki.erp.cambpm.query;

import vn.tki.erp.cambpm.entity.BpmProcessInstance;

import java.util.Set;

public class BpmProcessInstanceQuery extends BpmQueryImpl<BpmProcessInstanceQuery, BpmProcessInstance> implements BpmQuery {
    public BpmProcessInstanceQuery processInstanceId(String processInstanceId) {
        addString("processInstanceId", processInstanceId);
        return this;
    }

    public BpmProcessInstanceQuery processInstanceIds(Set<String> processInstanceIds) {
        addSet("processInstanceIds", processInstanceIds);
        return this;
    }

    public BpmProcessInstanceQuery processInstanceBusinessKey(String processInstanceBusinessKey) {
        addString("processInstanceBusinessKey", processInstanceBusinessKey);
        return this;
    }

    public BpmProcessInstanceQuery processInstanceBusinessKey(String processInstanceBusinessKey, String processDefinitionKey) {
        addStringString("processInstanceBusinessKey", processInstanceBusinessKey, processDefinitionKey);
        return this;
    }

    public BpmProcessInstanceQuery processInstanceBusinessKeyLike(String processInstanceBusinessKeyLike) {
        addString("processInstanceBusinessKeyLike", processInstanceBusinessKeyLike);
        return this;
    }

    public BpmProcessInstanceQuery processDefinitionKey(String processDefinitionKey) {
        addString("processDefinitionKey", processDefinitionKey);
        return this;
    }

    public BpmProcessInstanceQuery processDefinitionId(String processDefinitionId) {
        addString("processDefinitionId", processDefinitionId);
        return this;
    }

    public BpmProcessInstanceQuery deploymentId(String deploymentId) {
        addString("deploymentId", deploymentId);
        return this;
    }

    public BpmProcessInstanceQuery superProcessInstanceId(String superProcessInstanceId) {
        addString("superProcessInstanceId", superProcessInstanceId);
        return this;
    }

    public BpmProcessInstanceQuery subProcessInstanceId(String subProcessInstanceId) {
        addString("subProcessInstanceId", subProcessInstanceId);
        return this;
    }

    public BpmProcessInstanceQuery caseInstanceId(String caseInstanceId) {
        addString("caseInstanceId", caseInstanceId);
        return this;
    }

    public BpmProcessInstanceQuery superCaseInstanceId(String superCaseInstanceId) {
        addString("superCaseInstanceId", superCaseInstanceId);
        return this;
    }

    public BpmProcessInstanceQuery subCaseInstanceId(String subCaseInstanceId) {
        addString("subCaseInstanceId", subCaseInstanceId);
        return this;
    }

    public BpmProcessInstanceQuery variableValueEquals(String name, Object value) {
        addStringObject("variableValueEquals", name, value);
        return this;
    }

    public BpmProcessInstanceQuery variableValueNotEquals(String name, Object value) {
        addStringObject("variableValueNotEquals", name, value);
        return this;
    }

    public BpmProcessInstanceQuery variableValueGreaterThan(String name, Object value) {
        addStringObject("variableValueGreaterThan", name, value);
        return this;
    }

    public BpmProcessInstanceQuery variableValueGreaterThanOrEqual(String name, Object value) {
        addStringObject("variableValueGreaterThanOrEqual", name, value);
        return this;
    }

    public BpmProcessInstanceQuery variableValueLessThan(String name, Object value) {
        addStringObject("variableValueLessThan", name, value);
        return this;
    }

    public BpmProcessInstanceQuery variableValueLessThanOrEqual(String name, Object value) {
        addStringObject("variableValueLessThanOrEqual", name, value);
        return this;
    }

    public BpmProcessInstanceQuery variableValueLike(String name, String value) {
        addStringString("variableValueLike", name, value);
        return this;
    }

    public BpmProcessInstanceQuery suspended() {
        add("suspended");
        return this;
    }

    public BpmProcessInstanceQuery active() {
        add("active");
        return this;
    }

    public BpmProcessInstanceQuery incidentType(String incidentType) {
        addString("incidentType", incidentType);
        return this;
    }

    public BpmProcessInstanceQuery incidentId(String incidentId) {
        addString("incidentId", incidentId);
        return this;
    }

    public BpmProcessInstanceQuery incidentMessage(String incidentMessage) {
        addString("incidentMessage", incidentMessage);
        return this;
    }

    public BpmProcessInstanceQuery incidentMessageLike(String incidentMessageLike) {
        addString("incidentMessageLike", incidentMessageLike);
        return this;
    }

    public BpmProcessInstanceQuery tenantIdIn(String... tenantIds) {
        addStringArray("tenantIdIn", tenantIds);
        return this;
    }

    public BpmProcessInstanceQuery withoutTenantId() {
        add("withoutTenantId");
        return this;
    }

    public BpmProcessInstanceQuery activityIdIn(String... activityIds) {
        addStringArray("activityIdIn", activityIds);
        return this;
    }

    public BpmProcessInstanceQuery rootProcessInstances() {
        add("rootProcessInstances");
        return this;
    }

    public BpmProcessInstanceQuery orderByProcessInstanceId() {
        add("orderByProcessInstanceId");
        return this;
    }

    public BpmProcessInstanceQuery orderByProcessDefinitionKey() {
        add("orderByProcessDefinitionKey");
        return this;
    }

    public BpmProcessInstanceQuery orderByProcessDefinitionId() {
        add("orderByProcessDefinitionId");
        return this;
    }

    public BpmProcessInstanceQuery orderByTenantId() {
        add("orderByTenantId");
        return this;
    }

    public BpmProcessInstanceQuery orderByBusinessKey() {
        add("orderByBusinessKey");
        return this;
    }
}
