package vn.tki.erp.cambpm.core.query;

import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.entity.BpmProcessInstance;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.helper.BpmEntityConverter;
import vn.tki.erp.cambpm.query.BpmHistoricProcessInstanceQuery;

import javax.inject.Inject;

@Component(HistoricProcessInstanceQueryBuilder.NAME)
public class HistoricProcessInstanceQueryBuilder extends AbstractQueryBuilder<HistoricProcessInstanceQuery, HistoricProcessInstance, BpmProcessInstance> {
    public static final String NAME = "cambpm_CamHistoricProcessInstanceQueryBuilder";

    @Inject
    private CamPlatform camPlatform;
    @Inject
    private BpmEntityConverter converter;

    @Override
    public boolean supports(Class queryContextClass) {
        return queryContextClass.equals(BpmHistoricProcessInstanceQuery.class);
    }

    @Override
    public void authorization() throws BpmAuthorizationException {
        checkReadPermission(BpmProcessInstance.class);
    }

    @Override
    public HistoricProcessInstanceQuery createQuery() {
        return camPlatform.getProcessEngine().getHistoryService().createHistoricProcessInstanceQuery();
    }

    @Override
    public BpmProcessInstance convert(HistoricProcessInstance object) {
        return converter.toBpmProcessInstance(object);
    }
}
