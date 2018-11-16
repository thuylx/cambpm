package vn.tki.erp.cambpm.entity;

import com.google.common.base.Strings;
import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseGenericIdEntity;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.annotation.SystemLevel;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.security.entity.User;
import org.camunda.bpm.engine.EntityTypes;
import vn.tki.erp.cambpm.helper.EntityHelper;
import vn.tki.erp.cambpm.query.BpmProcessDefinitionQuery;
import vn.tki.erp.cambpm.query.BpmProcessInstanceQuery;
import vn.tki.erp.cambpm.query.BpmUserOperationLogQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NamePattern("%s|name")
@MetaClass(name = "cambpm$BpmTask")
public class BpmTask extends BaseUuidEntity {
    private static final long serialVersionUID = -55334087613960520L;

    @MetaProperty
    protected String name;

    @MetaProperty
    protected User assignee;

    @MetaProperty
    protected User owner;

    @MetaProperty
    protected String description;

    @MetaProperty
    protected Integer priority;

    @MetaProperty
    protected Date startTime;

    @MetaProperty
    protected Date endTime;

    @MetaProperty
    protected String duration;

    @MetaProperty
    protected Date dueDate;

    @MetaProperty
    protected Date followUpDate;

    @MetaProperty
    protected String candidateRoles;

    @SystemLevel
    @MetaProperty
    protected String formKey;


    @MetaProperty
    protected String taskDefinitionKey;

    @SystemLevel
    @MetaProperty
    protected UUID processInstanceId;

    @MetaProperty
    protected BpmProcessInstance processInstance;

    @SystemLevel
    @MetaProperty
    protected String processDefinitionVersionId;

    @MetaProperty
    protected BpmProcessDefinitionVersion bpmProcessDefinitionVersion;


    @MetaProperty
    protected BaseGenericIdEntity entity;

    @MetaProperty
    protected List<BpmUserOperationLogEntry> logEntries;


    public void setProcessInstanceId(UUID processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public UUID getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessDefinitionVersionId(String processDefinitionVersionId) {
        this.processDefinitionVersionId = processDefinitionVersionId;
    }

    public String getProcessDefinitionVersionId() {
        return processDefinitionVersionId;
    }


    public BpmProcessDefinitionVersion getBpmProcessDefinitionVersion() {
        if (bpmProcessDefinitionVersion == null && !Strings.isNullOrEmpty(processDefinitionVersionId)) {
            BpmProcessDefinitionQuery query = new BpmProcessDefinitionQuery();
            bpmProcessDefinitionVersion = query.processDefinitionId(processDefinitionVersionId)
                    .singleResult();
            if (bpmProcessDefinitionVersion == null) {
                bpmProcessDefinitionVersion = new BpmProcessDefinitionVersion();
                bpmProcessDefinitionVersion.setId(processDefinitionVersionId);
            }
        }
        return bpmProcessDefinitionVersion;
    }

    public void setBpmProcessDefinitionVersion(BpmProcessDefinitionVersion bpmProcessDefinitionVersion) {
        this.bpmProcessDefinitionVersion = bpmProcessDefinitionVersion;
    }


    public void setLogEntries(List<BpmUserOperationLogEntry> logEntries) {
        this.logEntries = logEntries;
    }


    public List<BpmUserOperationLogEntry> getLogEntries() {
        BpmUserOperationLogQuery queryContext = new BpmUserOperationLogQuery();

        logEntries = queryContext.entityType(EntityTypes.TASK)
                .taskId(getId().toString())
                .list();
        if (logEntries == null) {
            logEntries = new ArrayList<>();
        }

        return logEntries;
    }


    public void setEntity(BaseGenericIdEntity entity) {
        this.entity = entity;
    }

    public BaseGenericIdEntity getEntity() {
        if (entity == null && !Strings.isNullOrEmpty(this.getProcessInstance().getBusinessKey())) {
            EntityHelper entityHelper = AppBeans.get(EntityHelper.class);
            entity = (BaseGenericIdEntity) entityHelper.getEntityByKey(this.getProcessInstance().getBusinessKey());
        }
        return entity;
    }


    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public void setProcessInstance(BpmProcessInstance processInstance) {
        this.processInstance = processInstance;
    }

    public BpmProcessInstance getProcessInstance() {
        if (processInstance == null && processInstanceId != null) {
            BpmProcessInstanceQuery query = new BpmProcessInstanceQuery();
            processInstance = query.processInstanceId(processInstanceId.toString())
                    .singleResult();
            if (processInstance == null) {
                processInstance = new BpmProcessInstance();
                processInstance.setId(processInstanceId);
            }
        }
        return processInstance;
    }


    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public void setCandidateRoles(String candidateRoles) {
        this.candidateRoles = candidateRoles;
    }

    public String getCandidateRoles() {
        return candidateRoles;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }


    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getFormKey() {
        return formKey;
    }


    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }


    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }

    public Date getFollowUpDate() {
        return followUpDate;
    }
}