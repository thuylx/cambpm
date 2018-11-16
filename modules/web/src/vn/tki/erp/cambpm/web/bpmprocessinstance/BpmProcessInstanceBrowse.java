package vn.tki.erp.cambpm.web.bpmprocessinstance;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.EntityLoadInfo;
import com.haulmont.cuba.core.global.EntityLoadInfoBuilder;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.LinkButton;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import vn.tki.erp.cambpm.config.BpmConfig;
import vn.tki.erp.cambpm.entity.BpmProcessInstance;
import vn.tki.erp.cambpm.query.BpmHistoricProcessInstanceQuery;

import javax.inject.Inject;
import java.util.Map;

public class BpmProcessInstanceBrowse extends AbstractLookup {
    public static final String ID = "cambpm$BpmProcessInstance.browse";
    public interface WinParam{
        String ENTITY_PARAM_NAME = "entity";
    }

    @WindowParam(name = WinParam.ENTITY_PARAM_NAME)
    private Entity entity;

    @Inject
    private BpmProcessInstanceDatasource bpmProcessInstancesDs;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private Configuration configuration;
    @Inject
    private EntityLoadInfoBuilder loadInfoBuilder;

    @Override
    public void init(Map<String, Object> params){
        BpmConfig bpmConfig = configuration.getConfig(BpmConfig.class);
        bpmProcessInstancesDs.setMaxResults(bpmConfig.getMaxResults());

        BpmHistoricProcessInstanceQuery processInstanceQuery = new BpmHistoricProcessInstanceQuery();
        if (entity != null) {
            EntityLoadInfo loadInfo = loadInfoBuilder.create(entity);
            processInstanceQuery.processInstanceBusinessKey(loadInfo.toString());
        }
        bpmProcessInstancesDs.setProcessInstanceQuery(processInstanceQuery);
    }

    public Component generateIsCompletedCell(BpmProcessInstance entity) {
        LinkButton linkButton = componentsFactory.createComponent(LinkButton.class);
        linkButton.setId("isCompleted:"+entity.getId().toString());
        linkButton.setFocusable(false);

        if (entity.getEndTime() != null) {
            linkButton.setIcon("font-icon:CHECK_SQUARE");
        } else {
            linkButton.setIcon("font-icon:SQUARE");
        }

        return linkButton;
    }
}