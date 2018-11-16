package vn.tki.erp.cambpm.event;

import com.haulmont.addon.globalevents.GlobalApplicationEvent;
import com.haulmont.addon.globalevents.GlobalUiEvent;
import vn.tki.erp.cambpm.model.BpmForm;

import java.util.UUID;

public class BpmStartFormSubmittedEvent extends GlobalApplicationEvent implements GlobalUiEvent {
    private BpmForm formData;
    private UUID bpmProcessInstanceId;

    public BpmStartFormSubmittedEvent(Object source, UUID processInstanceId, BpmForm formData) {
        super(source);
        this.bpmProcessInstanceId = processInstanceId;
        this.formData = formData;
    }
    public BpmForm getFormData() {
        return formData;
    }

    public UUID getBpmProcessInstanceId() {
        return bpmProcessInstanceId;
    }
}
