package vn.tki.erp.cambpm.exception;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Frame;
import com.haulmont.cuba.gui.exception.AbstractGenericExceptionHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

/**
 * Exception handler displaying a user-friendly message when BpmEntityNotFoundException occurs.
 */
@Component(BpmEntityNotFoundExceptionHandler.NAME)
public class BpmEntityNotFoundExceptionHandler extends AbstractGenericExceptionHandler {
    public static final String NAME = "cambpm_BpmEntityNotFoundExceptionHandler";

    public BpmEntityNotFoundExceptionHandler() {
        super(BpmEntityNotFoundException.class.getName());
    }

    @Override
    protected void doHandle(String className, String message, @Nullable Throwable throwable, WindowManager windowManager) {
        windowManager.showNotification("Bpm entity key not found",
                throwable != null ? throwable.getMessage() : null,
                Frame.NotificationType.ERROR);
    }
}

