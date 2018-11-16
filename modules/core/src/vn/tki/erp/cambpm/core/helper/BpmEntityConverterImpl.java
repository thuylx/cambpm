package vn.tki.erp.cambpm.core.helper;

import com.haulmont.cuba.core.global.Metadata;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.UserOperationLogEntry;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.entity.*;
import vn.tki.erp.cambpm.helper.BpmEntityConverter;
import vn.tki.erp.cambpm.helper.UserHelper;
import vn.tki.erp.cambpm.util.TimeConverter;

import javax.inject.Inject;
import java.util.UUID;

@Component(BpmEntityConverter.NAME)
public class BpmEntityConverterImpl implements BpmEntityConverter {

    @Inject
    private Metadata metadata;
    @Inject
    private UserHelper userHelper;

    @Override
    public BpmTask toBpmTask(Task task) {
        BpmTask bpmTask = metadata.create(BpmTask.class);
        bpmTask.setId(UUID.fromString(task.getId()));
        bpmTask.setName(task.getName());
        bpmTask.setAssignee(userHelper.getUserByLoginName(task.getAssignee()));
        bpmTask.setDescription(task.getDescription());
        bpmTask.setPriority(task.getPriority());
        bpmTask.setStartTime(task.getCreateTime());
        bpmTask.setDueDate(task.getDueDate());
        bpmTask.setFollowUpDate(task.getFollowUpDate());
//        bpmTask.setFormKey(task.getFormKey());
        bpmTask.setTaskDefinitionKey(task.getTaskDefinitionKey());
        bpmTask.setOwner(userHelper.getUserByLoginName(task.getOwner() == null ? task.getAssignee() : task.getOwner()));

        //Process definition
        bpmTask.setProcessDefinitionVersionId(task.getProcessDefinitionId());

        //Process instance
        bpmTask.setProcessInstanceId(UUID.fromString(task.getProcessInstanceId()));

        return bpmTask;
    }

    @Override
    public BpmTask toBpmTask(HistoricTaskInstance task) {
        BpmTask bpmTask = metadata.create(BpmTask.class);
        bpmTask.setId(UUID.fromString(task.getId()));
        bpmTask.setName(task.getName());
        bpmTask.setAssignee(userHelper.getUserByLoginName(task.getAssignee()));
        bpmTask.setOwner(userHelper.getUserByLoginName(task.getOwner() == null?task.getAssignee():task.getOwner()));
        bpmTask.setDescription(task.getDescription());
        bpmTask.setPriority(task.getPriority());
        bpmTask.setStartTime(task.getStartTime());
        bpmTask.setDueDate(task.getDueDate());
        bpmTask.setFollowUpDate(task.getFollowUpDate());
        bpmTask.setEndTime(task.getEndTime());
        bpmTask.setDuration(TimeConverter.millisecondToDurationString(task.getDurationInMillis()));

        //Process definition
        bpmTask.setProcessDefinitionVersionId(task.getProcessDefinitionId());

        //Process instance
        bpmTask.setProcessInstanceId(UUID.fromString(task.getProcessInstanceId()));

        return bpmTask;
    }

    @Override
    public BpmProcessDefinitionVersion toBpmProcessDefinitionVersion(ProcessDefinition processDefinition) {
        BpmProcessDefinitionVersion bpmProcessDefinition = metadata.create(BpmProcessDefinitionVersion.class);
        bpmProcessDefinition.setId(processDefinition.getId());
        bpmProcessDefinition.setDescription(processDefinition.getDescription());
        bpmProcessDefinition.setProcessDefinitionKey(processDefinition.getKey());
        bpmProcessDefinition.setName(processDefinition.getName());
        bpmProcessDefinition.setVersion(processDefinition.getVersion());
        bpmProcessDefinition.setVersionTag(processDefinition.getVersionTag());
        return bpmProcessDefinition;
    }

    @Override
    public BpmProcessDefinition toBpmProcessDefinition(ProcessDefinition processDefinition) {
        BpmProcessDefinition bpmProcessDefinition = metadata.create(BpmProcessDefinition.class);
        bpmProcessDefinition.setKey(processDefinition.getKey());
        bpmProcessDefinition.setName(processDefinition.getName());
        bpmProcessDefinition.setDescription(processDefinition.getDescription());
        return bpmProcessDefinition;
    }

    @Override
    public BpmProcessInstance toBpmProcessInstance(ProcessInstance processInstance) {
        BpmProcessInstance bpmProcessInstance = metadata.create(BpmProcessInstance.class);
        bpmProcessInstance.setId(UUID.fromString(processInstance.getId()));
        bpmProcessInstance.setBusinessKey(processInstance.getBusinessKey());
        //Process definition
        bpmProcessInstance.setProcessDefinitionVersionId(processInstance.getProcessDefinitionId());

        return bpmProcessInstance;
    }

    @Override
    public BpmProcessInstance toBpmProcessInstance(HistoricProcessInstance processInstance) {
        BpmProcessInstance bpmProcessInstance = metadata.create(BpmProcessInstance.class);
        bpmProcessInstance.setId(UUID.fromString(processInstance.getId()));
        bpmProcessInstance.setBusinessKey(processInstance.getBusinessKey());
        bpmProcessInstance.setStartTime(processInstance.getStartTime());
        bpmProcessInstance.setEndTime(processInstance.getEndTime());
        bpmProcessInstance.setDuration(TimeConverter.millisecondToDurationString(processInstance.getDurationInMillis()));
        bpmProcessInstance.setStartedBy(userHelper.getUserByLoginName(processInstance.getStartUserId()));
        bpmProcessInstance.setState(processInstance.getState());

        //Process definition
        bpmProcessInstance.setProcessDefinitionVersionId(processInstance.getProcessDefinitionId());
        BpmProcessDefinitionVersion processDefinition = metadata.create(BpmProcessDefinitionVersion.class);
        processDefinition.setId(processInstance.getProcessDefinitionId());
        processDefinition.setName(processInstance.getProcessDefinitionName());
        processDefinition.setProcessDefinitionKey(processInstance.getProcessDefinitionKey());
        processDefinition.setVersion(processInstance.getProcessDefinitionVersion());
        bpmProcessInstance.setBpmProcessDefinitionVersion(processDefinition);

        return bpmProcessInstance;
    }

    @Override
    public BpmUserOperationLogEntry toBpmUserOperationLogEntry(UserOperationLogEntry entry) {
        BpmUserOperationLogEntry logEntry = metadata.create(BpmUserOperationLogEntry.class);
        logEntry.setId(UUID.fromString(entry.getId()));
        logEntry.setOperationType(entry.getOperationType());
        logEntry.setChangedProperty(entry.getProperty());
        logEntry.setOldPropertyValue(entry.getOrgValue());
        logEntry.setNewPropertyValue(entry.getNewValue());
        logEntry.setUser(userHelper.getUserByLoginName(entry.getUserId()));
        logEntry.setTime(entry.getTimestamp());
        return logEntry;
    }
}
