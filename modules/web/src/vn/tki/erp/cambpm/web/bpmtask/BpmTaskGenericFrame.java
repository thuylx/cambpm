package vn.tki.erp.cambpm.web.bpmtask;

import com.google.common.base.Strings;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.VBoxLayout;
import vn.tki.erp.cambpm.entity.BpmProcessInstance;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.event.BpmTaskFormSubmittedEvent;
import vn.tki.erp.cambpm.exception.BpmEntityNotFoundException;
import vn.tki.erp.cambpm.helper.BpmFormHelper;
import vn.tki.erp.cambpm.model.BpmForm;
import vn.tki.erp.cambpm.model.BpmFormField;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class BpmTaskGenericFrame extends BpmTaskAbstractFrame {
    public static final String ID = "cambpm$GenericTaskFrame";

    @WindowParam(name = BpmTaskComplete.WinParam.BPM_FORM_PARAM_NAME)
    private BpmForm bpmForm;

    @Inject
    private VBoxLayout taskFormBox;
    @Inject
    private Events events;
    @Inject
    private DataManager dataManager;
    @Inject
    private BpmFormHelper formHelper;

    private Map<String, Object> processVariables = new HashMap<>();

    @Override
    public void onCompleteTask() {
        BpmTask bpmTask = getBpmTask();

        formHelper.loadFormData(bpmForm, taskFormBox);

        boolean entityUpdated = false;

        for (BpmFormField formField:bpmForm.getFormFields()) {
            if (formField.isProcessVariable())
                processVariables.put(formField.getId(), formField.getValue());
            else if (formField.isEntityAttribute() ) {
                if (bpmTask.getEntity() == null) {
                    BpmProcessInstance processInstance = bpmTask.getProcessInstance();
                    if (processInstance != null && !Strings.isNullOrEmpty(processInstance.getBusinessKey())) {
                        throw new BpmEntityNotFoundException("Complete task", processInstance.getBusinessKey());
                    } else {
                        throw new BpmEntityNotFoundException("Complete task","");

                    }
                }
                if (!formField.getValue().equals(bpmTask.getEntity().getValue(formField.getId()))) {
                    bpmTask.getEntity().setValue(formField.getId(), formField.getValue());
                    entityUpdated = true;
                }
            }
        }

        if (entityUpdated) {
            dataManager.commit(bpmTask.getEntity());
        }

        events.publish(new BpmTaskFormSubmittedEvent(this, getBpmTask().getId(), bpmForm));
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        return processVariables;
    }

    @Override
    public void init(Map<String, Object> param) {
        if (bpmForm == null) {
            return;
        }
        formHelper.initBpmFormFields(taskFormBox, bpmForm);
    }
}