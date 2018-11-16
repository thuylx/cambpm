package vn.tki.erp.cambpm.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@NamePattern("%s|name")
@Table(name = "CAMBPM_BPM_PROCESS_DEFINITION_GROUP")
@Entity(name = "cambpm$BpmProcessDefinitionGroup")
public class BpmProcessDefinitionGroup extends StandardEntity {
    private static final long serialVersionUID = -9071950915488990509L;

    @Column(name = "NAME")
    protected String name;

    @OneToMany(mappedBy = "bpmProcessDefinitionGroup")
    protected List<BpmProcessDefinition> processDefinitions;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProcessDefinitions(List<BpmProcessDefinition> processDefinitions) {
        this.processDefinitions = processDefinitions;
    }

    public List<BpmProcessDefinition> getProcessDefinitions() {
        return processDefinitions;
    }


}