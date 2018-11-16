package vn.tki.erp.cambpm.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.security.entity.User;

import java.util.Date;

@NamePattern("%s|changedProperty")
@MetaClass(name = "cambpm$BpmUserOperationLogEntry")
public class BpmUserOperationLogEntry extends BaseUuidEntity {
    private static final long serialVersionUID = 4409285883517244174L;

    @MetaProperty
    protected String operationType;

    @MetaProperty
    protected User user;

    @MetaProperty
    protected Date time;

    @MetaProperty
    protected String changedProperty;

    @MetaProperty
    protected String oldPropertyValue;

    @MetaProperty
    protected String newPropertyValue;


    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setChangedProperty(String changedProperty) {
        this.changedProperty = changedProperty;
    }

    public String getChangedProperty() {
        return changedProperty;
    }

    public void setOldPropertyValue(String oldPropertyValue) {
        this.oldPropertyValue = oldPropertyValue;
    }

    public String getOldPropertyValue() {
        return oldPropertyValue;
    }

    public void setNewPropertyValue(String newPropertyValue) {
        this.newPropertyValue = newPropertyValue;
    }

    public String getNewPropertyValue() {
        return newPropertyValue;
    }


}