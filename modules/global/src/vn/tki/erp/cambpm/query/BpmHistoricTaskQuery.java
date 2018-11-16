package vn.tki.erp.cambpm.query;

import vn.tki.erp.cambpm.entity.BpmTask;

import java.util.Date;

public class BpmHistoricTaskQuery extends BpmQueryImpl<BpmHistoricTaskQuery, BpmTask> implements BpmQuery {
    public BpmHistoricTaskQuery taskId(String taskId) {
        addString("taskId",taskId);
        return this;
    }


    public BpmHistoricTaskQuery processInstanceId(String processInstanceId) {
        addString("processInstanceId",processInstanceId);
        return this;
    }


    public BpmHistoricTaskQuery processInstanceBusinessKey(String processInstanceBusinessKey) {
        addString("processInstanceBusinessKey",processInstanceBusinessKey);
        return this;
    }


    public BpmHistoricTaskQuery processInstanceBusinessKeyIn(String... processInstanceBusinessKeys) {
        addStringArray("processInstanceBusinessKeyIn",processInstanceBusinessKeys);
        return this;
    }


    public BpmHistoricTaskQuery processInstanceBusinessKeyLike(String processInstanceBusinessKey) {
        addString("processInstanceBusinessKeyLike",processInstanceBusinessKey);
        return this;
    }


    public BpmHistoricTaskQuery executionId(String executionId) {
        addString("executionId",executionId);
        return this;
    }


    public BpmHistoricTaskQuery activityInstanceIdIn(String... activityInstanceIds) {
        addStringArray("activityInstanceIdIn",activityInstanceIds);
        return this;
    }


    public BpmHistoricTaskQuery processDefinitionId(String processDefinitionId) {
        addString("processDefinitionId",processDefinitionId);
        return this;
    }


    public BpmHistoricTaskQuery processDefinitionKey(String processDefinitionKey) {
        addString("processDefinitionKey",processDefinitionKey);
        return this;
    }


    public BpmHistoricTaskQuery processDefinitionName(String processDefinitionName) {
        addString("processDefinitionName",processDefinitionName);
        return this;
    }


    public BpmHistoricTaskQuery caseDefinitionId(String caseDefinitionId) {
        addString("caseDefinitionId",caseDefinitionId);
        return this;
    }


    public BpmHistoricTaskQuery caseDefinitionKey(String caseDefinitionKey) {
        addString("caseDefinitionKey",caseDefinitionKey);
        return this;
    }


    public BpmHistoricTaskQuery caseDefinitionName(String caseDefinitionName) {
        addString("caseDefinitionName",caseDefinitionName);
        return this;
    }


    public BpmHistoricTaskQuery caseInstanceId(String caseInstanceId) {
        addString("caseInstanceId",caseInstanceId);
        return this;
    }


    public BpmHistoricTaskQuery caseExecutionId(String caseExecutionId) {
        addString("caseExecutionId",caseExecutionId);
        return this;
    }


    public BpmHistoricTaskQuery taskName(String taskName) {
        addString("taskName",taskName);
        return this;
    }


    public BpmHistoricTaskQuery taskNameLike(String taskNameLike) {
        addString("taskNameLike",taskNameLike);
        return this;
    }


    public BpmHistoricTaskQuery taskDescription(String taskDescription) {
        addString("taskDescription",taskDescription);
        return this;
    }


    public BpmHistoricTaskQuery taskDescriptionLike(String taskDescriptionLike) {
        addString("taskDescriptionLike",taskDescriptionLike);
        return this;
    }


    public BpmHistoricTaskQuery taskDefinitionKey(String taskDefinitionKey) {
        addString("taskDefinitionKey",taskDefinitionKey);
        return this;
    }


    public BpmHistoricTaskQuery taskDefinitionKeyIn(String... taskDefinitionKeys) {
        addStringArray("taskDefinitionKeyIn",taskDefinitionKeys);
        return this;
    }


    public BpmHistoricTaskQuery taskDeleteReason(String taskDeleteReason) {
        addString("taskDeleteReason",taskDeleteReason);
        return this;
    }


    public BpmHistoricTaskQuery taskDeleteReasonLike(String taskDeleteReasonLike) {
        addString("taskDeleteReasonLike",taskDeleteReasonLike);
        return this;
    }


