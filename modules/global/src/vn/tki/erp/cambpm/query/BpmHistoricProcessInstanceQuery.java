package vn.tki.erp.cambpm.query;

import vn.tki.erp.cambpm.entity.BpmProcessInstance;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class BpmHistoricProcessInstanceQuery extends BpmQueryImpl<BpmHistoricProcessInstanceQuery, BpmProcessInstance> implements BpmQuery {
    public BpmHistoricProcessInstanceQuery processInstanceId(String processInstanceId) {
        addString("processInstanceId", processInstanceId);
        return this;
    }

    public BpmHistoricProcessInstanceQuery processInstanceIds(Set<String> processInstanceIds) {
        addSet("processInstanceIds", processInstanceIds);
        return this;
    }

    public BpmHistoricProcessInstanceQuery processDefinitionId(String processDefinitionId) {
        addString("processDefinitionId", processDefinitionId);
        return this;
    }

    public BpmHistoricProcessInstanceQuery processDefinitionKey(String processDefinitionKey) {
        addString("processDefinitionKey", processDefinitionKey);
        return this;
    }

    public BpmHistoricProcessInstanceQuery processDefinitionKeyNotIn(List<String> processDefinitionKeys) {
        addList("processDefinitionKeyNotIn", processDefinitionKeys);
        return this;
    }

    public BpmHistoricProcessInstanceQuery processDefinitionName(String processDefinitionName) {
        addString("processDefinitionName", processDefinitionName);
        return this;
    }

    public BpmHistoricProcessInstanceQuery processDefinitionNameLike(String nameLike) {
        addString("processDefinitionNameLike", nameLike);
        return this;
    }

    public BpmHistoricProcessInstanceQuery processInstanceBusinessKey(String processInstanceBusinessKey) {
        addString("processInstanceBusinessKey", processInstanceBusinessKey);
        return this;
    }

    public BpmHistoricProcessInstanceQuery processInstanceBusinessKeyLike(String processInstanceBusinessKeyLike) {
        addString("processInstanceBusinessKeyLike", processInstanceBusinessKeyLike);
        return this;
    }

    public BpmHistoricProcessInstanceQuery finished() {
        add("finished");
        return this;
    }

    public BpmHistoricProcessInstanceQuery unfinished() {
        add("unfinished");
        return this;
    }

    public BpmHistoricProcessInstanceQuery withIncidents() {
        add("withIncidents");
        return this;
    }

    public BpmHistoricProcessInstanceQuery withRootIncidents() {
        add("withRootIncidents");
        return this;
    }

    public BpmHistoricProcessInstanceQuery incidentStatus(String status) {
        addString("incidentStatus", status);
        return this;
    }

    public BpmHistoricProcessInstanceQuery incidentType(String incidentType) {
        addString("incidentType", incidentType);
        return this;
    }

    public BpmHistoricProcessInstanceQuery incidentMessage(String incidentMessage) {
        addString("incidentMessage", incidentMessage);
        return this;
    }

    public BpmHistoricProcessInstanceQuery incidentMessageLike(String incidentMessageLike) {
        addString("incidentMessageLike", incidentMessageLike);
        return this;
    }

    public BpmHistoricProcessInstanceQuery caseInstanceId(String caseInstanceId) {
        addString("caseInstanceId", caseInstanceId);
        return this;
    }

    public BpmHistoricProcessInstanceQuery variableValueEquals(String name, Object value) {
        addStringObject("variableValueEquals", name, value);
        return this;
    }

    public BpmHistoricProcessInstanceQuery variableValueNotEquals(String name, Object value) {
        addStringObject("variableValueNotEquals", name, value);
        return this;
    }

    public BpmHistoricProcessInstanceQuery variableValueGreaterThan(String name, Object value) {
        addStringObject("variableValueGreaterThan",name, value);
        return this;
    }

    public BpmHistoricProcessInstanceQuery variableValueGreaterThanOrEqual(String name, Object value) {
        addStringObject("variableValueGreaterThanOrEqual",name, value);
        return this;
    }

    public BpmHistoricProcessInstanceQuery variableValueLessThan(String name, Object value) {
        addStringObject("variableValueLessThan",name, value);
        return this;
    }

    public BpmHistoricProcessInstanceQuery variableValueLessThanOrEqual(String name, Object value) {
        addStringObject("variableValueLessThanOrEqual",name, value);
        return this;
    }

    public BpmHistoricProcessInstanceQuery variableValueLike(String name, String value) {
        addStringString("variableValueLike",name, value);
        return this;
    }

    public BpmHistoricProcessInstanceQuery startedBefore(Date date) {
        addDate("startedBefore",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery startedAfter(Date date) {
        addDate("startedAfter",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery finishedBefore(Date date) {
        addDate("finishedBefore",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery finishedAfter(Date date) {
        addDate("finishedAfter",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery startedBy(String userId) {
        addString("startedBy",userId);
        return this;
    }

    public BpmHistoricProcessInstanceQuery orderByProcessInstanceId() {
        add("orderByProcessInstanceId");
        return this;
    }

    public BpmHistoricProcessInstanceQuery orderByProcessDefinitionId() {
        add("orderByProcessDefinitionId");
        return this;
    }

    public BpmHistoricProcessInstanceQuery orderByProcessDefinitionKey() {
        add("orderByProcessDefinitionKey");
        return this;
    }

    public BpmHistoricProcessInstanceQuery orderByProcessDefinitionName() {
        add("orderByProcessDefinitionName");
        return this;
    }

    public BpmHistoricProcessInstanceQuery orderByProcessDefinitionVersion() {
        add("orderByProcessDefinitionVersion");
        return this;
    }

    public BpmHistoricProcessInstanceQuery orderByProcessInstanceBusinessKey() {
        add("orderByProcessInstanceBusinessKey");
        return this;
    }

    public BpmHistoricProcessInstanceQuery orderByProcessInstanceStartTime() {
        add("orderByProcessInstanceStartTime");
        return this;
    }

    public BpmHistoricProcessInstanceQuery orderByProcessInstanceEndTime() {
        add("orderByProcessInstanceEndTime");
        return this;
    }

    public BpmHistoricProcessInstanceQuery orderByProcessInstanceDuration() {
        add("orderByProcessInstanceDuration");
        return this;
    }

    public BpmHistoricProcessInstanceQuery superProcessInstanceId(String superProcessInstanceId) {
        addString("superProcessInstanceId",superProcessInstanceId);
        return this;
    }

    public BpmHistoricProcessInstanceQuery subProcessInstanceId(String subProcessInstanceId) {
        addString("subProcessInstanceId",subProcessInstanceId);
        return this;
    }

    public BpmHistoricProcessInstanceQuery superCaseInstanceId(String superCaseInstanceId) {
        addString("superCaseInstanceId",superCaseInstanceId);
        return this;
    }

    public BpmHistoricProcessInstanceQuery subCaseInstanceId(String subCaseInstanceId) {
        addString("subCaseInstanceId",subCaseInstanceId);
        return this;
    }

    public BpmHistoricProcessInstanceQuery tenantIdIn(String... tenantIds) {
        addStringArray("tenantIdIn", tenantIds);
        return this;
    }

    public BpmHistoricProcessInstanceQuery orderByTenantId() {
        add("orderByTenantId");
        return this;
    }

    public BpmHistoricProcessInstanceQuery startDateBy(Date date) {
        addDate("startDateBy",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery startDateOn(Date date) {
        addDate("startDateOn",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery finishDateBy(Date date) {
        addDate("finishDateBy",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery finishDateOn(Date date) {
        addDate("finishDateOn",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery executedActivityAfter(Date date) {
        addDate("executedActivityAfter",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery executedActivityBefore(Date date) {
        addDate("executedActivityBefore",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery executedActivityIdIn(String... ids) {
        addStringArray("executedActivityIdIn", ids);
        return this;
    }

    public BpmHistoricProcessInstanceQuery activeActivityIdIn(String... ids) {
        addStringArray("activeActivityIdIn", ids);
        return this;
    }

    public BpmHistoricProcessInstanceQuery executedJobAfter(Date date) {
        addDate("executedJobAfter",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery executedJobBefore(Date date) {
        addDate("executedJobBefore",date);
        return this;
    }

    public BpmHistoricProcessInstanceQuery active() {
        add("active");
        return this;
    }

    public BpmHistoricProcessInstanceQuery suspended() {
        add("suspended");
        return this;
    }

    public BpmHistoricProcessInstanceQuery completed() {
        add("completed");
        return this;
    }

    public BpmHistoricProcessInstanceQuery externallyTerminated() {
        add("externallyTerminated");
        return this;
    }

    public BpmHistoricProcessInstanceQuery internallyTerminated() {
        add("internallyTerminated");
        return this;
    }
}
