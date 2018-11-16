package vn.tki.erp.cambpm.web.bpmtask;

import com.google.common.base.Strings;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.MessageTools;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.config.WindowConfig;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.security.entity.EntityAttrAccess;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.helper.BpmFormHelper;
import vn.tki.erp.cambpm.model.BpmForm;
import vn.tki.erp.cambpm.service.BpmFormService;
import vn.tki.erp.cambpm.service.BpmRepositoryService;
import vn.tki.erp.cambpm.service.BpmTaskService;
import vn.tki.erp.cambpm.web.config.BpmTaskFormParam;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

import static vn.tki.erp.cambpm.annotation.EnableBpmFrame.DISABLED_FLAG_PARAM_NAME;

public class BpmTaskComplete extends AbstractWindow {
    public static final String ID = "cambpm$BpmTask.complete";

    public interface WinParam {
        String BPM_TASK_PARAM_NAME = "bpmTask";
        String BPM_FORM_PARAM_NAME = "bpmForm";
    }

    @WindowParam(name = WinParam.BPM_TASK_PARAM_NAME)
    private BpmTask bpmTask;
    @WindowParam(name = WinParam.BPM_FORM_PARAM_NAME)
    private BpmForm bpmForm;

    private Frame embeddedTaskFrame;

    @Inject
    private BpmFormService formService;
    @Inject
    private BpmTaskService taskService;
    @Inject
    private BpmRepositoryService repositoryService;
    @Inject
    private Datasource<BpmTask> bpmTaskDs;
    @Inject
    private CollectionDatasource<User, UUID> usersDs;
    @Inject
    private FlowBoxLayout assigneeEditBox;
    @Inject
    private Label assigneeLbl;
    @Inject
    private FlowBoxLayout assigneeViewBox;
    @Inject
    private Button claimBtn;
    @Inject
    private Button completeBtn;
    @Inject
    private LinkButton delegateBtn;
    @Inject
    private Label descriptionLbl;
    @Inject
    private Button openEntityBtn;
    @Inject
    private VBoxLayout taskFormBox;
    @Inject
    private Label taskNameLbl;
    @Inject
    private Button unClaimBtn;
    @Inject
    private WindowConfig windowConfig;
    @Inject
    private UserSession userSession;
    @Inject
    private Security security;
    @Inject
    private MessageTools messageTools;
    @Inject
    private BpmFormHelper formHelper;

    @Inject
    private TabSheet mainTabSheet;
    // create logger
    private final Logger log = LoggerFactory.getLogger(BpmTaskComplete.class);

    public void setBpmTask(BpmTask bpmTask) {
        this.bpmTask = bpmTask;
    }

    public void setBpmForm(BpmForm bpmForm) {
        this.bpmForm = bpmForm;
    }

    @Override
    public void init(Map<String, Object> params) {
        if (log.isDebugEnabled()) log.debug("Initializing tasksSheet tab for task " + bpmTask.getId().toString());
        if (bpmTask == null) {
            if (log.isErrorEnabled()) log.error("BpmTaskFrame is initialized without BpmTask instance");
            return;
        }
        if (bpmForm == null)
            bpmForm = formService.getTaskForm(bpmTask.getId());
        formHelper.standardizeFormName(bpmForm, bpmTask);

        bpmTaskDs.setItem(bpmTask);

        initGeneralTaskFormComponent();
        initTaskFormFrame();

        mainTabSheet.addSelectedTabChangeListener(event -> {
            if ("diagramTab".equals(event.getSelectedTab().getName())) {
                initDiagramTab();
            }
        });
    }

    //init diagram
    private void initDiagramTab() {
        //Load bpmn viewer
        VBoxLayout diagramBox = (VBoxLayout) getComponentNN("diagramBox");
        if (diagramBox.isEnabled()) {
            //initialized already
            return;
        }

        String[] highlighted = {bpmTask.getTaskDefinitionKey()};
        String xmlDiagram = repositoryService.getProcessDefinitionDiagram(bpmTask.getProcessDefinitionVersionId());
        formHelper.initBpmnViewer(diagramBox, xmlDiagram, highlighted);
        //mark as initialized
        diagramBox.setEnabled(true);
    }

    private void initGeneralTaskFormComponent() {
        if (!Strings.isNullOrEmpty(bpmTask.getBpmProcessDefinitionVersion().getName())) {
            taskNameLbl.setValue(bpmTask.getName() + " (" + bpmTask.getBpmProcessDefinitionVersion().getName() + ")");
        } else {
            taskNameLbl.setValue(bpmTask.getName());
        }
        taskNameLbl.setVisible(!Strings.isNullOrEmpty(bpmTask.getName()));
        descriptionLbl.setVisible(!Strings.isNullOrEmpty(bpmTask.getDescription()));
        if (bpmTask.getEntity() != null) {
            openEntityBtn.setCaption(formatMessage("openEntityCaption", messageTools.getEntityCaption(bpmTask.getEntity().getMetaClass())));
        } else {
            openEntityBtn.setVisible(false);
        }

        assigneeViewBox.setVisible(true);
        if (bpmTask.getAssignee() == null) {
            assigneeLbl.setValue(getMessage("taskNotAssignedLabel"));
        } else {
            assigneeLbl.setValue(getMessage("assigneeCaption") + " " + bpmTask.getAssignee().getInstanceName());
        }
        delegateBtn.setEnabled(security.isEntityAttrPermitted(BpmTask.class, "assignee", EntityAttrAccess.MODIFY));
        assigneeEditBox.setVisible(false);

        boolean taskAssigned = checkAssignee();
        taskFormBox.setEnabled(taskAssigned);
        completeBtn.setEnabled(taskAssigned);
        claimBtn.setVisible(!taskAssigned);
        unClaimBtn.setVisible(taskAssigned);
        if (!taskAssigned) {
            claimBtn.setEnabled(checkClaimable());
        }
    }

