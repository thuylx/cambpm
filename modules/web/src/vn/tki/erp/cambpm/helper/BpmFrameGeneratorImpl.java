package vn.tki.erp.cambpm.helper;

import com.google.common.base.Strings;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Component.Container;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.annotation.EnableBpmFrame;
import vn.tki.erp.cambpm.entity.BpmProcessDefinition;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.query.BpmHistoricProcessInstanceQuery;
import vn.tki.erp.cambpm.query.BpmTaskQuery;
import vn.tki.erp.cambpm.service.BpmTaskService;
import vn.tki.erp.cambpm.web.bpmprocessdefinition.BpmProcessDefinitionStart;
import vn.tki.erp.cambpm.web.bpmprocessinstance.BpmProcessInstanceBrowse;
import vn.tki.erp.cambpm.web.bpmtask.BpmTaskComplete;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Component
public class BpmFrameGeneratorImpl implements BpmFrameGenerator {
    protected static final String TASK_COMPONENT_ID_PREFIX = "taskButton";
    protected static final String PROCESS_DEFINITION_COMPONENT_ID_PREFIX = "processDefinitionButton";

    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private Messages messages;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private EntityLoadInfoBuilder loadInfoBuilder;
    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;
    @Inject
    private BpmTaskService bpmTaskService;

    @Override
    public com.haulmont.cuba.gui.components.Component createTaskComponent(BpmTask task, boolean isSelected) {
        Button button = componentsFactory.createComponent(Button.class);
        button.setCaption(task.getName());
        button.setDescription(messages.formatMainMessage("taskButton.Description", task.getName()));
        if (isSelected) {
            button.setStyleName("icon-align-right primary");
        } else {
            button.setStyleName("icon-align-right");
        }
        if (userSessionSource.getUserSession().getCurrentOrSubstitutedUser().equals(task.getAssignee())) {
            button.setIcon("font-icon:QUESTION_CIRCLE");
        } else {
            button.setEnabled(bpmTaskService.checkClaimable(
                    userSessionSource.getUserSession().getCurrentOrSubstitutedUser().getLogin(),
                    userSessionSource.getUserSession().getRoles(),
                    task.getId()));
        }
        button.setId(TASK_COMPONENT_ID_PREFIX + task.getId());

        return button;
    }

    @Override
    public void postTaskComplete(Window window, BpmTask task) {
        Button button = (Button) window.getComponent(TASK_COMPONENT_ID_PREFIX + task.getId());
        button.setIcon("font-icon:CHECK");
        button.setEnabled(false);
    }

    @Override
    public BaseAction createTaskAction(Window window, BpmTask task, Entity entity) {
        BaseAction action = new BaseAction("taskAction" + task.getId());
        action.withHandler(actionPerformedEvent -> {
            //Open generic taskForm
            BpmTaskComplete genericTaskForm = (BpmTaskComplete) window.openWindow(BpmTaskComplete.ID, WindowManager.OpenType.THIS_TAB, ParamsMap.of(
                    BpmTaskComplete.WinParam.BPM_TASK_PARAM_NAME, task));
            genericTaskForm.setId(String.format("taskForm%s", task.getId()));
            genericTaskForm.addCloseWithCommitListener(() -> {
                postTaskComplete(window, task);
                window.getDsContext().refresh();
            });
        });
        return action;
    }

    @Override
    public com.haulmont.cuba.gui.components.Component createProcessDefinitionComponent(BpmProcessDefinition processDefinition, boolean isActive) {
        Button button = componentsFactory.createComponent(Button.class);
        button.setCaption(messages.formatMainMessage("processDefinitionButton.Caption", processDefinition.getName()));
        button.setDescription(messages.formatMainMessage("processDefinitionButton.Description", processDefinition.getName()));
        button.setIcon("font-icon:COG");
        button.setId(PROCESS_DEFINITION_COMPONENT_ID_PREFIX + processDefinition.getId());
        if (!isActive) {
            button.addStyleName("quiet");
        }

        button.setEnabled(processDefinition.getDeployed());

        return button;
    }

