package vn.tki.erp.cambpm.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseStringIdEntity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;

import javax.persistence.Id;

@NamePattern("%s|name")
@MetaClass(name = "cambpm$BpmProcessDefinitionVersion")
public class BpmProcessDefinitionVersion extends BaseStringIdEntity {
    private static final long serialVersionUID = 577661041559477540L;

    @Id
    @MetaProperty(mandatory = true)
    protected String id;

    @MetaProperty
    protected Integer version;

    @MetaProperty
    protected String versionTag;

    @MetaProperty
    protected String name;

    @MetaProperty
    protected String description;

    @MetaProperty
    protected String processDefinitionKey;

    @MetaProperty
    protected BpmProcessDefinition processDefinition;

    public void setProcessDefinition(BpmProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }

    public BpmProcessDefinition getProcessDefinition() {
        if (processDefinition == null && processDefinitionKey != null) {
            DataManager dataManager = AppBeans.get(DataManager.class);
            processDefinition = dataManager.load(BpmProcessDefinition.class)
                    .query("select e from cambpm$BpmProcessDefinition e where e.key = :key")
                    .parameter("key", processDefinitionKey)
                    .one();
            if (processDefinition == null) {
                Metadata metadata = AppBeans.get(Metadata.class);
                processDefinition = metadata.create(BpmProcessDefinition.class);
            }
        }
        return processDefinition;
    }


    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
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


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag;
    }

    public String getVersionTag() {
        return versionTag;
    }


}