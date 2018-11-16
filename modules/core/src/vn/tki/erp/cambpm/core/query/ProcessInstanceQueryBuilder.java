package vn.tki.erp.cambpm.core.query;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.entity.BpmProcessInstance;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.helper.BpmEntityConverter;
import vn.tki.erp.cambpm.query.BpmProcessInstanceQuery;

import javax.inject.Inject;

@Component(ProcessInstanceQueryBuilder.NAME)
public class ProcessInstanceQueryBuilder extends AbstractQueryBuilder<ProcessInstanceQuery, ProcessInstance, BpmProcessInstance> {
    public static final String NAME = "cambpm_CamProcessInstanceQueryBuilder";

    @Inject
    private CamPlatform camPlatform;
    @Inject
    private BpmEntityConverter converter;

    @Override
    public boolean supports(Class queryContextClass) {
        return queryContextClass.equals(BpmProcessInstanceQuery.class);
    }

    @Override
    public void authorization() throws BpmAuthorizationException {
        checkReadPermission(BpmProcessInstance.class);
    }

    @Override
    public ProcessInstanceQuery createQuery() {
        return camPlatform.getProcessEngine().getRuntimeService().createProcessInstanceQuery();
    }

    @Override
    public BpmProcessInstance convert(ProcessInstance object) {
        return converter.toBpmProcessInstance(object);
    }
}
