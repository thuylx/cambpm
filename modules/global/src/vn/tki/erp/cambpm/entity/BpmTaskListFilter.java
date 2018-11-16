package vn.tki.erp.cambpm.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamePattern("%s|parameter")
@Table(name = "CAMBPM_BPM_TASK_LIST_FILTER")
@Entity(name = "cambpm$BpmTaskListFilter")
public class BpmTaskListFilter extends StandardEntity {
    private static final long serialVersionUID = -8185078286222386502L;

    @NotNull
    @Column(name = "PARAMETER_", nullable = false)
    protected String parameter;

    @NotNull
    @Column(name = "VALUE_", nullable = false)
    protected String value;

    @Column(name = "ORDER_")
    protected Integer order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BPM_TASK_LIST_ID")
    protected BpmTaskList bpmTaskList;

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getOrder() {
        return order;
    }


    public void setBpmTaskList(BpmTaskList bpmTaskList) {
        this.bpmTaskList = bpmTaskList;
    }

    public BpmTaskList getBpmTaskList() {
        return bpmTaskList;
    }


    public void setParameter(BpmTaskQueryParam parameter) {
        this.parameter = parameter == null ? null : parameter.getId();
    }

    public BpmTaskQueryParam getParameter() {
        return parameter == null ? null : BpmTaskQueryParam.fromId(parameter);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}