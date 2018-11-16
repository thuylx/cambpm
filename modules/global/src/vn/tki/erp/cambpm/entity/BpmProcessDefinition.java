package vn.tki.erp.cambpm.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import vn.tki.erp.cambpm.query.BpmProcessDefinitionQuery;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamePattern("%s (v)|name")
@Table(name = "CAMBPM_BPM_PROCESS_DEFINITION")
@Entity(name = "cambpm$BpmProcessDefinition")
public class BpmProcessDefinition extends StandardEntity {
    private static final long serialVersionUID = -2620262200277516177L;

    @NotNull
    @Column(name = "KEY_", nullable = false, unique = true)
    protected String key;

    @Column(name = "NAME")
    protected String name;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @Composition
    @OneToMany(mappedBy = "bpmProcessDefinition")
    protected List<BpmProcessDefinitionEntity> entities;

    @Transient
    @MetaProperty
    protected List<BpmProcessDefinitionVersion> versions;


    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open", "clear"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BPM_PROCESS_DEFINITION_GROUP_ID")
    protected BpmProcessDefinitionGroup bpmProcessDefinitionGroup;

    @Column(name = "ACTIVE")
    protected Boolean active;

    @Transient
    @MetaProperty
    protected Boolean deployed;

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }


    public void setDeployed(Boolean deployed) {
        this.deployed = deployed;
    }

    public Boolean getDeployed() {
        if (deployed == null) {
            BpmProcessDefinitionQuery query = new BpmProcessDefinitionQuery();
            long versionNumber = query.processDefinitionKey(key).count();
            deployed = versionNumber>0;
        }
        return deployed;
    }


    public List<BpmProcessDefinitionVersion> getVersions() {
        if (versions == null) {
            BpmProcessDefinitionQuery query = new BpmProcessDefinitionQuery();
            query.processDefinitionKey(key);
            versions = query.list();
        }
        return versions;
    }

    public void setVersions(List<BpmProcessDefinitionVersion> versions) {
        this.versions = versions;
    }

    public void setBpmProcessDefinitionGroup(BpmProcessDefinitionGroup bpmProcessDefinitionGroup) {
        this.bpmProcessDefinitionGroup = bpmProcessDefinitionGroup;
    }

    public BpmProcessDefinitionGroup getBpmProcessDefinitionGroup() {
        return bpmProcessDefinitionGroup;
    }


    public void setEntities(List<BpmProcessDefinitionEntity> entities) {
        this.entities = entities;
    }

    public List<BpmProcessDefinitionEntity> getEntities() {
        return entities;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}