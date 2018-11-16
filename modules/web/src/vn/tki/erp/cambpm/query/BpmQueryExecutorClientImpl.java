package vn.tki.erp.cambpm.query;

import com.haulmont.cuba.core.entity.Entity;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.service.BpmQueryService;

import javax.inject.Inject;
import java.util.List;

@Component(BpmQueryExecutor.NAME)
public class BpmQueryExecutorClientImpl implements BpmQueryExecutor {
    @Inject
    private BpmQueryService bpmQueryService;

    @Override
    public long count(BpmQuery queryContext) {
        return bpmQueryService.getCount(queryContext);
    }

    @Override
    public <T extends Entity<?>> List<T> list(BpmQuery queryContext) {
        return bpmQueryService.getList(queryContext);
    }

    @Override
    public <T extends Entity<?>> List<T> listPage(BpmQuery queryContext, int maxResults, int firstResult) {
        return bpmQueryService.getList(queryContext, maxResults, firstResult);
    }

    @Override
    public <T extends Entity<?>> T singleResult(BpmQuery queryContext) {
        return bpmQueryService.getItem(queryContext);
    }
}
