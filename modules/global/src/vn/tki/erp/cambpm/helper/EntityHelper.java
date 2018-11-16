package vn.tki.erp.cambpm.helper;

import com.google.common.base.Strings;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.*;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.entity.BpmProcessInstance;
import vn.tki.erp.cambpm.entity.BpmTask;

import java.util.Optional;

@Component(EntityHelper.NAME)
public class EntityHelper {
    public static final String NAME = "cambpm_EntityHelper";

    public <T extends Entity<?>> T getEntityByKey(String entityKey) {
        if (Strings.isNullOrEmpty(entityKey))
            return null;

        EntityLoadInfoBuilder entityLoadInfoBuilder = AppBeans.get(EntityLoadInfoBuilder.class);
        EntityLoadInfo entityLoadInfo = entityLoadInfoBuilder.parse(entityKey);

        if (entityLoadInfo == null) {
            return null;
        }

        DataManager dataManager = AppBeans.get(DataManager.class);
        //noinspection unchecked
        Optional optional = dataManager.load(entityLoadInfo.getMetaClass().getJavaClass())
                .id(entityLoadInfo.getId())
                .view(View.LOCAL)
                .optional();

        if (optional.isPresent()) {
            return (T) optional.get();
        } else {
            return null;
        }
    }

    public <T extends Entity<?>> T getEntityByKey(String entityKey, String viewName) {
        if (Strings.isNullOrEmpty(entityKey))
            return null;

        EntityLoadInfoBuilder entityLoadInfoBuilder = AppBeans.get(EntityLoadInfoBuilder.class);
        EntityLoadInfo entityLoadInfo = entityLoadInfoBuilder.parse(entityKey);

        if (entityLoadInfo == null) {
            return null;
        }

        DataManager dataManager = AppBeans.get(DataManager.class);
        //noinspection unchecked
        Optional optional = dataManager.load(entityLoadInfo.getMetaClass().getJavaClass())
                .id(entityLoadInfo.getId())
                .view(viewName)
                .optional();

        if (optional.isPresent()) {
            return (T) optional.get();
        } else {
            return null;
        }
    }

    public <T extends Entity<?>> T getEntityByKey(String entityKey, View view) {
        if (Strings.isNullOrEmpty(entityKey))
            return null;

        EntityLoadInfoBuilder entityLoadInfoBuilder = AppBeans.get(EntityLoadInfoBuilder.class);
        EntityLoadInfo entityLoadInfo = entityLoadInfoBuilder.parse(entityKey);

        if (entityLoadInfo == null) {
            return null;
        }

        DataManager dataManager = AppBeans.get(DataManager.class);
        //noinspection unchecked
        Optional optional = dataManager.load(entityLoadInfo.getMetaClass().getJavaClass())
                .id(entityLoadInfo.getId())
                .view(view)
                .optional();

        if (optional.isPresent()) {
            return (T) optional.get();
        } else {
            return null;
        }
    }

    public <T extends Entity<?>> T getEntity(BpmProcessInstance processInstance) {
        String entityKey = processInstance.getBusinessKey();
        if (Strings.isNullOrEmpty(entityKey)) {
            return null;
        }

        return getEntityByKey(entityKey);
    }

    public <T extends Entity<?>> T getEntity(BpmProcessInstance processInstance, String viewName) {
        String entityKey = processInstance.getBusinessKey();
        if (Strings.isNullOrEmpty(entityKey)) {
            return null;
        }
        return getEntityByKey(entityKey, viewName);
    }

    public <T extends Entity<?>> T getEntity(BpmProcessInstance processInstance, View view) {
        String entityKey = processInstance.getBusinessKey();
        if (Strings.isNullOrEmpty(entityKey)) {
            return null;
        }
        return getEntityByKey(entityKey, view);
    }

    public <T extends Entity<?>> T getEntity(BpmTask bpmTask) {
        if (bpmTask.getProcessInstance() == null || Strings.isNullOrEmpty(bpmTask.getProcessInstance().getBusinessKey())) {
            return null;
        }

        String entityKey = bpmTask.getProcessInstance().getBusinessKey();
        if (Strings.isNullOrEmpty(entityKey)) {
            return null;
        }

        return getEntityByKey(entityKey);
    }

    public <T extends Entity<?>> T getEntity(BpmTask bpmTask, String viewName) {
        if (bpmTask.getProcessInstance() == null || Strings.isNullOrEmpty(bpmTask.getProcessInstance().getBusinessKey())) {
            return null;
        }

        String entityKey = bpmTask.getProcessInstance().getBusinessKey();
        if (Strings.isNullOrEmpty(entityKey)) {
            return null;
        }
        return getEntityByKey(entityKey, viewName);
    }

    public <T extends Entity<?>> T getEntity(BpmTask bpmTask, View view) {
        if (bpmTask.getProcessInstance() == null || Strings.isNullOrEmpty(bpmTask.getProcessInstance().getBusinessKey())) {
            return null;
        }

        String entityKey = bpmTask.getProcessInstance().getBusinessKey();
        if (Strings.isNullOrEmpty(entityKey)) {
            return null;
        }
        return getEntityByKey(entityKey, view);
    }
}
