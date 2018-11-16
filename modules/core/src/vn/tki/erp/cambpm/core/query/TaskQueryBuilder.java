package vn.tki.erp.cambpm.core.query;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.DataManager;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.config.BpmConfig;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.entity.BpmTaskList;
import vn.tki.erp.cambpm.entity.BpmTaskListFilter;
import vn.tki.erp.cambpm.entity.BpmTaskQueryParam;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.exception.BpmTaskListException;
import vn.tki.erp.cambpm.helper.BpmEntityConverter;
import vn.tki.erp.cambpm.query.BpmQuery;
import vn.tki.erp.cambpm.query.BpmTaskQuery;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

import static vn.tki.erp.cambpm.entity.BpmTaskQueryParam.*;

@Component(TaskQueryBuilder.NAME)
public class TaskQueryBuilder extends AbstractQueryBuilder<TaskQuery, Task, BpmTask> {
    public static final String NAME = "cambpm_CamTaskQueryBuilder";

    @Inject
    private CamPlatform camPlatform;
    @Inject
    private BpmEntityConverter converter;
    @Inject
    private DataManager dataManager;

    @Override
    public boolean supports(Class queryContextClass) {
        return queryContextClass.equals(BpmTaskQuery.class);
    }

    @Override
    public void authorization() throws BpmAuthorizationException {
        checkReadPermission(BpmTask.class);
    }

    @Override
    public TaskQuery createQuery() {
        return camPlatform.getProcessEngine().getTaskService().createTaskQuery();
    }

    @Override
    public BpmTask convert(Task object) {
        return converter.toBpmTask(object);
    }

