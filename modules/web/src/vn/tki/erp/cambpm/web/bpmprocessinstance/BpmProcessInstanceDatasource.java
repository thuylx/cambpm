package vn.tki.erp.cambpm.web.bpmprocessinstance;

import com.haulmont.cuba.gui.data.impl.CustomGroupDatasource;
import vn.tki.erp.cambpm.entity.BpmProcessInstance;
import vn.tki.erp.cambpm.query.BpmHistoricProcessInstanceQuery;

import java.util.*;


public class BpmProcessInstanceDatasource extends CustomGroupDatasource<BpmProcessInstance, UUID> {

    private BpmHistoricProcessInstanceQuery processInstanceQuery;

    public BpmHistoricProcessInstanceQuery getProcessInstanceQuery() {
        return processInstanceQuery;
    }

    public void setProcessInstanceQuery(BpmHistoricProcessInstanceQuery processInstanceQuery) {
        this.processInstanceQuery = processInstanceQuery;
    }

    @Override
    protected Collection<BpmProcessInstance> getEntities(Map<String, Object> params) {

        if (processInstanceQuery == null) {
            return new ArrayList<>();
        }

        List<BpmProcessInstance> processInstances = processInstanceQuery.listPage(maxResults, firstResult);

        return processInstances;
    }

    /**
     * @return Count of all entities
     */
    @Override
    public int getCount() {
        if (processInstanceQuery == null) {
            return 0;
        }

        return Math.toIntExact(processInstanceQuery.count());
    }
}