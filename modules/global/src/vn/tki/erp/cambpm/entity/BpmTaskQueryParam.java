package vn.tki.erp.cambpm.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum BpmTaskQueryParam implements EnumClass<String> {
    ACTIVE("active"),
    ACTIVITY_INSTANCE_ID_IN("activityInstanceIdIn"),
    CASE_DEFINITION_ID("caseDefinitionId"),
    CASE_DEFINITION_KEY("caseDefinitionKey"),
    CASE_DEFINITION_NAME("caseDefinitionName"),
    CASE_DEFINITION_NAME_LIKE("caseDefinitionNameLike"),
    CASE_EXECUTION_ID("caseExecutionId"),
    CASE_INSTANCE_BUSINESS_KEY("caseInstanceBusinessKey"),
    CASE_INSTANCE_BUSINESS_KEY_LIKE("caseInstanceBusinessKeyLike"),
    CASE_INSTANCE_ID("caseInstanceId"),
    CASE_INSTANCE_VARIABLE_VALUE_EQUALS("caseInstanceVariableValueEquals"),
    CASE_INSTANCE_VARIABLE_VALUE_GREATER_THAN("caseInstanceVariableValueGreaterThan"),
    CASE_INSTANCE_VARIABLE_VALUE_GREATER_THAN_OR_EQUALS("caseInstanceVariableValueGreaterThanOrEquals"),
    CASE_INSTANCE_VARIABLE_VALUE_LESS_THAN("caseInstanceVariableValueLessThan"),
    CASE_INSTANCE_VARIABLE_VALUE_LESS_THAN_OR_EQUALS("caseInstanceVariableValueLessThanOrEquals"),
    CASE_INSTANCE_VARIABLE_VALUE_LIKE("caseInstanceVariableValueLike"),
    CASE_INSTANCE_VARIABLE_VALUE_NOT_EQUALS("caseInstanceVariableValueNotEquals"),
    DUE_AFTER("dueAfter"),
    DUE_AFTER_EXPRESSION("dueAfterExpression"),
    DUE_BEFORE("dueBefore"),
    DUE_BEFORE_EXPRESSION("dueBeforeExpression"),
    DUE_DATE("dueDate"),
    DUE_DATE_EXPRESSION("dueDateExpression"),
    EXCLUDE_SUBTASKS("excludeSubtasks"),
    EXECUTION_ID("executionId"),
    FOLLOW_UP_AFTER("followUpAfter"),
    FOLLOW_UP_AFTER_EXPRESSION("followUpAfterExpression"),
    FOLLOW_UP_BEFORE("followUpBefore"),
    FOLLOW_UP_BEFORE_EXPRESSION("followUpBeforeExpression"),
    FOLLOW_UP_BEFORE_OR_NOT_EXISTENT("followUpBeforeOrNotExistent"),
    FOLLOW_UP_BEFORE_OR_NOT_EXISTENT_EXPRESSION("followUpBeforeOrNotExistentExpression"),
    FOLLOW_UP_DATE("followUpDate"),
    FOLLOW_UP_DATE_EXPRESSION("followUpDateExpression"),
    INITIALIZE_FORM_KEYS("initializeFormKeys"),
    ORDER_BY_CASE_EXECUTION_ID("orderByCaseExecutionId"),
    ORDER_BY_CASE_INSTANCE_ID("orderByCaseInstanceId"),
    ORDER_BY_DUE_DATE("orderByDueDate"),
    ORDER_BY_EXECUTION_ID("orderByExecutionId"),
    ORDER_BY_FOLLOW_UP_DATE("orderByFollowUpDate"),
    ORDER_BY_PROCESS_INSTANCE_ID("orderByProcessInstanceId"),
    ORDER_BY_TASK_ASSIGNEE("orderByTaskAssignee"),
    ORDER_BY_TASK_CREATE_TIME("orderByTaskCreateTime"),
    ORDER_BY_TASK_DESCRIPTION("orderByTaskDescription"),
    ORDER_BY_TASK_ID("orderByTaskId"),
    ORDER_BY_TASK_NAME("orderByTaskName"),
    ORDER_BY_TASK_NAME_CASE_INSENSITIVE("orderByTaskNameCaseInsensitive"),
    ORDER_BY_TASK_PRIORITY("orderByTaskPriority"),
    PROCESS_DEFINITION_ID("processDefinitionId"),
    PROCESS_DEFINITION_KEY("processDefinitionKey"),
    PROCESS_DEFINITION_NAME("processDefinitionName"),
    PROCESS_DEFINITION_NAME_LIKE("processDefinitionNameLike"),
    PROCESS_INSTANCE_BUSINESS_KEY("processInstanceBusinessKey"),
    PROCESS_INSTANCE_BUSINESS_KEY_LIKE("processInstanceBusinessKeyLike"),
    PROCESS_INSTANCE_ID("processInstanceId"),
    PROCESS_VARIABLE_VALUE_EQUALS("processVariableValueEquals"),
    PROCESS_VARIABLE_VALUE_GREATER_THAN("processVariableValueGreaterThan"),
    PROCESS_VARIABLE_VALUE_GREATER_THAN_OR_EQUALS("processVariableValueGreaterThanOrEquals"),
    PROCESS_VARIABLE_VALUE_LESS_THAN("processVariableValueLessThan"),
    PROCESS_VARIABLE_VALUE_LESS_THAN_OR_EQUALS("processVariableValueLessThanOrEquals"),
    PROCESS_VARIABLE_VALUE_LIKE("processVariableValueLike"),
    PROCESS_VARIABLE_VALUE_NOT_EQUALS("processVariableValueNotEquals"),
    SUSPENDED("suspended"),
    TASK_ASSIGNEE("taskAssignee"),
    TASK_ASSIGNEE_EXPRESSION("taskAssigneeExpression"),
    TASK_ASSIGNEE_LIKE("taskAssigneeLike"),
    TASK_ASSIGNEE_LIKE_EXPRESSION("taskAssigneeLikeExpression"),
    TASK_CANDIDATE_GROUP("taskCandidateGroup"),
    TASK_CANDIDATE_GROUP_EXPRESSION("taskCandidateGroupExpression"),
    TASK_CANDIDATE_GROUP_IN("taskCandidateGroupIn"),
    TASK_CANDIDATE_GROUP_IN_EXPRESSION("taskCandidateGroupInExpression"),
    TASK_CANDIDATE_USER("taskCandidateUser"),
    TASK_CANDIDATE_USER_EXPRESSION("taskCandidateUserExpression"),
    TASK_CREATED_AFTER("taskCreatedAfter"),
    TASK_CREATED_AFTER_EXPRESSION("taskCreatedAfterExpression"),
    TASK_CREATED_BEFORE("taskCreatedBefore"),
    TASK_CREATED_BEFORE_EXPRESSION("taskCreatedBeforeExpression"),
    TASK_CREATED_ON("taskCreatedOn"),
    TASK_CREATED_ON_EXPRESSION("taskCreatedOnExpression"),
    TASK_DEFINITION_KEY("taskDefinitionKey"),
    TASK_DEFINITION_KEY_LIKE("taskDefinitionKeyLike"),
    TASK_DELEGATION_STATE("taskDelegationState"),
    TASK_DESCRIPTION("taskDescription"),
    TASK_DESCRIPTION_LIKE("taskDescriptionLike"),
    TASK_ID("taskId"),
    TASK_INVOLVED_USER("taskInvolvedUser"),
    TASK_INVOLVED_USER_EXPRESSION("taskInvolvedUserExpression"),
    TASK_MAX_PRIORITY("taskMaxPriority"),
    TASK_MIN_PRIORITY("taskMinPriority"),
    TASK_NAME("taskName"),
    TASK_NAME_LIKE("taskNameLike"),
    TASK_OWNER("taskOwner"),
    TASK_OWNER_EXPRESSION("taskOwnerExpression"),
    TASK_PRIORITY("taskPriority"),
    TASK_UNASSIGNED("taskUnassigned"),
    TASK_VARIABLE_VALUE_EQUALS("taskVariableValueEquals"),
    TASK_VARIABLE_VALUE_GREATER_THAN("taskVariableValueGreaterThan"),
    TASK_VARIABLE_VALUE_GREATER_THAN_OR_EQUALS("taskVariableValueGreaterThanOrEquals"),
    TASK_VARIABLE_VALUE_LESS_THAN("taskVariableValueLessThan"),
    TASK_VARIABLE_VALUE_LESS_THAN_OR_EQUALS("taskVariableValueLessThanOrEquals"),
    TASK_VARIABLE_VALUE_LIKE("taskVariableValueLike"),
    TASK_VARIABLE_VALUE_NOT_EQUALS("taskVariableValueNotEquals");

    private String id;

    BpmTaskQueryParam(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static BpmTaskQueryParam fromId(String id) {
        for (BpmTaskQueryParam at : BpmTaskQueryParam.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}