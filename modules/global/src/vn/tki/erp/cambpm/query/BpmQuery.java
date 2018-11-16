package vn.tki.erp.cambpm.query;

import com.haulmont.cuba.core.entity.Entity;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.variable.type.ValueType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BpmQuery extends Serializable {
    List<BpmQueryConfig> getConfigs();

    void add(String property);

    void addString(String property, String value);

    void addInteger(String property, Integer value);

    void addDate(String property, Date value);

    void addSet(String property, Set value);

    void addList(String property, List value);

    void addStringArray(String property, String... value);

    void addStringObject(String property, String stringParam, Object objectParam);

    void addStringString(String property, String stringValue1, String stringValue2);

    void addStringValueType(String property, String stringValue, ValueType value);

    void addDelegationState(String property, DelegationState value);

    long count();

    <T extends Entity<?>> List<T> list();

    <T extends Entity<?>> List<T> listPage(int maxResults, int firstResult);

    <T extends Entity<?>> T singleResult();
}
