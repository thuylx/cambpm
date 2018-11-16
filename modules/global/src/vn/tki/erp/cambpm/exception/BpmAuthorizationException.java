package vn.tki.erp.cambpm.exception;

import com.haulmont.cuba.core.global.Logging;
import com.haulmont.cuba.core.global.SupportedByClient;

/**
 * Exception thrown on attempt to query bpm services without sufficient permission.
 */
@SupportedByClient
@Logging(Logging.Type.BRIEF)
public class BpmAuthorizationException extends RuntimeException {
    private final String name;

    public BpmAuthorizationException(String action, String permissionNeeded) {
        super(String.format("Permissions required to perform action %s: %s", action, permissionNeeded));
        this.name = action;
    }

    public String getName() {
        return name;
    }

}
