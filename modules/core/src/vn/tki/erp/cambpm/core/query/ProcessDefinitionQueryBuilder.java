package vn.tki.erp.cambpm.core.query;

import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.entity.BpmProcessDefinitionVersion;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.helper.BpmEntityConverter;
import vn.tki.erp.cambpm.query.BpmProcessDefinitionQuery;

import javax.inject.Inject;

@Component(ProcessDefinitionQueryBuilder.NAME)
public class ProcessDefinitionQueryBuilder extends AbstractQueryBuilder<ProcessDefinitionQuery, ProcessDefinition, BpmProcessDefinitionVersion> {
    public static final String NAME = "cambpm_CamProcessDefinitionQueryBuilder";

    @Inject
    private CamPlatform camPlatform;
    @Inject
    private BpmEntityConverter converter;

    @Override
    public boolean supports(Class queryContextClass) {
        return queryContextClass.equals(BpmProcessDefinitionQuery.class);
    }

    @Override
    public void authorization() throws BpmAuthorizationException {
        checkReadPermission(BpmProcessDefinitionVersion.class);
    }

    @Override
    public ProcessDefinitionQuery createQuery() {
        return camPlatform.getProcessEngine().getRepositoryService().createProcessDefinitionQuery();
    }

    @Override
    public BpmProcessDefinitionVersion convert(ProcessDefinition object) {
        return converter.toBpmProcessDefinitionVersion(object);
    }
}
