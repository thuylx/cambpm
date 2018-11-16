package vn.tki.erp.cambpm.annotation;

import com.google.common.base.Strings;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.Window;
import de.balvi.cuba.declarativecontrollers.web.annotationexecutor.editor.EditorAnnotationExecutor;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.helper.BpmFrameGenerator;
import vn.tki.erp.cambpm.web.config.BpmTaskFormParam;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.UUID;

import static vn.tki.erp.cambpm.annotation.EnableBpmFrame.DISABLED_FLAG_PARAM_NAME;

@Component
public class BpmFrameEditorAnnotationExecutor implements EditorAnnotationExecutor<EnableBpmFrame> {

    private BpmTask bpmTask;
    private boolean bpmFrameDisabled = false;
    @Inject
    private BpmFrameGenerator frameGenerator;

    @Override
    public boolean supports(Annotation annotation) {
        return annotation instanceof EnableBpmFrame;
    }

    @Override
    public void init(EnableBpmFrame annotation, Window.Editor editor, Map<String, Object> params) {
        bpmTask = (BpmTask) params.get(BpmTaskFormParam.BPM_TASK_PARAM_NAME);
        if (params.containsKey(DISABLED_FLAG_PARAM_NAME)) {
            bpmFrameDisabled = (boolean) params.get(DISABLED_FLAG_PARAM_NAME);
        } else {
            bpmFrameDisabled = false;
        }
    }

    @Override
    public void postInit(EnableBpmFrame annotation, Window.Editor editor) {
        initContainers(annotation, editor);
        UUID selectedTaskId = null;
        if (bpmTask != null) {
            selectedTaskId = bpmTask.getId();
        }
        frameGenerator.loadBpmComponents(annotation, editor, editor.getItem(), selectedTaskId);
    }

    private void initContainers(EnableBpmFrame annotation, Window.Editor editor) {
        boolean isNewItem = PersistenceHelper.isNew(editor.getItem());
        if (!Strings.isNullOrEmpty(annotation.taskActionContainerId())) {
            com.haulmont.cuba.gui.components.Component.Container container = (com.haulmont.cuba.gui.components.Component.Container) editor.getComponent(annotation.taskActionContainerId());
            if (container != null) {
                container.setVisible(!isNewItem);
                container.setEnabled(!bpmFrameDisabled);
            }
        }
        if (!Strings.isNullOrEmpty(annotation.taskRefreshActionContainerId())) {
            com.haulmont.cuba.gui.components.Component.Container container = (com.haulmont.cuba.gui.components.Component.Container) editor.getComponent(annotation.taskRefreshActionContainerId());
            if (container != null) {
                container.setVisible(!isNewItem);
                container.setEnabled(!bpmFrameDisabled);
            }
        }
        if (!Strings.isNullOrEmpty(annotation.processDefinitionActionContainerId())) {
            com.haulmont.cuba.gui.components.Component.Container container = (com.haulmont.cuba.gui.components.Component.Container) editor.getComponent(annotation.processDefinitionActionContainerId());
            if (container != null) {
                container.setEnabled(!isNewItem && !bpmFrameDisabled);
                container.removeAll();
            }
        }
        if (!Strings.isNullOrEmpty(annotation.runningProcessActionContainerId())) {
            com.haulmont.cuba.gui.components.Component.Container container = (com.haulmont.cuba.gui.components.Component.Container) editor.getComponent(annotation.runningProcessActionContainerId());
            if (container != null) {
                container.setEnabled(!isNewItem && !bpmFrameDisabled);
                container.removeAll();
            }
        }
    }
}
