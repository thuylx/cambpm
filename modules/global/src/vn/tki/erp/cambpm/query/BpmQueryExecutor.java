package vn.tki.erp.cambpm.query;

import com.haulmont.cuba.core.entity.Entity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BpmQueryExecutor {
    String NAME = "cambpm_BpmQuery";

    long count(BpmQuery queryContext);
    <T extends Entity<?>> List<T> list(BpmQuery queryContext);
    <T extends Entity<?>> List<T> listPage(BpmQuery queryContext, int maxResults, int firstResult);
    <T extends Entity<?>> T singleResult(BpmQuery queryContext);
}
