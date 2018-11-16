package vn.tki.erp.cambpm.core;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import java.util.Collection;

/**
 * Interface to access common application information from BPMN expression
 */
public interface ProcessApplication extends BpmExposedBean {
    String NAME = "app";

    String getCurrentUserName();

    String getSubstitutedUserName();

    String getCurrentOrSubstitutedUserName();

    UserSession getUserSession();

    Collection<String> getCurrentUserRoles();

    User getUser(String loginName);

    /**
     * @param execution camunda execution. This variable is a built-in variable of camunda engine, hence available for expression.
     * @return Entity object according to process instance business key. Entity will be loaded using _local view by default. In order to use another view, see function below.
     */
    Entity<?> getEntity(DelegateExecution execution);

    /**
     * @param execution camunda execution. This variable is a built-in variable of camunda engine, hence available for expression.
     * @param viewName name of cuba view to load entity
     * @return Entity object according to process instance business key. Entity will be loaded using given view name.
     */
    Entity<?> getEntity(DelegateExecution execution, String viewName);
}
