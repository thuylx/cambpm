package vn.tki.erp.cambpm.exception;

import com.haulmont.cuba.core.global.SupportedByClient;

/**
 * Exception thrown on attempt to query bpm services without sufficient permission.
 */
@SupportedByClient
public class BpmEntityNotFoundException extends RuntimeException {
    private final String name;

    public BpmEntityNotFoundException(String action, String entityKey) {
        super(String.format("Entiy not found for key %s", entityKey));
        this.name = action;
    }

    public String getName() {
        return name;
    }

}
