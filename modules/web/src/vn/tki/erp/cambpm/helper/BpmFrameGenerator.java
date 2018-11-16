package vn.tki.erp.cambpm.helper;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import vn.tki.erp.cambpm.annotation.EnableBpmFrame;
import vn.tki.erp.cambpm.entity.BpmProcessDefinition;
import vn.tki.erp.cambpm.entity.BpmTask;

import java.util.UUID;

public interface BpmFrameGenerator {
    String NAME = "cambpm_BpmFrameGenerator";

    Component createTaskComponent(BpmTask task, boolean isSelected);

    void postTaskComplete(Window window, BpmTask task);

    BaseAction createTaskAction(Window window, BpmTask task, Entity entity);

    Component createProcessDefinitionComponent(BpmProcessDefinition processDefinition, boolean isActive);

    void postProcessDefinitionStart(Window window, BpmProcessDefinition processDefinition);

    BaseAction createProcessDefinitionAction(Window window, BpmProcessDefinition processDefinition, Entity entity);

    Component createShowAllProcessInstancesComponent(Window window, Entity entity);

    Component createRefreshTasksComponent();

    void loadTaskComponents(Window window, String containerId, UUID selectedTaskId, Entity entity);

    void loadProcessDefinitionComponents(Window window, String containerId, Entity entity);

    void loadRunningProcessesComponent(Window window, String containerId, Entity entity);

    void loadTaskRefreshComponent(EnableBpmFrame annotation, Window window, Entity entity, UUID selectedTaskId);

    void loadBpmComponents(EnableBpmFrame annotation, Window window, Entity entity, UUID selectedTaskId);

    void reloadBpmComponents(EnableBpmFrame annotation, Window window, Entity entity, UUID selectedTaskId);
}
