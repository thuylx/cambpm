package vn.tki.erp.cambpm.web.bpmprocessdefinition;

import com.company.editablebrowser.EditableBrowser;
import com.google.common.base.Strings;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.data.GroupDatasource;
import de.balvi.cuba.declarativecontrollers.web.browse.AnnotatableAbstractLookup;
import vn.tki.erp.cambpm.entity.BpmProcessDefinition;
import vn.tki.erp.cambpm.helper.BpmFormHelper;
import vn.tki.erp.cambpm.model.BpmForm;
import vn.tki.erp.cambpm.query.BpmProcessDefinitionQuery;
import vn.tki.erp.cambpm.service.BpmFormService;
import vn.tki.erp.cambpm.service.BpmRepositoryService;
import vn.tki.erp.cambpm.web.config.BpmStartFormParam;

import javax.inject.Inject;
import java.util.UUID;

@EditableBrowser(editableTableId = "bpmProcessDefinitionsTable")
public class BpmProcessDefinitionBrowse extends AnnotatableAbstractLookup{

    @Inject
    private BpmRepositoryService repositoryService;
    @Inject
    private BpmFormService formService;
    @Inject
    private Metadata metadata;
    @Inject
    private BpmFormHelper formHelper;
    @Inject
    private GroupDatasource<BpmProcessDefinition, UUID> bpmProcessDefinitionsDs;

    public void onStart(Component source) {
        BpmProcessDefinition processDefinition = bpmProcessDefinitionsDs.getItem();
        if (!processDefinition.getDeployed()) {
            showNotification(getMessage("processDefinitionNotDeployedNotification"),NotificationType.ERROR);
            return;
        }

        openProcessStartForm(processDefinition);
    }

    private void openProcessStartForm(BpmProcessDefinition processDefinition) {
        BpmProcessDefinitionQuery queryContext = new BpmProcessDefinitionQuery();
        queryContext.latestVersion().processDefinitionKey(processDefinition.getKey());
        BpmForm bpmForm = formService.getStartForm(queryContext.singleResult().getId());

        if (Strings.isNullOrEmpty(bpmForm.getFormName())) {
            //Open generic form
            openWindow(BpmProcessDefinitionStart.ID, WindowManager.OpenType.NEW_TAB, ParamsMap.of(
                    BpmProcessDefinitionStart.WinParam.BPM_PROCESS_DEFINITION_PARAM_NAME, processDefinition,
                    BpmProcessDefinitionStart.WinParam.BPM_FORM_PARAM_NAME, bpmForm));
        } else {
            try {
                formHelper.standardizeFormName(bpmForm, processDefinition);
                if (BpmForm.ScreenType.EDITOR.equals(bpmForm.getScreenType())) {
                    try {
                        //Open an editor
                        if (processDefinition.getEntities().size() != 1) throw new IllegalStateException();

                        Entity item = metadata.create(processDefinition.getEntities().get(0).getEntityName());
                        openEditor(bpmForm.getFormName(), item, WindowManager.OpenType.valueOf(bpmForm.getOpenType()), ParamsMap.of(
                                BpmStartFormParam.BPM_PROCESS_DEFINITION_PARAM_NAME, processDefinition));
                    } catch (IllegalArgumentException e) {
                        showNotification(messages.getMessage(getClass(), "openEditorErrorMsg"), NotificationType.ERROR);
                    }
                } else {
                    //Open a window (not editor)
                    openWindow(bpmForm.getFormName(), WindowManager.OpenType.valueOf(bpmForm.getOpenType()), ParamsMap.of(
                            BpmStartFormParam.BPM_PROCESS_DEFINITION_PARAM_NAME, processDefinition));
                }
            } catch (IllegalStateException e) {
                showNotification(getMessage("cannotGetSingleEntityToStartProcessNotification"), NotificationType.TRAY);
            }
        }
    }

    public void onDeploy(Component source) {
        openWindow("cambpm$BpmProcessDefinition.deploy", WindowManager.OpenType.DIALOG)
                .addCloseWithCommitListener(() -> bpmProcessDefinitionsDs.refresh());
    }

    public void onSync(Component source) {
//        showNotification(getMessage("syncingNotification"));
        repositoryService.syncProcessDefinition();
        bpmProcessDefinitionsDs.refresh();
        showNotification(getMessage("syncCompletedNotification"), NotificationType.TRAY);
    }
}