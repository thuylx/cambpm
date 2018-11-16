package vn.tki.erp.cambpm.web.bpmtask;

import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Field;
import vn.tki.erp.cambpm.annotation.ProcessVariable;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.service.BpmTaskService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * All of frame which intended to be embedded in the task completion form should be extended from this class
 */
public abstract class BpmTaskAbstractFrame extends AbstractFrame {

    /**
     * Passed bpmTask object from Task Completion form
     */
    @WindowParam(name = BpmTaskComplete.WinParam.BPM_TASK_PARAM_NAME)
    protected BpmTask bpmTask;

    @Inject
    BpmTaskService taskService;

    public BpmTask getBpmTask() {
        return bpmTask;
    }

    /**
     * Bpm Task completion form will call this function to get map of process variables to complete bpm task. <br>
     * By default, all of component with ID in thr form of "bpm.variableName" will be considered input for a process variable. Override this function to ignore that.
     * @return map of process variables which will be commit to the bpm engine.
     */
    public Map<String, Object> getProcessVariables() {
        Map<String, Object> processVariables = new HashMap<>();
        for (java.lang.reflect.Field field: this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ProcessVariable.class)) {
                ProcessVariable annotation = field.getAnnotation(ProcessVariable.class);
                String variableName = annotation.name().isEmpty()?field.getName():annotation.name();
                try {
                    field.setAccessible(true);
                    processVariables.put(variableName, ((Field) field.get(this)).getValue());
                } catch (IllegalAccessException e) {
                    showNotification("Cannot get process variable field. This may due to wrong use of @ProcessVariable annotation in task form controller");
                    e.printStackTrace();
                }
            }
        }
        return processVariables;
    }

    /**
     * Bpm task completion form will invoke this method right before completing task
     * @throws Exception
     */
    public abstract void onCompleteTask() throws Exception;

    @Override
    public void init(Map<String, Object> params) {
        for (java.lang.reflect.Field field: this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ProcessVariable.class)) {
                ProcessVariable annotation = field.getAnnotation(ProcessVariable.class);
                if (annotation.loadValue()) {
                    String variableName = annotation.name().isEmpty()?field.getName():annotation.name();
                    Object value = taskService.getTaskVariable(bpmTask.getId(), variableName);
                    try {
                        field.setAccessible(true);
                        ((Field) field.get(this)).setValue(value);
                    } catch (IllegalAccessException e) {
                        showNotification("Cannot get process variable field. This may due to wrong use of @ProcessVariable annotation in task form controller");
                        e.printStackTrace();
                    }

                }
            }
        }
    }

}
