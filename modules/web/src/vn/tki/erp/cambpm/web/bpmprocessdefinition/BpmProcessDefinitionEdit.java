package vn.tki.erp.cambpm.web.bpmprocessdefinition;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.TabSheet;
import com.haulmont.cuba.gui.components.VBoxLayout;
import vn.tki.erp.cambpm.entity.BpmProcessDefinition;
import vn.tki.erp.cambpm.helper.BpmFormHelper;
import vn.tki.erp.cambpm.query.BpmProcessDefinitionQuery;
import vn.tki.erp.cambpm.service.BpmRepositoryService;

import javax.inject.Inject;
import java.util.Map;

public class BpmProcessDefinitionEdit extends AbstractEditor<BpmProcessDefinition> {
    @Inject
    private BpmFormHelper formHelper;
    @Inject
    private TabSheet mainTabSheet;
    @Inject
    private BpmRepositoryService repositoryService;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        mainTabSheet.addSelectedTabChangeListener(event -> {
            if ("diagramTab".equals(event.getSelectedTab().getName())) {
                initDiagramTab();
            }
        });
    }

    private void initDiagramTab(){
        VBoxLayout diagramBox = (VBoxLayout) getComponentNN("diagramBox");
        if (diagramBox.isEnabled()) {
            //initialized already
            return;
        }

        BpmProcessDefinition processDefinition = getItem();
        BpmProcessDefinitionQuery query = new BpmProcessDefinitionQuery();
        query.processDefinitionKey(processDefinition.getKey()).latestVersion();

        String xmlDiagram = repositoryService.getProcessDefinitionDiagram(query.singleResult().getId());
        formHelper.initBpmnViewer(diagramBox, xmlDiagram, null);
        //mark as initialized
        diagramBox.setEnabled(true);
    }
}