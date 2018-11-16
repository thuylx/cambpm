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
import vn.tki.erp.cambpm.query.BpmHistoricTaskQuery;
import vn.tki.erp.cambpm.query.BpmProcessDefinitionQuery;
import vn.tki.erp.cambpm.query.BpmUserOperationLogQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamePattern("%s (%s)|bpmProcessDefinitionVersion,startedBy")
@MetaClass(name = "cambpm$BpmProcessInstance")
public class BpmProcessInstance extends BaseUuidEntity {
    private static final long serialVersionUID = -8854307774419090082L;

    @MetaProperty
    protected BpmProcessDefinitionVersion bpmProcessDefinitionVersion;

    @SystemLevel
    @MetaProperty
    protected String processDefinitionVersionId;

    @MetaProperty
    protected User startedBy;

    @MetaProperty
    protected Date startTime;


    @MetaProperty
    protected Date endTime;

    @MetaProperty
    protected String duration;

    @MetaProperty
    protected String businessKey;

    @MetaProperty
    protected String state;

    @MetaProperty
    protected BaseGenericIdEntity entity;

    @MetaProperty
    protected List<BpmTask> tasks;

    @MetaProperty
    protected List<BpmUserOperationLogEntry> logEntries;


    public void setProcessDefinitionVersionId(String processDefinitionVersionId) {
        this.processDefinitionVersionId = processDefinitionVersionId;
    }

    public String getProcessDefinitionVersionId() {
        return processDefinitionVersionId;
    }


    public void setBpmProcessDefinitionVersion(BpmProcessDefinitionVersion bpmProcessDefinitionVersion) {
        this.bpmProcessDefinitionVersion = bpmProcessDefinitionVersion;
    }

    public BpmProcessDefinitionVersion getBpmProcessDefinitionVersion() {
        if (bpmProcessDefinitionVersion == null && !Strings.isNullOrEmpty(processDefinitionVersionId)) {
            BpmProcessDefinitionQuery query = new BpmProcessDefinitionQuery();
            query.processDefinitionId(processDefinitionVersionId);

            bpmProcessDefinitionVersion =  query.singleResult();
            if (bpmProcessDefinitionVersion == null) {
                bpmProcessDefinitionVersion = new BpmProcessDefinitionVersion();
                bpmProcessDefinitionVersion.setId(processDefinitionVersionId);
            }
        }
        return bpmProcessDefinitionVersion;
    }


    public void setLogEntries(List<BpmUserOperationLogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    public List<BpmUserOperationLogEntry> getLogEntries() {
        BpmUserOperationLogQuery query = new BpmUserOperationLogQuery();
        logEntries = query.entityType(EntityTypes.PROCESS_INSTANCE)
                .processInstanceId(getId().toString())
                .list();
        if (logEntries == null) {
            logEntries = new ArrayList<>();
        }
        return logEntries;
    }


    public void setTasks(List<BpmTask> tasks) {
        this.tasks = tasks;
    }

    public List<BpmTask> getTasks() {
        if (tasks == null) {
            BpmHistoricTaskQuery query = new BpmHistoricTaskQuery();
            tasks = query.processInstanceId(getId().toString())
                    .list();
            if (tasks == null) {
                tasks = new ArrayList<>();
            }
        }
        return tasks;
    }


    public void setEntity(BaseGenericIdEntity entity) {
        this.entity = entity;
    }

    public BaseGenericIdEntity getEntity() {
        if (entity == null && !Strings.isNullOrEmpty(businessKey)) {
            EntityHelper entityHelper = AppBeans.get(EntityHelper.class);
            entity = (BaseGenericIdEntity) entityHelper.getEntityByKey(businessKey);
        }
        return entity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public User getStartedBy() {
        return startedBy;
    }

    public void setStartedBy(User startedBy) {
        this.startedBy = startedBy;
    }


    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }


    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }


    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getBusinessKey() {
        return businessKey;
    }
}