package vn.tki.erp.cambpm.query;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.variable.type.ValueType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BpmQueryImpl<T extends BpmQuery, U extends Entity<?>> implements BpmQuery {
    private List<BpmQueryConfig> configs = new ArrayList<>();

    @Override
    public List<BpmQueryConfig> getConfigs() {
        return configs;
    }

    protected void add(String property, Class type, Object value) {
        configs.add(new BpmQueryConfig(property, new Class[]{type}, new Object[]{value}));
    }

    @Override
    public void add(String property) {
        configs.add(new BpmQueryConfig(property, null, null));
    }

    @Override
    public void addString(String property, String value){
        add(property, String.class, value);
    }

    @Override
    public void addInteger(String property, Integer value) {
        add(property, Integer.class, value);
    }

    @Override
    public void addDate(String property, Date value) {
        add(property, Date.class, value);
    }

    @Override
    public void addSet(String property, Set value) {
        add(property, Set.class, value);
    }

    @Override
    public void addList(String property, List value) {
        add(property, List.class, value);
    }

    @Override
    public void addStringArray(String property, String... value) {
        add(property, String[].class, value);
    }

    @Override
    public void addStringObject(String property, String stringValue, Object objectValue) {
        configs.add(new BpmQueryConfig(property, new Class[]{String.class, Object.class}, new Object[]{stringValue, objectValue}));
    }

    @Override
    public void addStringString(String property, String stringValue1, String stringValue2) {
        configs.add(new BpmQueryConfig(property, new Class[]{String.class, String.class}, new Object[]{stringValue1, stringValue2}));
    }

    @Override
    public void addStringValueType(String property, String stringValue, ValueType value) {
        configs.add(new BpmQueryConfig(property, new Class[]{String.class, ValueType.class}, new Object[]{stringValue, value}));
    }

    @Override
    public void addDelegationState(String property, DelegationState value) {
        configs.add(new BpmQueryConfig(property, new Class[]{DelegationState.class}, new Object[]{value}));
    }

    public T asc() {
        add("asc");
        return (T) this;
    }

    public T desc() {
        add("desc");
        return (T) this;
    }

    @Override
    public long count() {
        BpmQueryExecutor queryExecutor = AppBeans.get(BpmQueryExecutor.NAME);
        return queryExecutor.count(this);
    }

    @Override
    public List<U> list() {
        BpmQueryExecutor queryExecutor = AppBeans.get(BpmQueryExecutor.NAME);
        return queryExecutor.list(this);
    }

    @Override
    public List<U> listPage(int maxResults, int firstResult) {
        BpmQueryExecutor queryExecutor = AppBeans.get(BpmQueryExecutor.NAME);
        return queryExecutor.listPage(this, maxResults, firstResult);
    }

    @Override
    public U singleResult() {
        BpmQueryExecutor queryExecutor = AppBeans.get(BpmQueryExecutor.NAME);
        return queryExecutor.singleResult(this);
    }
}
