package vn.tki.erp.cambpm.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|name")
@Table(name = "CAMBPM_NEW_ENTITY")
@Entity(name = "cambpm$NewEntity")
public class NewEntity extends StandardEntity {
    private static final long serialVersionUID = 7615110991659198618L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "REASON")
    protected String reason;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }


}