package vn.tki.erp.cambpm.event;

import com.haulmont.addon.globalevents.GlobalApplicationEvent;
import com.haulmont.addon.globalevents.GlobalUiEvent;
import vn.tki.erp.cambpm.model.BpmForm;

import java.util.UUID;

public class BpmTaskFormSubmittedEvent extends GlobalApplicationEvent implements GlobalUiEvent {
    private BpmForm formData;
    private UUID bpmTaskId;

    public BpmTaskFormSubmittedEvent(Object source, UUID taskId, BpmForm formData) {
        super(source);
        this.bpmTaskId = taskId;
        this.formData = formData;
    }
    public BpmForm getFormData() {
        return formData;
    }

    public UUID getBpmTaskId() {
        return bpmTaskId;
    }
}
