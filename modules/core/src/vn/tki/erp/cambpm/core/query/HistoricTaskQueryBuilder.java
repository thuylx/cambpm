package vn.tki.erp.cambpm.core.query;

import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.helper.BpmEntityConverter;
import vn.tki.erp.cambpm.query.BpmHistoricTaskQuery;

import javax.inject.Inject;

@Component(HistoricTaskQueryBuilder.NAME)
public class HistoricTaskQueryBuilder extends AbstractQueryBuilder<HistoricTaskInstanceQuery, HistoricTaskInstance, BpmTask> {
    public static final String NAME = "cambpm_CamHistoricTaskQueryBuilder";

    @Inject
    private CamPlatform camPlatform;
    @Inject
    private BpmEntityConverter converter;

    @Override
    public boolean supports(Class queryContextClass) {
        return queryContextClass.equals(BpmHistoricTaskQuery.class);
    }

    @Override
    public void authorization() throws BpmAuthorizationException {
        checkReadPermission(BpmTask.class);
    }

    @Override
    public HistoricTaskInstanceQuery createQuery() {
        return camPlatform.getProcessEngine().getHistoryService().createHistoricTaskInstanceQuery();
    }

    @Override
    public BpmTask convert(HistoricTaskInstance object) {
        return converter.toBpmTask(object);
    }
}
