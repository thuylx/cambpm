package vn.tki.erp.cambpm.exception;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Frame;
import com.haulmont.cuba.gui.exception.AbstractGenericExceptionHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

@Component(BpmTaskListExceptionHandler.NAME)
public class BpmTaskListExceptionHandler extends AbstractGenericExceptionHandler {
    public static final String NAME = "cambpm_BpmTaskListExceptionHandler";

    public BpmTaskListExceptionHandler() {
        super(BpmTaskListException.class.getName());
    }

    @Override
    protected void doHandle(String className, String message, @Nullable Throwable throwable, WindowManager windowManager) {
        windowManager.showNotification(message,
                Frame.NotificationType.ERROR);
    }
}
