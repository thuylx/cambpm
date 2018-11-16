package vn.tki.erp.cambpm.exception;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Frame;
import com.haulmont.cuba.gui.exception.AbstractGenericExceptionHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

/**
 * Exception handler displaying a user-friendly message when BpmEntityNotFoundException occurs.
 */
@Component(BpmProcessEngineExceptionHandler.NAME)
public class BpmProcessEngineExceptionHandler extends AbstractGenericExceptionHandler {
    public static final String NAME = "cambpm_BpmTaskNotFoundExceptionHandler";

    public BpmProcessEngineExceptionHandler() {
        super(BpmProcessEngineException.class.getName());
    }

    @Override
    protected void doHandle(String className, String message, @Nullable Throwable throwable, WindowManager windowManager) {
        windowManager.showNotification(message,
                throwable != null ? throwable.getMessage() : null,
                Frame.NotificationType.ERROR);
    }
}

