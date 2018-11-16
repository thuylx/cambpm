package vn.tki.erp.cambpm.helper;

import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.UserOperationLogEntry;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import vn.tki.erp.cambpm.entity.*;

public interface BpmEntityConverter {
    String NAME = "cambpm_BpmEntityConverter";

    BpmTask toBpmTask(Task task);

    BpmTask toBpmTask(HistoricTaskInstance task);

    BpmProcessDefinitionVersion toBpmProcessDefinitionVersion(ProcessDefinition processDefinition);

    BpmProcessDefinition toBpmProcessDefinition(ProcessDefinition processDefinition);

    BpmProcessInstance toBpmProcessInstance(ProcessInstance processInstance);

    BpmProcessInstance toBpmProcessInstance(HistoricProcessInstance processInstance);

    BpmUserOperationLogEntry toBpmUserOperationLogEntry(UserOperationLogEntry entry);
}
