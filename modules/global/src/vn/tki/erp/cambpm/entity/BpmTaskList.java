package vn.tki.erp.cambpm.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.List;

@NamePattern("%s|name")
@Table(name = "CAMBPM_BPM_TASK_LIST")
@Entity(name = "cambpm$BpmTaskList")
public class BpmTaskList extends StandardEntity {
    private static final long serialVersionUID = -4215561039790759067L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "DESCTIPTION")
    protected String desctiption;

    @Column(name = "ORDER_")
    protected Integer order;

    @OrderBy("order ASC")
    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "bpmTaskList")
    protected List<BpmTaskListFilter> parameters;

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getOrder() {
        return order;
    }


    public void setParameters(List<BpmTaskListFilter> parameters) {
        this.parameters = parameters;
    }

    public List<BpmTaskListFilter> getParameters() {
        return parameters;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public String getDesctiption() {
        return desctiption;
    }
}