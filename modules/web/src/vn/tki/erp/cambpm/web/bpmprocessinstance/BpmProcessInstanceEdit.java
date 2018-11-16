package vn.tki.erp.cambpm.web.bpmprocessinstance;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.icons.CubaIcon;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import vn.tki.erp.cambpm.entity.BpmProcessInstance;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.helper.BpmFormHelper;
import vn.tki.erp.cambpm.query.BpmTaskQuery;
import vn.tki.erp.cambpm.service.BpmRepositoryService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.haulmont.cuba.web.theme.HaloTheme.BUTTON_ICON_ALIGN_RIGHT;

public class BpmProcessInstanceEdit extends AbstractEditor<BpmProcessInstance> {
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private BpmFormHelper formHelper;
    @Inject
    private FieldGroup fieldGroup;
    @Inject
    private TabSheet tabSheet;
    @Inject
    private BpmRepositoryService repositoryService;
    @Inject
    private Datasource<BpmProcessInstance> bpmProcessInstanceDs;

    private BpmProcessInstance processInstance;

    @Override
    public void init(Map<String, Object> params){
        tabSheet.addSelectedTabChangeListener(event -> {
            if ("diagramTab".equals(event.getSelectedTab().getName())) {
                initDiagrabTab();
            }
        });
    }

    @Override
    protected void postInit() {
        processInstance = getItem();
        Entity entity = processInstance.getEntity();
        LinkButton linkButton = (LinkButton) fieldGroup.getField("entity").getComponentNN();
        if (entity == null) {
            linkButton.setCaption(getMessage("noEntityAttachedNotification"));
            linkButton.setEnabled(false);
        } else {
            linkButton.setCaption(entity.getInstanceName());
            linkButton.setDescription(processInstance.getBusinessKey());
            linkButton.setAction(new BaseAction("openEntityAction")
                    .withHandler(actionPerformedEvent -> openEditor(entity, WindowManager.OpenType.THIS_TAB)));
        }
        bpmProcessInstanceDs.setItem(processInstance);
    }

    private void initDiagrabTab() {
        VBoxLayout diagramBox = (VBoxLayout) getComponentNN("diagramBox");
        if (diagramBox.isEnabled()) {
            //initialized already
            return;
        }

        List<String> pendingTaskKeyList = new ArrayList<>();
        for (BpmTask bpmTask : processInstance.getTasks()) {
            if (bpmTask.getEndTime() == null) {
                //reload task from runtime database for task definition key.
                // This due to we may load from history database before which does not provide such field
                if (bpmTask.getTaskDefinitionKey() == null) {
                    BpmTaskQuery query = new BpmTaskQuery();
                    query.taskId(bpmTask.getId().toString());
                    bpmTask = query.singleResult();
                }
                pendingTaskKeyList.add(bpmTask.getTaskDefinitionKey());
            }
        }
        String[] pendingTaskKeys = pendingTaskKeyList.toArray(new String[pendingTaskKeyList.size()]);
        String xmlDiagram = repositoryService.getProcessDefinitionDiagram(processInstance.getBpmProcessDefinitionVersion().getId());
        formHelper.initBpmnViewer(diagramBox, xmlDiagram, pendingTaskKeys);

        //mark as initialized
        diagramBox.setEnabled(true);
    }

    public void onOpenEntity(Component source) {
        Entity entity = processInstance.getEntity();
        if (entity != null)
            openEditor(entity, WindowManager.OpenType.THIS_TAB);
        else
            showNotification(getMessage("noEntityAttachedNotification"), NotificationType.TRAY);
    }

    public void onClose(Component source) {
        close(CLOSE_ACTION_ID);
    }

    public Component generateIsCompletedCell(BpmTask entity) {
        LinkButton linkButton = componentsFactory.createComponent(LinkButton.class);
        linkButton.setId("isCompleted:"+entity.getId().toString());
        linkButton.setFocusable(false);

        if (entity.getEndTime() != null) {
            linkButton.setIcon("font-icon:CHECK_SQUARE_O");
        } else {
            linkButton.setIcon("font-icon:SQUARE_O");
        }

        return linkButton;
    }

    public Component generateEntityField(Datasource datasource, String fieldId) {
        LinkButton linkButton = componentsFactory.createComponent(LinkButton.class);
        linkButton.setId(fieldId);
        linkButton.setIconFromSet(CubaIcon.PICKERFIELD_OPEN);
        linkButton.addStyleName(BUTTON_ICON_ALIGN_RIGHT);
        linkButton.setAlignment(Alignment.BOTTOM_LEFT);

        return linkButton;
    }


    public Component generateStatusCell(BpmTask bpmTask) {
        LinkButton linkButton = componentsFactory.createComponent(LinkButton.class);
        linkButton.setId("isCompleted:"+bpmTask.getId().toString());
        linkButton.setFocusable(false);

        if (bpmTask.getEndTime() != null) {
            linkButton.setIcon("font-icon:CHECK_SQUARE_O");
        } else {
            linkButton.setIcon("font-icon:SQUARE_O");
        }

        return linkButton;
    }
}