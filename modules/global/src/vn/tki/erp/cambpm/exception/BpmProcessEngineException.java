package vn.tki.erp.cambpm.exception;

import com.haulmont.cuba.core.global.SupportedByClient;

/**
 * Exception thrown on attempt to query bpm services without sufficient permission.
 */
@SupportedByClient
public class BpmProcessEngineException extends RuntimeException {
    public BpmProcessEngineException(String message) {
        super(message);
    }

    public BpmProcessEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