    @Override
    public void postProcessDefinitionStart(Window window, BpmProcessDefinition processDefinition) {
        Button button = (Button) window.getComponent(PROCESS_DEFINITION_COMPONENT_ID_PREFIX + processDefinition.getId());
        button.addStyleName("quiet");
    }

    @Override
    public BaseAction createProcessDefinitionAction(Window window, BpmProcessDefinition processDefinition, Entity entity) {
        BaseAction action = new BaseAction("processDefinitionAction" + processDefinition.getKey());
        action.withHandler(actionPerformedEvent -> {
            if (processDefinition.getDeployed()) {
                BpmProcessDefinitionStart startForm = (BpmProcessDefinitionStart) window.openWindow(BpmProcessDefinitionStart.ID, WindowManager.OpenType.THIS_TAB, ParamsMap.of(
                        BpmProcessDefinitionStart.WinParam.BPM_PROCESS_DEFINITION_PARAM_NAME, processDefinition,
                        BpmProcessDefinitionStart.WinParam.ENTITY_PARAM_NAME, entity
                ));
                startForm.addCloseWithCommitListener(() -> postProcessDefinitionStart(window, processDefinition));
            }
        });
        return action;
    }

    @Override
    public com.haulmont.cuba.gui.components.Component createShowAllProcessInstancesComponent(Window window, Entity entity) {
        Button button = componentsFactory.createComponent(Button.class);
        button.setCaption(messages.getMainMessage("showInstanceButton.Caption"));
        button.setIcon("font-icon:COGS");
        button.setId("showAllProcessInstances");
        button.addStyleName("quiet");

        BaseAction action = new BaseAction("showAllRunningProcesses");
        action.withHandler(actionPerformedEvent -> window.openWindow(BpmProcessInstanceBrowse.ID, WindowManager.OpenType.DIALOG, ParamsMap.of(
                BpmProcessInstanceBrowse.WinParam.ENTITY_PARAM_NAME, entity
        )));
        button.setAction(action);

        return button;
    }

    @Override
    public com.haulmont.cuba.gui.components.Component createRefreshTasksComponent() {
        Button button = componentsFactory.createComponent(Button.class);
        button.setCaption(messages.getMainMessage("refreshTasksButton.Caption"));
        button.setIcon("font-icon:REFRESH");
        button.setId("refreshTasks");
        button.addStyleName("quiet");
        return button;
    }

    @Override
    public void loadTaskComponents(Window window, String containerId, UUID selectedTaskId, Entity entity) {
        BpmTaskQuery taskQuery = new BpmTaskQuery();
        EntityLoadInfo loadInfo = loadInfoBuilder.create(entity);

        List<BpmTask> bpmTasks = taskQuery.processInstanceBusinessKey(loadInfo.toString())
//                .or()
//                .taskAssignee(userSessionSource.getUserSession().getCurrentOrSubstitutedUser().getLogin())
//                .taskCandidateGroupIn((List<String>) userSessionSource.getUserSession().getRoles())
//                .includeAssignedTasks()
//                .endOr()
                .orderByTaskCreateTime()
                .asc()
                .list();

        Container container = (Container) window.getComponent(containerId);
        for (BpmTask task : bpmTasks) {
            boolean isSelected = task.getId().equals(selectedTaskId);
            com.haulmont.cuba.gui.components.Component component = createTaskComponent(task, isSelected);
            BaseAction action = createTaskAction(window, task, entity);
            ((Button) component).setAction(action);
            container.add(component);
        }
    }