    public BpmHistoricTaskQuery taskAssigned() {
        add("taskAssigned");
        return this;
    }


    public BpmHistoricTaskQuery taskUnassigned() {
        add("taskUnassigned");
        return this;
    }


    public BpmHistoricTaskQuery taskAssignee(String taskAssignee) {
        addString("taskAssignee",taskAssignee);
        return this;
    }


    public BpmHistoricTaskQuery taskAssigneeLike(String taskAssigneeLike) {
        addString("taskAssigneeLike",taskAssigneeLike);
        return this;
    }


    public BpmHistoricTaskQuery taskOwner(String taskOwner) {
        addString("taskOwner",taskOwner);
        return this;
    }


    public BpmHistoricTaskQuery taskOwnerLike(String taskOwnerLike) {
        addString("taskOwnerLike",taskOwnerLike);
        return this;
    }


    public BpmHistoricTaskQuery taskPriority(Integer taskPriority) {
        addInteger("taskPriority",taskPriority);
        return this;
    }


    public BpmHistoricTaskQuery finished() {
        add("finished");
        return this;
    }


    public BpmHistoricTaskQuery unfinished() {
        add("unfinished");
        return this;
    }


    public BpmHistoricTaskQuery processFinished() {
        add("processFinished");
        return this;
    }


    public BpmHistoricTaskQuery processUnfinished() {
        add("processUnfinished");
        return this;
    }


    public BpmHistoricTaskQuery taskInvolvedUser(String involvedUser) {
        addString("taskInvolvedUser",involvedUser);
        return this;
    }


    public BpmHistoricTaskQuery taskInvolvedGroup(String involvedGroup) {
        addString("taskInvolvedGroup",involvedGroup);
        return this;
    }


    public BpmHistoricTaskQuery taskHadCandidateUser(String candidateUser) {
        addString("taskHadCandidateUser",candidateUser);
        return this;
    }


    public BpmHistoricTaskQuery taskHadCandidateGroup(String candidateGroup) {
        addString("taskHadCandidateGroup",candidateGroup);
        return this;
    }


    public BpmHistoricTaskQuery withCandidateGroups() {
        add("withCandidateGroups");
        return this;
    }


    public BpmHistoricTaskQuery withoutCandidateGroups() {
        add("withoutCandidateGroups");
        return this;
    }


    public BpmHistoricTaskQuery taskVariableValueEquals(String variableName, Object variableValue) {
        addStringObject("taskVariableValueEquals", variableName, variableValue);
        return this;
    }


    public BpmHistoricTaskQuery taskParentTaskId(String parentTaskId) {
        addString("taskParentTaskId",parentTaskId);
        return this;
    }


    public BpmHistoricTaskQuery processVariableValueEquals(String variableName, Object variableValue) {
        addStringObject("processVariableValueEquals", variableName, variableValue);
        return this;
    }


    public BpmHistoricTaskQuery processVariableValueNotEquals(String variableName, Object variableValue) {
        addStringObject("processVariableValueNotEquals", variableName, variableValue);
        return this;
    }


    public BpmHistoricTaskQuery processVariableValueLike(String variableName, Object variableValue) {
        addStringObject("processVariableValueLike", variableName, variableValue);
        return this;
    }


    public BpmHistoricTaskQuery processVariableValueGreaterThan(String variableName, Object variableValue) {
        addStringObject("processVariableValueGreaterThan", variableName, variableValue);
        return this;
    }


    public BpmHistoricTaskQuery processVariableValueGreaterThanOrEquals(String variableName, Object variableValue) {
        addStringObject("processVariableValueGreaterThanOrEquals", variableName, variableValue);
        return this;
    }


    public BpmHistoricTaskQuery processVariableValueLessThan(String variableName, Object variableValue) {
        addStringObject("processVariableValueLessThan", variableName, variableValue);
        return this;
    }


    public BpmHistoricTaskQuery processVariableValueLessThanOrEquals(String variableName, Object variableValue) {
        addStringObject("processVariableValueLessThanOrEquals", variableName, variableValue);
        return this;
    }


    public BpmHistoricTaskQuery taskDueDate(Date dueDate) {
        addDate("taskDueDate", dueDate);
        return this;
    }


