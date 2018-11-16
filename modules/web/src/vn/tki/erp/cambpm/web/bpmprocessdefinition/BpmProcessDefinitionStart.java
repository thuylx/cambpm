package vn.tki.erp.cambpm.web.bpmprocessdefinition;

import com.google.common.base.Strings;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.Datasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.tki.erp.cambpm.entity.BpmProcessDefinition;
import vn.tki.erp.cambpm.entity.BpmProcessDefinitionVersion;
import vn.tki.erp.cambpm.event.BpmStartFormSubmittedEvent;
import vn.tki.erp.cambpm.helper.BpmFormHelper;
import vn.tki.erp.cambpm.model.BpmForm;
import vn.tki.erp.cambpm.model.BpmFormField;
import vn.tki.erp.cambpm.query.BpmProcessDefinitionQuery;
import vn.tki.erp.cambpm.service.BpmFormService;
import vn.tki.erp.cambpm.service.BpmRepositoryService;
import vn.tki.erp.cambpm.service.BpmRuntimeService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BpmProcessDefinitionStart extends AbstractWindow {
    public static final String ID = "cambpm$BpmProcessDefinition.start";
    public interface WinParam {
        String BPM_PROCESS_DEFINITION_PARAM_NAME = "bpmProcessDefinitionVersion";
        String ENTITY_PARAM_NAME = "entity";
        String BPM_FORM_PARAM_NAME = "bpmForm";
    }

    @WindowParam(name = WinParam.BPM_PROCESS_DEFINITION_PARAM_NAME)
    private BpmProcessDefinition processDefinition;
    @WindowParam(name = WinParam.ENTITY_PARAM_NAME)
    private Entity entity;
    @WindowParam(name = WinParam.BPM_FORM_PARAM_NAME)
    private BpmForm bpmForm;

    @Inject
    private BpmRuntimeService runtimeService;
    @Inject
    private BpmRepositoryService repositoryService;
    @Inject
    private BpmFormService formService;
    @Inject
    private DataManager dataManager;
    @Inject
    private BpmFormHelper formHelper;
    @Inject
    private Events events;
    @Inject
    private TabSheet tabSheet;
    @Inject
    private ResizableTextArea descriptionLbl;
    @Inject
    private VBoxLayout fieldsBox;
    @Inject
    private Datasource<BpmProcessDefinition> bpmProcessDefinitionDs;

    // create logger
    private final Logger log = LoggerFactory.getLogger(BpmProcessDefinitionStart.class);

    @Override
    public void init(Map<String, Object> params) {
        if (processDefinition == null) {
            if (log.isErrorEnabled()) log.error("BpmProcessDefinitionFrame is initialized without BpmProcessDefinition instance");
            this.close(CLOSE_ACTION_ID);
            return;
        }

        if (!processDefinition.getDeployed()) {
            if (log.isErrorEnabled()) log.error("BpmProcessDefinitionFrame is initialized with an un-deployed BpmProcessDefinition");
            showNotification(getMessage("processDefinitionNotDeployedNotification"),NotificationType.ERROR);
            this.close(CLOSE_ACTION_ID);
            return;
        }

        bpmProcessDefinitionDs.setItem(processDefinition);

        descriptionLbl.setVisible(!Strings.isNullOrEmpty(processDefinition.getDescription()));

        BpmProcessDefinitionQuery query = new BpmProcessDefinitionQuery();
        query.processDefinitionKey(processDefinition.getKey()).latestVersion();
        if (bpmForm == null) bpmForm = formService.getStartForm(query.singleResult().getId());

        formHelper.initBpmFormFields(fieldsBox, bpmForm);

        tabSheet.addSelectedTabChangeListener(event -> {
            if ("diagramTab".equals(event.getSelectedTab().getName())) {
                initDiagramTab();
            }
        });

    }

    private void initDiagramTab() {
        VBoxLayout diagramBox = (VBoxLayout) getComponentNN("diagramBox");
        if (diagramBox.isEnabled()) {
            //initialized already
            return;
        }

        //Load bpmn viewer
        BpmProcessDefinitionQuery query = new BpmProcessDefinitionQuery();
        query.processDefinitionKey(processDefinition.getKey()).latestVersion();
        BpmProcessDefinitionVersion processDefinitionVersion = query.singleResult();
        String xmlDiagram;
        if (processDefinitionVersion != null) {
            xmlDiagram = repositoryService.getProcessDefinitionDiagram(processDefinitionVersion.getId());
            formHelper.initBpmnViewer(diagramBox, xmlDiagram, new String[0]);
        } else {
            showNotification(getMessage("diagramNotFoundNotification"), NotificationType.TRAY);
        }
        //mark as initialized
        diagramBox.setEnabled(true);
    }

    public void onStart(Component source) {

        if (validateAll()) {

            formHelper.loadFormData(bpmForm, fieldsBox);

            Map<String, Object> processVariables = new HashMap<>();
            boolean entityUpdated = false;
            for (BpmFormField formField:bpmForm.getFormFields()) {
                if (formField.isProcessVariable())
                    processVariables.put(formField.getId(), formField.getValue());
                else if (formField.isEntityAttribute()){
                    if (entity != null) {
                        entity.setValue(formField.getId(), formField.getValue());
                        entityUpdated = true;
                    }
                }
            }

            if (entityUpdated) dataManager.commit(entity);

            //then start process
            UUID processInstanceId;
            if (entity != null) {
                EntityLoadInfoBuilder entityLoadInfoBuilder = AppBeans.get(EntityLoadInfoBuilder.NAME);
                EntityLoadInfo entityLoadInfo = entityLoadInfoBuilder.create(entity);
                processInstanceId = runtimeService.startProcessInstanceByKey(processDefinition.getKey(),entityLoadInfo.toString(), processVariables);
            } else {
                processInstanceId = runtimeService.startProcessInstanceByKey(processDefinition.getKey(), processVariables);
            }

            events.publish(new BpmStartFormSubmittedEvent(this, processInstanceId, bpmForm));
            showNotification(formatMessage("processInstanceStartedNotification", processDefinition.getName(), NotificationType.TRAY));
            close(Window.COMMIT_ACTION_ID);
        }
    }


    public void onClose(Component source) {
        close(CLOSE_ACTION_ID);
    }
}