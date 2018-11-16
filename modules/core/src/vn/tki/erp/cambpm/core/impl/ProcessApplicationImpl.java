package vn.tki.erp.cambpm.core.impl;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import vn.tki.erp.cambpm.core.ProcessApplication;
import vn.tki.erp.cambpm.helper.EntityHelper;
import vn.tki.erp.cambpm.helper.UserHelper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is loaded when cuba application start with the bean name of "app".<br>
 * It provides methods to access cuba application from Bpm engine through expression in bpmn file.<br>
 * For example, in user task form field, we can get the entity property like this: ${app.getEntity(execution).getName()}<br>
 * or current logged in username can retrieve like this: ${app.getCurrentUserNameName()}
 */
@Component(ProcessApplication.NAME)
public class ProcessApplicationImpl implements ProcessApplication {

    @Inject
    private EntityHelper entityHelper;
    @Inject
    private UserHelper userHelper;
    @Inject
    private UserSessionSource userSessionSource;

    /**
     * @return logged in sec$User object
     */
    @Override
    public String getCurrentUserName() {
        if (userSessionSource.checkCurrentUserSession()) {
            return userSessionSource.getUserSession().getUser().getLogin();
        }

        return null;
    }

    /**
     * @return substitute sec$User object
     */
    @Override
    public String getSubstitutedUserName() {
        if (userSessionSource.checkCurrentUserSession()) {
            return userSessionSource.getUserSession().getSubstitutedUser().getLogin();
        }

        return null;
    }

    /**
     * @return logged in or substitute sec$User object
     */
    @Override
    public String getCurrentOrSubstitutedUserName() {
        if (userSessionSource.checkCurrentUserSession()) {
            return userSessionSource.getUserSession().getCurrentOrSubstitutedUser().getLogin();
        }

        return null;
    }

    /**
     * @return current UserSession object. UserSession provides information of current user. See Cuba Platform document for more details.
     */
    @Override
    public UserSession getUserSession() {
        if (userSessionSource.checkCurrentUserSession()) {
            return userSessionSource.getUserSession();
        }

        return null;
    }

    /**
     * Example: use roles (not groups) for Camunda Candidate Group
     *
     * @return collection of role name.
     */
    @Override
    public Collection<String> getCurrentUserRoles() {
        if (userSessionSource.checkCurrentUserSession())
            return userSessionSource.getUserSession().getRoles();
        else
            return new ArrayList<>();
    }

    /**
     * @param loginName login of the User object to get
     * @return sec$User object
     */
    @Override
    public User getUser(String loginName) {
        return userHelper.getUserByLoginName(loginName);
    }

    /**
     * @param execution Camunda Delegate Execution. This function normally is used in task form field. Example expression: <br>
     *                  ${app.getEntity(execution).getName()}
     * @return Processing entity object, which entity key stored in business key of the process instance.
     */
    @Override
    public Entity<?> getEntity(DelegateExecution execution) {
        return entityHelper.getEntityByKey(execution.getProcessBusinessKey());
    }

    @Override
    public Entity<?> getEntity(DelegateExecution execution, String viewName) {
        return entityHelper.getEntityByKey(execution.getProcessBusinessKey(), viewName);
    }
}
