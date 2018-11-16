package vn.tki.erp.cambpm.service;

import com.haulmont.cuba.core.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.tki.erp.cambpm.query.BpmQuery;
import vn.tki.erp.cambpm.query.BpmQueryExecutor;

import javax.inject.Inject;
import java.util.List;

@Service(BpmQueryService.NAME)
public class BpmQueryServiceBean implements BpmQueryService {
    @Inject
    private BpmQueryExecutor queryExecutor;

    private Logger log = LoggerFactory.getLogger(BpmQueryServiceBean.class);

    @Override
    public <T extends BpmQuery, U extends Entity<?>> List<U> getList(T queryContext) {
        return queryExecutor.list(queryContext);
    }

    @Override
    public <T extends BpmQuery, U extends Entity<?>> List<U> getList(T queryContext, int maxResults, int firstResult) {
        return queryExecutor.listPage(queryContext, maxResults, firstResult);
    }

    @Override
    public <T extends BpmQuery> long getCount(T queryContext) {
        return queryExecutor.count(queryContext);
    }

    @Override
    public <T extends BpmQuery, U extends Entity<?>> U getItem(T queryContext) {
        return queryExecutor.singleResult(queryContext);
    }
}