package vn.tki.erp.cambpm.core.query;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import org.camunda.bpm.engine.query.Query;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.query.BpmQuery;
import vn.tki.erp.cambpm.query.BpmQueryExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component(BpmQueryExecutor.NAME)
public class BpmQueryExecutorCoreImpl implements BpmQueryExecutor {
    @Override
    public long count(BpmQuery queryContext) {
        AbstractQueryBuilder builder = getQueryBuilder(queryContext);
        try {
            builder.authorization();
            Query query = builder.buildQuery(queryContext);
            return query.count();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public <T extends Entity<?>> List<T> list(BpmQuery queryContext) {
        AbstractQueryBuilder builder = getQueryBuilder(queryContext);
        try {
            builder.authorization();
            Query query = builder.buildQuery(queryContext);
            return (List) query.list().stream().map(builder::convert).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public <T extends Entity<?>> List<T> listPage(BpmQuery queryContext, int maxResults, int firstResult) {
        AbstractQueryBuilder builder = getQueryBuilder(queryContext);
        try {
            builder.authorization();
            Query query = builder.buildQuery(queryContext);
            return (List<T>) query.listPage(firstResult, maxResults).stream().map(o -> builder.convert(o)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public <T extends Entity<?>> T singleResult(BpmQuery queryContext) {
        AbstractQueryBuilder builder = getQueryBuilder(queryContext);
        try {
            builder.authorization();
            Query query = builder.buildQuery(queryContext);
            return (T) builder.convert(query.singleResult());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T extends AbstractQueryBuilder, U extends BpmQuery> T getQueryBuilder(U queryContext) {
        T builder = null;
        for (Map.Entry<String, AbstractQueryBuilder> entry : AppBeans.getAll(AbstractQueryBuilder.class).entrySet()) {
            if (entry.getValue().supports(queryContext.getClass())) {
                builder = (T) entry.getValue();
                break;
            }
        }

        return builder;
    }

}
