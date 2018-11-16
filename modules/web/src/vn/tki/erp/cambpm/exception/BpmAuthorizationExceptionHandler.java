package vn.tki.erp.cambpm.exception;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.Frame;
import com.haulmont.cuba.gui.exception.AbstractGenericExceptionHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

/**
 * Exception handler displaying a user-friendly message when TooManyOrdersException occurs.
 */
@Component(BpmAuthorizationExceptionHandler.NAME)
public class BpmAuthorizationExceptionHandler extends AbstractGenericExceptionHandler {
    public static final String NAME = "cambpm_BpmAuthorizationExceptionHandler";

    public BpmAuthorizationExceptionHandler() {
        super(BpmAuthorizationException.class.getName());
    }

    @Override
    protected void doHandle(String className, String message, @Nullable Throwable throwable, WindowManager windowManager) {
        windowManager.showNotification("Bpm authorization error",
                throwable != null ? throwable.getMessage() : null,
                Frame.NotificationType.ERROR);
    }
}