    public BpmHistoricTaskQuery taskDueBefore(Date dueDate) {
        addDate("taskDueBefore", dueDate);
        return this;
    }


    public BpmHistoricTaskQuery taskDueAfter(Date dueDate) {
        addDate("taskDueAfter", dueDate);
        return this;
    }


    public BpmHistoricTaskQuery taskFollowUpDate(Date followUpDate) {
        addDate("taskFollowUpDate", followUpDate);
        return this;
    }


    public BpmHistoricTaskQuery taskFollowUpBefore(Date followUpDate) {
        addDate("taskFollowUpBefore", followUpDate);
        return this;
    }


    public BpmHistoricTaskQuery taskFollowUpAfter(Date followUpDate) {
        addDate("taskFollowUpAfter", followUpDate);
        return this;
    }


    public BpmHistoricTaskQuery tenantIdIn(String... tenantIds) {
        addStringArray("tenantIdIn",tenantIds);
        return this;
    }


    public BpmHistoricTaskQuery finishedAfter(Date date) {
        addDate("finishedAfter", date);
        return this;
    }


    public BpmHistoricTaskQuery finishedBefore(Date date) {
        addDate("finishedBefore", date);
        return this;
    }


    public BpmHistoricTaskQuery startedAfter(Date date) {
        addDate("startedAfter", date);
        return this;
    }


    public BpmHistoricTaskQuery startedBefore(Date date) {
        addDate("startedBefore", date);
        return this;
    }


    public BpmHistoricTaskQuery orderByTenantId() {
        add("orderByTenantId");
        return this;
    }


    public BpmHistoricTaskQuery orderByTaskId() {
        add("orderByTaskId");
        return this;
    }


    public BpmHistoricTaskQuery orderByHistoricActivityInstanceId() {
        add("orderByHistoricActivityInstanceId");
        return this;
    }


    public BpmHistoricTaskQuery orderByProcessDefinitionId() {
        add("orderByProcessDefinitionId");
        return this;
    }


    public BpmHistoricTaskQuery orderByProcessInstanceId() {
        add("orderByProcessInstanceId");
        return this;
    }


    public BpmHistoricTaskQuery orderByExecutionId() {
        add("orderByExecutionId");
        return this;
    }


    public BpmHistoricTaskQuery orderByHistoricTaskInstanceDuration() {
        add("orderByHistoricTaskInstanceDuration");
        return this;
    }


    public BpmHistoricTaskQuery orderByHistoricTaskInstanceEndTime() {
        add("orderByHistoricTaskInstanceEndTime");
        return this;
    }


    public BpmHistoricTaskQuery orderByHistoricActivityInstanceStartTime() {
        add("orderByHistoricActivityInstanceStartTime");
        return this;
    }


    public BpmHistoricTaskQuery orderByTaskName() {
        add("orderByTaskName");
        return this;
    }


    public BpmHistoricTaskQuery orderByTaskDescription() {
        add("orderByTaskDescription");
        return this;
    }


    public BpmHistoricTaskQuery orderByTaskAssignee() {
        add("orderByTaskAssignee");
        return this;
    }


    public BpmHistoricTaskQuery orderByTaskOwner() {
        add("orderByTaskOwner");
        return this;
    }


    public BpmHistoricTaskQuery orderByTaskDueDate() {
        add("orderByTaskDueDate");
        return this;
    }


    public BpmHistoricTaskQuery orderByTaskFollowUpDate() {
        add("orderByTaskFollowUpDate");
        return this;
    }


    public BpmHistoricTaskQuery orderByDeleteReason() {
        add("orderByDeleteReason");
        return this;
    }


    public BpmHistoricTaskQuery orderByTaskDefinitionKey() {
        add("orderByTaskDefinitionKey");
        return this;
    }

    public BpmHistoricTaskQuery orderByTaskPriority() {
        add("orderByTaskPriority");
        return this;
    }

    public BpmHistoricTaskQuery orderByCaseDefinitionId() {
        add("orderByCaseDefinitionId");
        return this;
    }

    public BpmHistoricTaskQuery orderByCaseInstanceId() {
        add("orderByCaseInstanceId");
        return this;
    }

    public BpmHistoricTaskQuery orderByCaseExecutionId() {
        add("orderByCaseExecutionId");
        return this;
    }
}
