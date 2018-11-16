package vn.tki.erp.cambpm.core.query;

import org.camunda.bpm.engine.history.UserOperationLogEntry;
import org.camunda.bpm.engine.history.UserOperationLogQuery;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.entity.BpmUserOperationLogEntry;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.helper.BpmEntityConverter;
import vn.tki.erp.cambpm.query.BpmUserOperationLogQuery;

import javax.inject.Inject;

@Component(UserOperationLogQueryBuilder.NAME)
public class UserOperationLogQueryBuilder extends AbstractQueryBuilder<UserOperationLogQuery, UserOperationLogEntry, BpmUserOperationLogEntry> {
    public static final String NAME = "cambpm_CamUserOperationLogQueryBuilder";

    @Inject
    private CamPlatform camPlatform;
    @Inject
    private BpmEntityConverter converter;

    @Override
    public boolean supports(Class queryContextClass) {
        return queryContextClass.equals(BpmUserOperationLogQuery.class);
    }

    @Override
    public void authorization() throws BpmAuthorizationException {
        checkReadPermission(BpmUserOperationLogEntry.class);
    }

    @Override
    public UserOperationLogQuery createQuery() {
        return camPlatform.getProcessEngine().getHistoryService().createUserOperationLogQuery();
    }

    @Override
    public BpmUserOperationLogEntry convert(UserOperationLogEntry object) {
        return converter.toBpmUserOperationLogEntry(object);
    }
}