    @Override
    public void preConfigQuery(TaskQuery camQuery, BpmQuery queryContext) {
        if (queryContext instanceof BpmTaskQuery) {
            if (((BpmTaskQuery) queryContext).getTaskListId() != null) {
                configQuery(camQuery, ((BpmTaskQuery) queryContext).getTaskListId());
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    private void configQuery(TaskQuery query, UUID bpmTaskListId) throws BpmTaskListException {
        BpmTaskList taskList = dataManager.load(BpmTaskList.class).id(bpmTaskListId).view("bpmTaskList-view").one();
        try {
            Configuration configuration = AppBeans.get(Configuration.class);
            BpmConfig bpmConfig = configuration.getConfig(BpmConfig.class);
            String delimiter = bpmConfig.getTaskListFilterValueDelimiter();
            DateFormat format;
            String[] values;

            for (BpmTaskListFilter param : taskList.getParameters()) {
                BpmTaskQueryParam property = param.getParameter();
                String value = param.getValue();
                switch (property) {
                    case ACTIVE:
                        query.active();
                        break;
                    case ACTIVITY_INSTANCE_ID_IN:
                        values = value.split(Pattern.quote(delimiter));
                        query.activityInstanceIdIn(values);
                        break;
                    case CASE_DEFINITION_ID:
                        query.caseDefinitionId(value);
                        break;
                    case CASE_DEFINITION_KEY:
                        query.caseDefinitionKey(value);
                        break;
                    case CASE_DEFINITION_NAME:
                        query.caseDefinitionName(value);
                        break;
                    case CASE_DEFINITION_NAME_LIKE:
                        query.caseDefinitionNameLike(value);
                        break;
                    case CASE_EXECUTION_ID:
                        query.caseExecutionId(value);
                        break;
                    case CASE_INSTANCE_BUSINESS_KEY:
                        query.caseInstanceBusinessKey(value);
                        break;
                    case CASE_INSTANCE_BUSINESS_KEY_LIKE:
                        query.caseInstanceBusinessKeyLike(value);
                        break;
                    case CASE_INSTANCE_ID:
                        query.caseInstanceId(value);
                        break;
                    case CASE_INSTANCE_VARIABLE_VALUE_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    CASE_INSTANCE_VARIABLE_VALUE_EQUALS.getId()));
                        } else {
                            query.caseInstanceVariableValueEquals(values[0], values[1]);
                        }
                        break;
                    case CASE_INSTANCE_VARIABLE_VALUE_GREATER_THAN:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    CASE_INSTANCE_VARIABLE_VALUE_GREATER_THAN.getId()));
                        } else {
                            query.caseInstanceVariableValueGreaterThan(values[0], values[1]);
                        }
                        break;
                    case CASE_INSTANCE_VARIABLE_VALUE_GREATER_THAN_OR_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    CASE_INSTANCE_VARIABLE_VALUE_GREATER_THAN_OR_EQUALS.getId()));
                        } else {
                            query.caseInstanceVariableValueGreaterThanOrEquals(values[0], values[1]);
                        }
                        break;
                    case CASE_INSTANCE_VARIABLE_VALUE_LESS_THAN:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    CASE_INSTANCE_VARIABLE_VALUE_LESS_THAN.getId()));
                        } else {
                            query.caseInstanceVariableValueLessThan(values[0], values[1]);
                        }
                        break;
                    case CASE_INSTANCE_VARIABLE_VALUE_LESS_THAN_OR_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    CASE_INSTANCE_VARIABLE_VALUE_LESS_THAN_OR_EQUALS.getId()));
                        } else {
                            query.caseInstanceVariableValueLessThanOrEquals(values[0], values[1]);
                        }
                        break;
                    case CASE_INSTANCE_VARIABLE_VALUE_LIKE:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    CASE_INSTANCE_VARIABLE_VALUE_LIKE.getId()));
                        } else {
                            query.caseInstanceVariableValueLike(values[0], values[1]);
                        }
                        break;
                    case CASE_INSTANCE_VARIABLE_VALUE_NOT_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    CASE_INSTANCE_VARIABLE_VALUE_NOT_EQUALS.getId()));
                        } else {
                            query.caseInstanceVariableValueNotEquals(values[0], values[1]);
                        }
                        break;
                    case DUE_AFTER:
                        format = new SimpleDateFormat();
                        query.dueAfter(format.parse(value));
                        break;
                    case DUE_AFTER_EXPRESSION:
                        query.dueAfterExpression(value);
                        break;
                    case DUE_BEFORE:
                        format = new SimpleDateFormat();
                        query.dueBefore(format.parse(value));
                        break;
                    case DUE_BEFORE_EXPRESSION:
                        query.dueBeforeExpression(value);
                        break;
                    case DUE_DATE:
                        format = new SimpleDateFormat();
                        query.dueDate(format.parse(value));
                        break;
                    case DUE_DATE_EXPRESSION:
                        query.dueDateExpression(value);
                        break;
                    case EXCLUDE_SUBTASKS:
                        query.excludeSubtasks();
                        break;
                    case EXECUTION_ID:
                        query.executionId(value);
                        break;
                    case FOLLOW_UP_AFTER:
                        format = new SimpleDateFormat();
                        query.followUpAfter(format.parse(value));
                        break;
                    case FOLLOW_UP_AFTER_EXPRESSION:
                        query.followUpAfterExpression(value);
                        break;
                    case FOLLOW_UP_BEFORE:
                        format = new SimpleDateFormat();
                        query.followUpBefore(format.parse(value));
                        break;
                    case FOLLOW_UP_BEFORE_EXPRESSION:
                        query.followUpBeforeExpression(value);
                        break;
                    case FOLLOW_UP_BEFORE_OR_NOT_EXISTENT:
                        format = new SimpleDateFormat();
                        query.followUpBeforeOrNotExistent(format.parse(value));
                        break;
                    case FOLLOW_UP_BEFORE_OR_NOT_EXISTENT_EXPRESSION:
                        query.followUpBeforeOrNotExistentExpression(value);
                        break;
                    case FOLLOW_UP_DATE:
                        format = new SimpleDateFormat();
                        query.followUpDate(format.parse(value));
                        break;
                    case FOLLOW_UP_DATE_EXPRESSION:
                        query.followUpDateExpression(value);
                        break;
                    case INITIALIZE_FORM_KEYS:
                        query.initializeFormKeys();
                        break;
                    case ORDER_BY_CASE_EXECUTION_ID:
                        query.orderByCaseExecutionId();
                        break;
                    case ORDER_BY_CASE_INSTANCE_ID:
                        query.orderByCaseInstanceId();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_DUE_DATE:
                        query.orderByDueDate();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_EXECUTION_ID:
                        query.orderByExecutionId();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_FOLLOW_UP_DATE:
                        query.orderByFollowUpDate();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_PROCESS_INSTANCE_ID:
                        query.orderByProcessInstanceId();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_TASK_ASSIGNEE:
                        query.orderByTaskAssignee();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_TASK_CREATE_TIME:
                        query.orderByTaskCreateTime();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_TASK_DESCRIPTION:
                        query.orderByTaskDescription();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_TASK_ID:
                        query.orderByTaskId();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_TASK_NAME:
                        query.orderByTaskName();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_TASK_NAME_CASE_INSENSITIVE:
                        query.orderByTaskNameCaseInsensitive();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case ORDER_BY_TASK_PRIORITY:
                        query.orderByTaskPriority();
                        if ("desc".equals(value.toLowerCase())) {
                            query.desc();
                        } else {
                            query.asc();
                        }
                        break;
                    case PROCESS_DEFINITION_ID:
                        query.processDefinitionId(value);
                        break;
                    case PROCESS_DEFINITION_KEY:
                        query.processDefinitionKey(value);
                        break;
                    case PROCESS_DEFINITION_NAME:
                        query.processDefinitionName(value);
                        break;
                    case PROCESS_DEFINITION_NAME_LIKE:
                        query.processDefinitionNameLike(value);
                        break;
                    case PROCESS_INSTANCE_BUSINESS_KEY:
                        query.processInstanceBusinessKey(value);
                        break;
                    case PROCESS_INSTANCE_BUSINESS_KEY_LIKE:
                        query.processInstanceBusinessKeyLike(value);
                        break;
                    case PROCESS_INSTANCE_ID:
                        query.processInstanceId(value);
                        break;
                    case PROCESS_VARIABLE_VALUE_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    PROCESS_VARIABLE_VALUE_EQUALS.getId()));
                        } else {
                            query.processVariableValueEquals(values[0], values[1]);
                        }
                        break;
                    case PROCESS_VARIABLE_VALUE_GREATER_THAN:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    PROCESS_VARIABLE_VALUE_GREATER_THAN.getId()));
                        } else {
                            query.processVariableValueGreaterThan(values[0], values[1]);
                        }
                        break;
                    case PROCESS_VARIABLE_VALUE_GREATER_THAN_OR_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    PROCESS_VARIABLE_VALUE_GREATER_THAN_OR_EQUALS.getId()));
                        } else {
                            query.processVariableValueGreaterThanOrEquals(values[0], values[1]);
                        }
                        break;
                    case PROCESS_VARIABLE_VALUE_LESS_THAN:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    PROCESS_VARIABLE_VALUE_LESS_THAN.getId()));
                        } else {
                            query.processVariableValueLessThan(values[0], values[1]);
                        }
                        break;
                    case PROCESS_VARIABLE_VALUE_LESS_THAN_OR_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    PROCESS_VARIABLE_VALUE_LESS_THAN_OR_EQUALS.getId()));
                        } else {
                            query.processVariableValueLessThanOrEquals(values[0], values[1]);
                        }
                        break;
                    case PROCESS_VARIABLE_VALUE_LIKE:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    PROCESS_VARIABLE_VALUE_LIKE.getId()));
                        } else {
                            query.processVariableValueLike(values[0], values[1]);
                        }
                        break;
                    case PROCESS_VARIABLE_VALUE_NOT_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    PROCESS_VARIABLE_VALUE_NOT_EQUALS.getId()));
                        } else {
                            query.processVariableValueNotEquals(values[0], values[1]);
                        }
                        break;
                    case SUSPENDED:
                        query.suspended();
                        break;
                    case TASK_ASSIGNEE:
                        query.taskAssignee(value);
                        break;
                    case TASK_ASSIGNEE_EXPRESSION:
                        query.taskAssigneeExpression(value);
                        break;
                    case TASK_ASSIGNEE_LIKE:
                        query.taskAssigneeLike(value);
                        break;
                    case TASK_ASSIGNEE_LIKE_EXPRESSION:
                        query.taskAssigneeLikeExpression(value);
                        break;
                    case TASK_CANDIDATE_GROUP:
                        query.taskCandidateGroup(value);
                        break;
                    case TASK_CANDIDATE_GROUP_EXPRESSION:
                        query.taskCandidateGroupExpression(value);
                        break;
                    case TASK_CANDIDATE_GROUP_IN:
                        values = value.split(Pattern.quote(delimiter));
                        query.taskCandidateGroupIn(Arrays.asList(values));
                        break;
                    case TASK_CANDIDATE_GROUP_IN_EXPRESSION:
                        query.taskCandidateGroupInExpression(value);
                        break;
                    case TASK_CANDIDATE_USER:
                        query.taskCandidateUser(value);
                        break;
                    case TASK_CANDIDATE_USER_EXPRESSION:
                        query.taskCandidateUserExpression(value);
                        break;
                    case TASK_CREATED_AFTER:
                        format = new SimpleDateFormat();
                        query.taskCreatedAfter(format.parse(value));
                        break;
                    case TASK_CREATED_AFTER_EXPRESSION:
                        query.taskCreatedAfterExpression(value);
                        break;
                    case TASK_CREATED_BEFORE:
                        format = new SimpleDateFormat();
                        query.taskCreatedBefore(format.parse(value));
                        break;
                    case TASK_CREATED_BEFORE_EXPRESSION:
                        query.taskCreatedBeforeExpression(value);
                        break;
                    case TASK_CREATED_ON:
                        format = new SimpleDateFormat();
                        query.taskCreatedOn(format.parse(value));
                        break;
                    case TASK_CREATED_ON_EXPRESSION:
                        query.taskCreatedOnExpression(value);
                        break;
                    case TASK_DEFINITION_KEY:
                        query.taskDefinitionKey(value);
                        break;
                    case TASK_DEFINITION_KEY_LIKE:
                        query.taskDefinitionKeyLike(value);
                        break;
                    case TASK_DELEGATION_STATE:
                        query.taskDelegationState(DelegationState.valueOf(value));
                        break;
                    case TASK_DESCRIPTION:
                        query.taskDescription(value);
                        break;
                    case TASK_DESCRIPTION_LIKE:
                        query.taskDescriptionLike(value);
                        break;
                    case TASK_ID:
                        query.taskId(value);
                        break;
                    case TASK_INVOLVED_USER:
                        query.taskInvolvedUser(value);
                        break;
                    case TASK_INVOLVED_USER_EXPRESSION:
                        query.taskInvolvedUserExpression(value);
                        break;
                    case TASK_MAX_PRIORITY:
                        query.taskMaxPriority(Integer.valueOf(value));
                        break;
                    case TASK_MIN_PRIORITY:
                        query.taskMinPriority(Integer.valueOf(value));
                        break;
                    case TASK_NAME:
                        query.taskName(value);
                        break;
                    case TASK_NAME_LIKE:
                        query.taskNameLike(value);
                        break;
                    case TASK_OWNER:
                        query.taskOwner(value);
                        break;
                    case TASK_OWNER_EXPRESSION:
                        query.taskOwnerExpression(value);
                        break;
                    case TASK_PRIORITY:
                        query.taskPriority(Integer.valueOf(value));
                        break;
                    case TASK_UNASSIGNED:
                        query.taskUnassigned();
                        break;
                    case TASK_VARIABLE_VALUE_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    TASK_VARIABLE_VALUE_EQUALS.getId()));
                        } else {
                            query.taskVariableValueEquals(values[0], values[1]);
                        }
                        break;
                    case TASK_VARIABLE_VALUE_GREATER_THAN:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    TASK_VARIABLE_VALUE_GREATER_THAN.getId()));
                        } else {
                            query.taskVariableValueGreaterThan(values[0], values[1]);
                        }
                        break;
                    case TASK_VARIABLE_VALUE_GREATER_THAN_OR_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    TASK_VARIABLE_VALUE_GREATER_THAN_OR_EQUALS.getId()));
                        } else {
                            query.taskVariableValueGreaterThanOrEquals(values[0], values[1]);
                        }
                        break;
                    case TASK_VARIABLE_VALUE_LESS_THAN:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    TASK_VARIABLE_VALUE_LESS_THAN.getId()));
                        } else {
                            query.taskVariableValueLessThan(values[0], values[1]);
                        }
                        break;
                    case TASK_VARIABLE_VALUE_LESS_THAN_OR_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    TASK_VARIABLE_VALUE_LESS_THAN_OR_EQUALS.getId()));
                        } else {
                            query.taskVariableValueLessThanOrEquals(values[0], values[1]);
                        }
                        break;
                    case TASK_VARIABLE_VALUE_LIKE:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    TASK_VARIABLE_VALUE_LIKE.getId()));
                        } else {
                            query.taskVariableValueLike(values[0], values[1]);
                        }
                        break;
                    case TASK_VARIABLE_VALUE_NOT_EQUALS:
                        values = value.split(Pattern.quote(delimiter));
                        if (values.length != 2) {
                            throw new BpmTaskListException(String.format("Bpm task list [%s] error, property %s",
                                    taskList.getName(),
                                    TASK_VARIABLE_VALUE_NOT_EQUALS.getId()));
                        } else {
                            query.taskVariableValueNotEquals(values[0], values[1]);
                        }
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BpmTaskListException(String.format("Bpm task list [%s] error", taskList.getName()));
        }
    }

}
