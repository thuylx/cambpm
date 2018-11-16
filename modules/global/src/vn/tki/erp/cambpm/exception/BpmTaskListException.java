package vn.tki.erp.cambpm.exception;

import com.haulmont.cuba.core.global.Logging;
import com.haulmont.cuba.core.global.SupportedByClient;

@SupportedByClient
@Logging(Logging.Type.BRIEF)
public class BpmTaskListException extends RuntimeException {
    public BpmTaskListException(String message) {
        super(message);
    }

    public BpmTaskListException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
