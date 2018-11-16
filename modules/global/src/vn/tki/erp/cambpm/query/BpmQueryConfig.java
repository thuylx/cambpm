package vn.tki.erp.cambpm.query;

import java.io.Serializable;

public class BpmQueryConfig implements Serializable {
    private String propertyName;
    private Class[] dataTypes;
    private Object[] values;

    public BpmQueryConfig(String propertyName, Class[] dataTypes, Object[] values) {
        this.propertyName = propertyName;
        this.dataTypes = dataTypes;
        this.values = values;
    }

    public BpmQueryConfig() {
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Class[] getDataTypes() {
        return dataTypes;
    }

    public void setDataTypes(Class[] dataTypes) {
        this.dataTypes = dataTypes;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
