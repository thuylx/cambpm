package vn.tki.erp.cambpm.web.newentity;

import de.balvi.cuba.declarativecontrollers.web.editor.AnnotatableAbstractEditor;
import vn.tki.erp.cambpm.annotation.EnableBpmFrame;
import vn.tki.erp.cambpm.entity.NewEntity;

@EnableBpmFrame(taskActionContainerId = "bpmPanel",
        processDefinitionActionContainerId = "bpmPanel",
        runningProcessActionContainerId = "bpmPanel",
        taskRefreshActionContainerId = "bpmPanel")
public class NewEntityEdit extends AnnotatableAbstractEditor<NewEntity> {
}