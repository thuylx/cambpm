package vn.tki.erp.cambpm.web.bpmtask;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParam;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.model.BpmForm;

import java.util.Map;

public class BpmTaskExternalFrame extends BpmTaskAbstractFrame {
    public static final String ID = "cambpm$BpmTaskExternalFrame";

    @WindowParam(name = BpmTaskComplete.WinParam.BPM_FORM_PARAM_NAME)
    private BpmForm bpmForm;

    public void onExternalFormBtnClick() {
        BpmTask bpmTask = getBpmTask();
        Entity entity = bpmTask.getEntity();
        //Editor
        if (BpmForm.ScreenType.EDITOR.equals(bpmForm.getScreenType())) {
            if (entity != null) {
                openEditor(bpmForm.getFormName(), entity,
                        WindowManager.OpenType.valueOf(bpmForm.getOpenType()));
            } else {
                showNotification(getMessage("noEntityFoundNotification"), NotificationType.HUMANIZED);
            }
            //Window
        } else {
            openWindow(bpmForm.getFormName(), WindowManager.OpenType.valueOf(bpmForm.getOpenType()), ParamsMap.of(
                    BpmTaskComplete.WinParam.BPM_TASK_PARAM_NAME, bpmTask));
        }
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        return null;
    }

    @Override
    public void onCompleteTask() throws Exception {

    }
}