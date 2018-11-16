package vn.tki.erp.cambpm.helper;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component(UserHelper.NAME)
public class UserHelper {
    public static final String NAME = "cambpm_UserHelper";

    @Inject
    private DataManager dataManager;

    private Logger log = LoggerFactory.getLogger(UserHelper.class);

    public User getUserByLoginName(String loginName) {
        try {
            return dataManager.load(User.class)
                    .view(View.MINIMAL)
                    .query("select c from sec$User c where c.login = :login")
                    .parameter("login", loginName)
                    .one();
        } catch (IllegalStateException e) {
            if (log.isDebugEnabled())
                log.debug("Cannot get user by login name " + loginName);
            return null;
        }
    }
}
