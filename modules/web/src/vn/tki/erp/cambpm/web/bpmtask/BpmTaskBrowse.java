package vn.tki.erp.cambpm.web.bpmtask;

import com.google.common.base.Strings;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.global.UserSession;
import vn.tki.erp.cambpm.config.BpmConfig;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.entity.BpmTaskList;
import vn.tki.erp.cambpm.helper.BpmFormHelper;
import vn.tki.erp.cambpm.model.BpmForm;
import vn.tki.erp.cambpm.query.BpmTaskQuery;
import vn.tki.erp.cambpm.service.BpmFormService;
import vn.tki.erp.cambpm.service.BpmTaskService;
import vn.tki.erp.cambpm.web.config.BpmTaskFormParam;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.UUID;

public class BpmTaskBrowse extends AbstractLookup {
    @Inject
    private BpmTaskDatasource bpmTasksDs;
    @Named("taskListsTable")
    private Table<BpmTaskList> filtersTable;
    @Named("bpmTasksTable")
    private Table<BpmTask> bpmTaskGroupTable;
    @Named("bpmTasksTable.openTaskForm")
    private Action openTaskFormAction;
    @Named("claimBtn")
    private Button claimBtn;
    @Inject
    private BpmFormService formService;
    @Inject
    private BpmTaskService taskService;
    @Inject
    private UserSession userSession;
    @Inject
    private ComponentsFactory componentsFactory;
    @Named("bpmTaskListsDs")
    private CollectionDatasource<BpmTaskList, UUID> bpmTaskListsDs;
    @Inject
    private Configuration configuration;
    @Inject
    private BpmFormHelper formHelper;

    @Override
    public void init(Map<String, Object> params) {
        bpmTaskGroupTable.setItemClickAction(openTaskFormAction);

        bpmTasksDs.addItemChangeListener(e -> claimBtn.setEnabled(checkClaimable(e.getItem())));
        bpmTaskListsDs.addItemChangeListener(this::listChanged);

        BpmConfig bpmConfig = configuration.getConfig(BpmConfig.class);
        bpmTasksDs.setMaxResults(bpmConfig.getMaxResults());
    }

    @Override
    public void ready() {
        filtersTable.setSelected(bpmTaskListsDs.getItems().isEmpty() ? null : bpmTaskListsDs.getItems().iterator().next());
    }

    private void listChanged(Datasource.ItemChangeEvent<BpmTaskList> event) {
        BpmTaskQuery taskQuery = new BpmTaskQuery();
        taskQuery.setTaskListId(event.getItem().getId());
        bpmTasksDs.setBpmTaskQuery(taskQuery);
        bpmTasksDs.refresh();
    }


    public void onOpenTaskForm(Component source) {
        BpmTask bpmTask = bpmTasksDs.getItem();
        BpmForm bpmForm = formService.getTaskForm(bpmTask.getId());
        formHelper.standardizeFormName(bpmForm, bpmTask);

        Window controller;
        //Empty formName or embeddable, open task complete form
        if (Strings.isNullOrEmpty(bpmForm.getFormName()) || bpmForm.isEmbeddable()) {
            //Open generic taskForm
            controller = openGenericTaskForm(bpmTask, bpmForm);
        } else if (BpmForm.ScreenType.EDITOR.equals(bpmForm.getScreenType())) {
            //Open an editor
            controller = openEditorScreen(bpmTask, bpmForm);
        } else {
            //openWindow
            controller = openWindowScreen(bpmTask, bpmForm);
        }

        if (controller != null)
            controller.addCloseWithCommitListener(() -> bpmTasksDs.refresh());
    }

    private Window openGenericTaskForm(BpmTask bpmTask, BpmForm bpmForm) {
        WindowManager.OpenType openType = WindowManager.OpenType.valueOf(configuration.getConfig(BpmConfig.class).getDefaultTaskFormOpenType());
        return openWindow(BpmTaskComplete.ID, openType, ParamsMap.of(
                BpmTaskComplete.WinParam.BPM_TASK_PARAM_NAME, bpmTask,
                BpmTaskComplete.WinParam.BPM_FORM_PARAM_NAME, bpmForm));
    }

    private Window openEditorScreen(BpmTask bpmTask, BpmForm bpmForm) {
        Entity entity = bpmTask.getEntity();
        if (entity != null)
            return openEditor(bpmForm.getFormName(), entity, WindowManager.OpenType.valueOf(bpmForm.getOpenType()), ParamsMap.of(
                    BpmTaskFormParam.BPM_TASK_PARAM_NAME, bpmTask));
        else {
            showNotification(getMessage("entityNotFoundNotification"), NotificationType.ERROR);
            return null;
        }
    }

    private Window openWindowScreen(BpmTask bpmTask, BpmForm bpmForm){
        return openWindow(bpmForm.getFormName(), WindowManager.OpenType.valueOf(bpmForm.getOpenType()), ParamsMap.of(
                BpmTaskFormParam.BPM_TASK_PARAM_NAME, bpmTask));
    }

    public void onClaim(Component source) {
        //check if allowed
        String userLoginName = userSession.getCurrentOrSubstitutedUser().getLogin();
        taskService.claimTask(bpmTasksDs.getItem().getId(), userLoginName);
        showNotification(getMessage("taskClaimedNotification"), NotificationType.TRAY);
        bpmTasksDs.refresh();
    }

    private Boolean checkClaimable(BpmTask bpmTask) {
        if (bpmTask == null || bpmTask.getAssignee() != null) {
            return false;
        }

        return taskService.checkClaimable(userSession.getUser().getLogin(), userSession.getRoles(), bpmTask.getId());
    }

    public Component generateIsClaimableCell(BpmTask bpmTask) {
        LinkButton linkButton = componentsFactory.createComponent(LinkButton.class);
        linkButton.setId("flag:" + bpmTask.getId().toString());
        linkButton.setFocusable(false);

        if (userSession.getCurrentOrSubstitutedUser().equals(bpmTask.getAssignee())) {
            linkButton.setIcon("font-icon:FLAG_O");
            return linkButton;
        }

        if (checkClaimable(bpmTask)) {
            linkButton.setIcon("font-icon:GROUP");
            return linkButton;
        }

        linkButton.setIcon("font-icon:ANGLE_DOUBLE_RIGHT");
        return linkButton;
    }

    public void onManage(Component source) {
        openWindow("cambpm$BpmTaskList.browse", WindowManager.OpenType.DIALOG)
                .addCloseListener(actionId -> bpmTaskListsDs.refresh());
    }

    public void onOpenProcessInstance(Component source) {
        BpmTask bpmTask = bpmTasksDs.getItem();
        openEditor(bpmTask.getProcessInstance(), WindowManager.OpenType.DIALOG);
    }
}