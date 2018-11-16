package vn.tki.erp.cambpm.service;


import com.haulmont.cuba.core.entity.Entity;
import vn.tki.erp.cambpm.query.BpmQuery;

import java.util.List;

public interface BpmQueryService {
    String NAME = "cambpm_BpmQueryService";

    /*-----------------------------------------------------------------------------------------------------------------
     * COMMON
     */
    /**
     * Get list of U objects which satisfy given bpm query (type T) instance. <br>
     * @param queryContext  Any kind of object which extend BpmQueryImpl class such as BpmTaskQuery, BpmProcessInstanceQuery,...
     * @param <U>   BpmTask, BpmProcessInstance,...
     * @return      List of U objects
     */
    <T extends BpmQuery, U extends Entity<?>> List<U> getList(T queryContext);

    /**
     * Get list of U objects which satisfy given bpm query (type T) instance. <br>
     *      U may be BpmTask, BpmProcessInstance,...<br>
     *      T may be BpmTaskQuery,...
     * @param queryContext  Any kind of object which extend BpmQueryImpl class such as BpmTaskQuery, BpmProcessInstanceQuery,...
     * @param maxResults    number of returned object
     * @param firstResult   index of first returned object
     * @return  List of BpmTask, BpmProcessInstance,...
     */
    <T extends BpmQuery, U extends Entity<?>> List<U> getList(T queryContext, int maxResults, int firstResult);

    /**
     * @param queryContext  Any kind of object which extend BpmQueryImpl class such as BpmTaskQuery, BpmProcessInstanceQuery,...
     */
    <T extends BpmQuery> long getCount(T queryContext);

    /**
     * @param queryContext  Any kind of object which extend BpmQueryImpl class such as BpmTaskQuery, BpmProcessInstanceQuery,...
     * @return Single object of type BpmTask, BpmProcessInstance,...
     */
    <T extends BpmQuery, U extends Entity<?>> U getItem(T queryContext);
}