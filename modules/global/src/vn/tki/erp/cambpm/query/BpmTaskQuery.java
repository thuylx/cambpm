package vn.tki.erp.cambpm.query;

import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.variable.type.ValueType;
import vn.tki.erp.cambpm.entity.BpmTask;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BpmTaskQuery extends BpmQueryImpl<BpmTaskQuery, BpmTask> implements BpmQuery {
    private UUID taskListId;

    public void setTaskListId(UUID taskListId) {
        this.taskListId = taskListId;
    }

    public UUID getTaskListId() {
        return taskListId;
    }

    public BpmTaskQuery taskId(String taskId) {
        addString("taskId", taskId);
        return this;
    }

    public BpmTaskQuery taskName(String name) {
        addString("taskName", name);
        return this;
    }

    public BpmTaskQuery taskNameNotEqual(String name) {
        addString("taskNameNotEqual", name);
        return this;
    }

    public BpmTaskQuery taskNameLike(String nameLike) {
        addString("taskNameLike", nameLike);
        return this;
    }

    public BpmTaskQuery taskNameNotLike(String nameNotLike) {
        addString("taskNameNotLike", nameNotLike);
        return this;
    }

    public BpmTaskQuery taskDescription(String description) {
        addString("taskDescription", description);
        return this;
    }

    public BpmTaskQuery taskDescriptionLike(String descriptionLike) {
        addString("taskDescriptionLike", descriptionLike);
        return this;
    }

    public BpmTaskQuery taskPriority(Integer priority) {
        addInteger("taskPriority",priority);
        return this;
    }

    public BpmTaskQuery taskMinPriority(Integer minPriority) {
        addInteger("taskMinPriority",minPriority);
        return this;
    }

    public BpmTaskQuery taskMaxPriority(Integer maxPriority) {
        addInteger("taskMaxPriority",maxPriority);
        return this;
    }

    public BpmTaskQuery taskAssignee(String assignee) {
        addString("taskAssignee", assignee);
        return this;
    }

    public BpmTaskQuery taskAssigneeExpression(String assigneeExpression) {
        addString("taskAssigneeExpression", assigneeExpression);
        return this;
    }

    public BpmTaskQuery taskAssigneeLike(String assignee) {
        addString("taskAssigneeLike", assignee);
        return this;
    }

    public BpmTaskQuery taskAssigneeLikeExpression(String assigneeLikeExpression) {
        addString("taskAssigneeLikeExpression", assigneeLikeExpression);
        return this;
    }

    public BpmTaskQuery taskOwner(String owner) {
        addString("taskOwner", owner);
        return this;
    }

    public BpmTaskQuery taskOwnerExpression(String ownerExpression) {
        addString("taskOwnerExpression", ownerExpression);
        return this;
    }

    public BpmTaskQuery taskUnassigned() {
        add("taskUnassigned");
        return this;
    }

    public BpmTaskQuery taskUnnassigned() {
        add("taskUnnassigned");
        return this;
    }

    public BpmTaskQuery taskAssigned() {
        add("taskAssigned");
        return this;
    }

    public BpmTaskQuery taskDelegationState(DelegationState delegationState) {
        addDelegationState("taskDelegationState", delegationState);
        return this;
    }

    public BpmTaskQuery taskCandidateUser(String candidateUser) {
        addString("taskCandidateUser", candidateUser);
        return this;
    }

    public BpmTaskQuery taskCandidateUserExpression(String candidateUserExpression) {
        addString("taskCandidateUserExpression", candidateUserExpression);
        return this;
    }

    public BpmTaskQuery taskInvolvedUser(String involvedUser) {
        addString("taskInvolvedUser", involvedUser);
        return this;
    }

    public BpmTaskQuery taskInvolvedUserExpression(String involvedUserExpression) {
        addString("taskInvolvedUserExpression", involvedUserExpression);
        return this;
    }

    public BpmTaskQuery withCandidateGroups() {
        add("withCandidateGroups");
        return this;
    }

    public BpmTaskQuery withoutCandidateGroups() {
        add("withoutCandidateGroups");
        return this;
    }

    public BpmTaskQuery withCandidateUsers() {
        add("withCandidateUsers");
        return this;
    }

    public BpmTaskQuery withoutCandidateUsers() {
        add("withoutCandidateUsers");
        return this;
    }

    public BpmTaskQuery taskCandidateGroup(String candidateGroup) {
        addString("taskCandidateGroup", candidateGroup);
        return this;
    }

    public BpmTaskQuery taskCandidateGroupExpression(String candidateGroupExpression) {
        addString("taskCandidateGroupExpression", candidateGroupExpression);
        return this;
    }

    public BpmTaskQuery taskCandidateGroupIn(List<String> candidateGroups) {
        addList("taskCandidateGroupIn", candidateGroups);
        return this;
    }

    public BpmTaskQuery taskCandidateGroupInExpression(String candidateGroupsExpression) {
        addString("taskCandidateGroupInExpression", candidateGroupsExpression);
        return this;
    }

    public BpmTaskQuery includeAssignedTasks() {
        add("includeAssignedTasks");
        return this;
    }

    public BpmTaskQuery processInstanceId(String processInstanceId) {
        addString("processInstanceId", processInstanceId);
        return this;
    }

    public BpmTaskQuery processInstanceBusinessKey(String processInstanceBusinessKey) {
        addString("processInstanceBusinessKey", processInstanceBusinessKey);
        return this;
    }

    public BpmTaskQuery processInstanceBusinessKeyExpression(String processInstanceBusinessKeyExpression) {
        addString("processInstanceBusinessKeyExpression", processInstanceBusinessKeyExpression);
        return this;
    }

    public BpmTaskQuery processInstanceBusinessKeyIn(String... processInstanceBusinessKeys) {
        addStringArray("processInstanceBusinessKeyIn", processInstanceBusinessKeys);
        return this;
    }

    public BpmTaskQuery processInstanceBusinessKeyLike(String processInstanceBusinessKey) {
        addString("processInstanceBusinessKeyLike", processInstanceBusinessKey);
        return this;
    }

    public BpmTaskQuery processInstanceBusinessKeyLikeExpression(String processInstanceBusinessKeyExpression) {
        addString("processInstanceBusinessKeyLikeExpression", processInstanceBusinessKeyExpression);
        return this;
    }

    public BpmTaskQuery executionId(String executionId) {
        addString("executionId", executionId);
        return this;
    }

    public BpmTaskQuery activityInstanceIdIn(String... activityInstanceIds) {
        addStringArray("activityInstanceIdIn", activityInstanceIds);
        return this;
    }

    public BpmTaskQuery taskCreatedOn(Date createTime) {
        addDate("taskCreatedOn", createTime);
        return this;
    }

    public BpmTaskQuery taskCreatedOnExpression(String createTimeExpression) {
        addString("taskCreatedOnExpression", createTimeExpression);
        return this;
    }

    public BpmTaskQuery taskCreatedBefore(Date before) {
        addDate("taskCreatedBefore", before);
        return this;
    }

    public BpmTaskQuery taskCreatedBeforeExpression(String beforeExpression) {
        addString("taskCreatedBeforeExpression", beforeExpression);
        return this;
    }

    public BpmTaskQuery taskCreatedAfter(Date after) {
        addDate("taskCreatedAfter", after);
        return this;
    }

    public BpmTaskQuery taskCreatedAfterExpression(String afterExpression) {
        addString("taskCreatedAfterExpression", afterExpression);
        return this;
    }

    public BpmTaskQuery excludeSubtasks() {
        add("excludeSubtasks");
        return this;
    }

    public BpmTaskQuery taskDefinitionKey(String key) {
        addString("taskDefinitionKey", key);
        return this;
    }

    public BpmTaskQuery taskDefinitionKeyLike(String keyLike) {
        addString("taskDefinitionKeyLike", keyLike);
        return this;
    }

    public BpmTaskQuery taskDefinitionKeyIn(String... taskDefinitionKeys) {
        addStringArray("taskDefinitionKeyIn", taskDefinitionKeys);
        return this;
    }

    public BpmTaskQuery taskParentTaskId(String parentTaskId) {
        addString("taskParentTaskId", parentTaskId);
        return this;
    }

    public BpmTaskQuery caseInstanceId(String caseInstanceId) {
        addString("caseInstanceId", caseInstanceId);
        return this;
    }

    public BpmTaskQuery caseInstanceBusinessKey(String caseInstanceBusinessKey) {
        addString("caseInstanceBusinessKey", caseInstanceBusinessKey);
        return this;
    }

    public BpmTaskQuery caseInstanceBusinessKeyLike(String caseInstanceBusinessKeyLike) {
        addString("caseInstanceBusinessKeyLike", caseInstanceBusinessKeyLike);
        return this;
    }

    public BpmTaskQuery caseExecutionId(String caseExecutionId) {
        addString("caseExecutionId", caseExecutionId);
        return this;
    }

    public BpmTaskQuery caseDefinitionKey(String caseDefinitionKey) {
        addString("caseDefinitionKey", caseDefinitionKey);
        return this;
    }

    public BpmTaskQuery caseDefinitionId(String caseDefinitionId) {
        addString("caseDefinitionId", caseDefinitionId);
        return this;
    }

    public BpmTaskQuery caseDefinitionName(String caseDefinitionName) {
        addString("caseDefinitionName", caseDefinitionName);
        return this;
    }

    public BpmTaskQuery caseDefinitionNameLike(String caseDefinitionNameLike) {
        addString("caseDefinitionNameLike", caseDefinitionNameLike);
        return this;
    }

    public BpmTaskQuery taskVariableValueEquals(String variableName, Object variableValue) {
        addStringObject("taskVariableValueEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery taskVariableValueNotEquals(String variableName, Object variableValue) {
        addStringObject("taskVariableValueNotEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery taskVariableValueLike(String variableName, String variableValue) {
        addStringObject("taskVariableValueLike", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery taskVariableValueGreaterThan(String variableName, Object variableValue) {
        addStringObject("taskVariableValueGreaterThan", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery taskVariableValueGreaterThanOrEquals(String variableName, Object variableValue) {
        addStringObject("taskVariableValueGreaterThanOrEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery taskVariableValueLessThan(String variableName, Object variableValue) {
        addStringObject("taskVariableValueLessThan", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery taskVariableValueLessThanOrEquals(String variableName, Object variableValue) {
        addStringObject("taskVariableValueLessThanOrEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery processVariableValueEquals(String variableName, Object variableValue) {
        addStringObject("processVariableValueEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery processVariableValueNotEquals(String variableName, Object variableValue) {
        addStringObject("processVariableValueNotEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery processVariableValueLike(String variableName, String variableValue) {
        addStringString("processVariableValueLike", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery processVariableValueGreaterThan(String variableName, Object variableValue) {
        addStringObject("processVariableValueGreaterThan", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery processVariableValueGreaterThanOrEquals(String variableName, Object variableValue) {
        addStringObject("processVariableValueGreaterThanOrEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery processVariableValueLessThan(String variableName, Object variableValue) {
        addStringObject("processVariableValueLessThan", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery processVariableValueLessThanOrEquals(String variableName, Object variableValue) {
        addStringObject("processVariableValueLessThanOrEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery caseInstanceVariableValueEquals(String variableName, Object variableValue) {
        addStringObject("caseInstanceVariableValueEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery caseInstanceVariableValueNotEquals(String variableName, Object variableValue) {
        addStringObject("caseInstanceVariableValueNotEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery caseInstanceVariableValueLike(String variableName, String variableValue) {
        addStringString("caseInstanceVariableValueLike", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery caseInstanceVariableValueGreaterThan(String variableName, Object variableValue) {
        addStringObject("caseInstanceVariableValueGreaterThan", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery caseInstanceVariableValueGreaterThanOrEquals(String variableName, Object variableValue) {
        addStringObject("caseInstanceVariableValueGreaterThanOrEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery caseInstanceVariableValueLessThan(String variableName, Object variableValue) {
        addStringObject("caseInstanceVariableValueLessThan", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery caseInstanceVariableValueLessThanOrEquals(String variableName, Object variableValue) {
        addStringObject("caseInstanceVariableValueLessThanOrEquals", variableName, variableValue);
        return this;
    }

    public BpmTaskQuery processDefinitionKey(String processDefinitionKey) {
        addString("processDefinitionKey", processDefinitionKey);
        return this;
    }

    public BpmTaskQuery processDefinitionKeyIn(String... processDefinitionKeys) {
        addStringArray("processDefinitionKeyIn", processDefinitionKeys);
        return this;
    }

    public BpmTaskQuery processDefinitionId(String processDefinitionId) {
        addString("processDefinitionId", processDefinitionId);
        return this;
    }

    public BpmTaskQuery processDefinitionName(String processDefinitionName) {
        addString("processDefinitionName", processDefinitionName);
        return this;
    }

    public BpmTaskQuery processDefinitionNameLike(String processDefinitionName) {
        addString("processDefinitionNameLike", processDefinitionName);
        return this;
    }

    public BpmTaskQuery dueDate(Date dueDate) {
        addDate("dueDate", dueDate);
        return this;
    }

    public BpmTaskQuery dueDateExpression(String dueDateExpression) {
        addString("dueDateExpression", dueDateExpression);
        return this;
    }

    public BpmTaskQuery dueBefore(Date dueDate) {
        addDate("dueBefore", dueDate);
        return this;
    }

    public BpmTaskQuery dueBeforeExpression(String dueDateExpression) {
        addString("dueBeforeExpression", dueDateExpression);
        return this;
    }

    public BpmTaskQuery dueAfter(Date dueDate) {
        addDate("dueAfter", dueDate);
        return this;
    }

    public BpmTaskQuery dueAfterExpression(String dueDateExpression) {
        addString("dueAfterExpression", dueDateExpression);
        return this;
    }

    public BpmTaskQuery followUpDate(Date followUpDate) {
        addDate("followUpDate", followUpDate);
        return this;
    }

    public BpmTaskQuery followUpDateExpression(String followUpDateExpression) {
        addString("followUpDateExpression", followUpDateExpression);
        return this;
    }

    public BpmTaskQuery followUpBefore(Date followUpDate) {
        addDate("followUpBefore", followUpDate);
        return this;
    }

    public BpmTaskQuery followUpBeforeExpression(String followUpDateExpression) {
        addString("followUpBeforeExpression", followUpDateExpression);
        return this;
    }

    public BpmTaskQuery followUpBeforeOrNotExistent(Date followUpDate) {
        addDate("followUpBeforeOrNotExistent", followUpDate);
        return this;
    }

    public BpmTaskQuery followUpBeforeOrNotExistentExpression(String followUpDateExpression) {
        addString("followUpBeforeOrNotExistentExpression", followUpDateExpression);
        return this;
    }

    public BpmTaskQuery followUpAfter(Date followUpDate) {
        addDate("followUpAfter", followUpDate);
        return this;
    }

    public BpmTaskQuery followUpAfterExpression(String followUpDateExpression) {
        addString("followUpAfterExpression", followUpDateExpression);
        return this;
    }

    public BpmTaskQuery suspended() {
        add("suspended");
        return this;
    }

    public BpmTaskQuery active() {
        add("active");
        return this;
    }

    public BpmTaskQuery initializeFormKeys() {
        add("initializeFormKeys");
        return this;
    }

    public BpmTaskQuery tenantIdIn(String... tenantIds) {
        addStringArray("tenantIdIn", tenantIds);
        return this;
    }

    public BpmTaskQuery withoutTenantId() {
        add("withoutTenantId");
        return this;
    }

    public BpmTaskQuery orderByTaskId() {
        add("orderByTaskId");
        return this;
    }

    public BpmTaskQuery orderByTaskName() {
        add("orderByTaskName");
        return this;
    }

    public BpmTaskQuery orderByTaskNameCaseInsensitive() {
        add("orderByTaskNameCaseInsensitive");
        return this;
    }

    public BpmTaskQuery orderByTaskDescription() {
        add("orderByTaskDescription");
        return this;
    }

    public BpmTaskQuery orderByTaskPriority() {
        add("orderByTaskPriority");
        return this;
    }

    public BpmTaskQuery orderByTaskAssignee() {
        add("orderByTaskAssignee");
        return this;
    }

    public BpmTaskQuery orderByTaskCreateTime() {
        add("orderByTaskCreateTime");
        return this;
    }

    public BpmTaskQuery orderByProcessInstanceId() {
        add("orderByProcessInstanceId");
        return this;
    }

    public BpmTaskQuery orderByCaseInstanceId() {
        add("orderByCaseInstanceId");
        return this;
    }

    public BpmTaskQuery orderByExecutionId() {
        add("orderByExecutionId");
        return this;
    }

    public BpmTaskQuery orderByCaseExecutionId() {
        add("orderByCaseExecutionId");
        return this;
    }

    public BpmTaskQuery orderByDueDate() {
        add("orderByDueDate");
        return this;
    }

    public BpmTaskQuery orderByFollowUpDate() {
        add("orderByFollowUpDate");
        return this;
    }

    public BpmTaskQuery orderByProcessVariable(String variableName, ValueType valueType) {
        addStringValueType("orderByProcessVariable", variableName, valueType);
        return this;
    }

    public BpmTaskQuery orderByExecutionVariable(String variableName, ValueType valueType) {
        addStringValueType("orderByExecutionVariable", variableName, valueType);
        return this;
    }

    public BpmTaskQuery orderByTaskVariable(String variableName, ValueType valueType) {
        addStringValueType("orderByTaskVariable", variableName, valueType);
        return this;
    }

    public BpmTaskQuery orderByCaseExecutionVariable(String variableName, ValueType valueType) {
        addStringValueType("orderByCaseExecutionVariable", variableName, valueType);
        return this;
    }

    public BpmTaskQuery orderByCaseInstanceVariable(String variableName, ValueType valueType) {
        addStringValueType("orderByCaseInstanceVariable", variableName, valueType);
        return this;
    }

    public BpmTaskQuery orderByTenantId() {
        add("orderByTenantId");
        return this;
    }

    public BpmTaskQuery or() {
        add("or");
        return this;
    }

    public BpmTaskQuery endOr() {
        add("endOr");
        return this;
    }
}
