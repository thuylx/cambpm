package vn.tki.erp.cambpm.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@NamePattern("%s|entityName")
@Table(name = "CAMBPM_BPM_PROCESS_DEFINITION_ENTITY")
@Entity(name = "cambpm$BpmProcessDefinitionEntity")
public class BpmProcessDefinitionEntity extends StandardEntity {
    private static final long serialVersionUID = 3691916459753012621L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BPM_PROCESS_DEFINITION_ID")
    protected BpmProcessDefinition bpmProcessDefinition;

    @Column(name = "ENTITY_NAME")
    protected String entityName;


    public void setBpmProcessDefinition(BpmProcessDefinition bpmProcessDefinition) {
        this.bpmProcessDefinition = bpmProcessDefinition;
    }

    public BpmProcessDefinition getBpmProcessDefinition() {
        return bpmProcessDefinition;
    }


    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }


}