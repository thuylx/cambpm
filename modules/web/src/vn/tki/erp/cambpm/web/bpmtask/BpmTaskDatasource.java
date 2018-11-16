package vn.tki.erp.cambpm.web.bpmtask;

import com.haulmont.cuba.gui.data.impl.CustomGroupDatasource;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.query.BpmTaskQuery;

import java.util.*;


public class BpmTaskDatasource extends CustomGroupDatasource<BpmTask, UUID> {

    private BpmTaskQuery bpmTaskQuery;

    public void setBpmTaskQuery(BpmTaskQuery bpmTaskQuery) {
        this.bpmTaskQuery = bpmTaskQuery;
    }

    @Override
    protected Collection<BpmTask> getEntities(Map<String, Object> params) {
        List<BpmTask> bpmTasks;

        if (bpmTaskQuery == null) {
            return new ArrayList<>();
        } else {

            bpmTasks = bpmTaskQuery.listPage(maxResults, firstResult);

            return bpmTasks;
        }
    }

    /**
     * @return Count of all entities
     */
    @Override
    public int getCount() {
        if (bpmTaskQuery != null) {
            return Math.toIntExact(bpmTaskQuery.count());
        } else {
            return 0;
        }
    }
}