    private void initTaskFormFrame() {
        if (Strings.isNullOrEmpty(bpmForm.getFormName()) ||
                (bpmTask.getEntity() != null && bpmForm.getFormName().equals(windowConfig.getEditorScreen(bpmTask.getEntity()).getId())) ||
                bpmForm.getFormName().toUpperCase().equals(BpmForm.ScreenType.EDITOR.toString())) {
            //Generate form
            embeddedTaskFrame = openFrame(taskFormBox, BpmTaskGenericFrame.ID, ParamsMap.of(
                    WinParam.BPM_TASK_PARAM_NAME, bpmTask,
                    WinParam.BPM_FORM_PARAM_NAME, bpmForm
            ));
        } else if (bpmForm.isEmbeddable()) {
            //Embedded form
            embeddedTaskFrame = openFrame(taskFormBox, bpmForm.getFormName(), ParamsMap.of(
                    WinParam.BPM_TASK_PARAM_NAME, bpmTask));
        } else {
            //External task form
            embeddedTaskFrame = openFrame(taskFormBox, BpmTaskExternalFrame.ID, ParamsMap.of(
                    WinParam.BPM_TASK_PARAM_NAME, bpmTask,
                    WinParam.BPM_FORM_PARAM_NAME, bpmForm
            ));
        }
    }

    public void onCompleteBtnClick() {
        if (!this.validateAll()) {
            return;
        }

        showOptionDialog(
                getMessage("confirmCompleteTaskDialog.title"),
                formatMessage("confirmCompleteTaskDialog.msg", bpmTask.getName()),
                MessageType.CONFIRMATION,
                new Action[]{
                        new DialogAction(DialogAction.Type.YES) {
                            public void actionPerform(Component component) {
                                completeTask();
                            }
                        },
                        new DialogAction(DialogAction.Type.NO)
                }
        );
    }

    private void completeTask() {
        try {
            ((BpmTaskAbstractFrame) embeddedTaskFrame).onCompleteTask();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> processVariables = ((BpmTaskAbstractFrame) embeddedTaskFrame).getProcessVariables();
        if (processVariables != null) {
            taskService.completeTask(bpmTask.getId(), processVariables);
        } else {
            taskService.completeTask(bpmTask.getId());
        }

        showNotification(formatMessage("taskCompletedNotification", bpmTask.getName(), NotificationType.TRAY));
        close(Window.COMMIT_ACTION_ID);
        if (log.isDebugEnabled()) log.debug("Call bpm task service to complete task " + bpmTask.getId().toString());

    }

    private boolean checkAssignee() {
        User user = userSession.getCurrentOrSubstitutedUser();
        return user.equals(bpmTask.getAssignee());
    }

    private boolean checkClaimable() {
        if (bpmTask.getAssignee() != null) {
            return false;
        }

        return taskService.checkClaimable(userSession.getUser().getLogin(), userSession.getRoles(), bpmTask.getId());
    }

    public void onClaimBtnClick() {
        String userLoginName = userSession.getCurrentOrSubstitutedUser().getLogin();
        taskService.claimTask(bpmTask.getId(), userLoginName);
        bpmTask.setAssignee(userSession.getCurrentOrSubstitutedUser());
        initGeneralTaskFormComponent();
        showNotification(getMessage("taskClaimedNotification"), NotificationType.TRAY);
    }


    public void onUnClaimBtnClick() {
        taskService.unClaimTask(bpmTask.getId());
        bpmTask.setAssignee(null);
        initGeneralTaskFormComponent();
        showNotification(getMessage("taskUnClaimedNotification"), NotificationType.TRAY);
    }

    public void onDelegateBtnClick() {
        if (bpmTask.getCandidateRoles() == null) {
            String candidateRoles = String.join(",", taskService.getCandidateGroups(bpmTask.getId()));
            usersDs.refresh(ParamsMap.of("roles", candidateRoles));
            bpmTask.setCandidateRoles(candidateRoles);
        }

        assigneeViewBox.setVisible(false);
        assigneeEditBox.setVisible(true);
    }

    public void onShowProcessInstanceBtnClick() {
        openEditor(bpmTask.getProcessInstance(), WindowManager.OpenType.DIALOG);
    }

    public void onCloseBtnClick() {
        close(CLOSE_ACTION_ID);
    }

    public void onConfirmDelegateBtnClick() {
        if (bpmTask.getAssignee() != null) {
            taskService.delegateTask(bpmTask.getId(), bpmTask.getAssignee().getLogin());
        } else taskService.delegateTask(bpmTask.getId(), null);
        initGeneralTaskFormComponent();
    }

    public void onOpenEntityBtnClick() {
        //Open an editor
        Entity entity = bpmTask.getEntity();
        if (entity != null)
            openEditor(entity, WindowManager.OpenType.DIALOG, ParamsMap.of(
                    BpmTaskFormParam.BPM_TASK_PARAM_NAME, bpmTask,
                    DISABLED_FLAG_PARAM_NAME, true));
        else
            showNotification(getMessage("entityNotFoundNotification"), NotificationType.ERROR);
    }
}