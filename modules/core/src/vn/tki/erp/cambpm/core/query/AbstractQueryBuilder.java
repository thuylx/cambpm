package vn.tki.erp.cambpm.core.query;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.security.entity.EntityOp;
import org.camunda.bpm.engine.query.Query;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.query.BpmQuery;
import vn.tki.erp.cambpm.query.BpmQueryConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class AbstractQueryBuilder<T extends Query<?,?>, U, V extends Entity<?>> {
    void checkReadPermission(Class bpmClass) throws BpmAuthorizationException {
        Security security = AppBeans.get(Security.class);
        if (!security.isEntityOpPermitted(bpmClass, EntityOp.READ)) {
            throw new BpmAuthorizationException(String.format("Query %s", bpmClass.getName()), String.format("READ  entity", bpmClass.getName()));
        }
    }

    public abstract boolean supports(Class queryContextClass);

    public abstract void authorization() throws BpmAuthorizationException;

    protected abstract T createQuery();

    public T buildQuery(BpmQuery queryContext) {
        T query = createQuery();
        preConfigQuery(query, queryContext);
        configQuery(query, queryContext);
        postConfigQuery(query, queryContext);

        return query;
    }

    protected void preConfigQuery(T camQuery, BpmQuery queryContext) {
    }

    private void configQuery(Query query, BpmQuery queryContext) {
        List<BpmQueryConfig> configs = queryContext.getConfigs();
        for (BpmQueryConfig config : configs) {
            try {
                Method method = query.getClass().getMethod(config.getPropertyName(), config.getDataTypes());
                query = (Query) method.invoke(query, config.getValues());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    protected void postConfigQuery(T camQuery, BpmQuery queryContext) {
    }

    public abstract V convert(U object);
}