    @Override
    public void loadProcessDefinitionComponents(Window window, String containerId, Entity entity) {
        Container container = (Container) window.getComponent(containerId);

        String entityName = metadata.getTools().getEntityName(entity.getClass());

        List<BpmProcessDefinition> processDefinitions = dataManager.load(BpmProcessDefinition.class)
                .query("select c from cambpm$BpmProcessDefinition c join c.entities e where (e.entityName = :entityName or e.entityName = :all) and c.active = TRUE")
                .view("bpmProcessDefinition-view")
                .parameter("entityName", entityName)
                .parameter("all", "*")
                .list();

        for (BpmProcessDefinition processDefinition : processDefinitions) {

            BpmHistoricProcessInstanceQuery processInstanceQuery = new BpmHistoricProcessInstanceQuery();
            EntityLoadInfo loadInfo = loadInfoBuilder.create(entity);
            processInstanceQuery.processInstanceBusinessKey(loadInfo.toString());
            processInstanceQuery.processDefinitionKey(processDefinition.getKey());
            boolean isActive = processInstanceQuery.count() == 0;

            com.haulmont.cuba.gui.components.Component component = createProcessDefinitionComponent(processDefinition, isActive);
            container.add(component);
            BaseAction action = createProcessDefinitionAction(window, processDefinition, entity);
            ((Button) component).setAction(action);
        }
    }

    @Override
    public void loadRunningProcessesComponent(Window window, String containerId, Entity entity) {
        Container container = (Container) window.getComponent(containerId);
        com.haulmont.cuba.gui.components.Component component = createShowAllProcessInstancesComponent(window, entity);
        container.add(component);
    }

    @Override
    public void loadTaskRefreshComponent(EnableBpmFrame annotation, Window window, Entity entity, UUID selectedTaskId) {
        Container container = (Container) window.getComponent(annotation.taskRefreshActionContainerId());
        com.haulmont.cuba.gui.components.Component component = createRefreshTasksComponent();
        ((Button) component).setAction(new BaseAction("refreshPendingTasks")
                .withHandler(actionPerformedEvent -> {
                    reloadBpmComponents(annotation, window, entity, selectedTaskId);
                }));
        container.add(component);
    }

    @Override
    public void loadBpmComponents(EnableBpmFrame annotation, Window window, Entity entity, UUID selectedTaskId) {
        if (!Strings.isNullOrEmpty(annotation.taskActionContainerId())) {
            loadTaskComponents(window, annotation.taskActionContainerId(), selectedTaskId, entity);
        }

        if (!Strings.isNullOrEmpty(annotation.processDefinitionActionContainerId())) {
            loadProcessDefinitionComponents(window, annotation.processDefinitionActionContainerId(), entity);
        }

        if (!Strings.isNullOrEmpty(annotation.runningProcessActionContainerId())) {
            loadRunningProcessesComponent(window, annotation.runningProcessActionContainerId(), entity);
        }

        if (!Strings.isNullOrEmpty(annotation.taskRefreshActionContainerId())) {
            loadTaskRefreshComponent(annotation, window, entity, selectedTaskId);
        }
    }

    @Override
    public void reloadBpmComponents(EnableBpmFrame annotation, Window window, Entity entity, UUID selectedTaskId) {
        if (!Strings.isNullOrEmpty(annotation.taskActionContainerId())) {
            Container container = (Container) window.getComponent(annotation.taskActionContainerId());
            //Remove task components
            container.removeAll();

            loadTaskComponents(window, annotation.taskActionContainerId(), selectedTaskId, entity);

            if (annotation.taskActionContainerId().equals(annotation.processDefinitionActionContainerId())) {
                loadProcessDefinitionComponents(window, annotation.processDefinitionActionContainerId(), entity);
            }

            if (annotation.taskActionContainerId().equals(annotation.runningProcessActionContainerId())) {
                loadRunningProcessesComponent(window, annotation.runningProcessActionContainerId(), entity);
            }

            if (annotation.taskActionContainerId().equals(annotation.taskRefreshActionContainerId())) {
                loadTaskRefreshComponent(annotation, window, entity, selectedTaskId);
            }
        }
    }
}
