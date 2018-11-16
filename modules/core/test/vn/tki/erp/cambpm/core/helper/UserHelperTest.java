package vn.tki.erp.cambpm.core.helper;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import vn.tki.erp.cambpm.CambpmTestContainer;
import vn.tki.erp.cambpm.helper.UserHelper;

import static org.junit.Assert.assertEquals;

public class UserHelperTest {

    @ClassRule
    public static CambpmTestContainer cont = CambpmTestContainer.Common.INSTANCE;

    private Metadata metadata;
    private Persistence persistence;
    private DataManager dataManager;

    @Before
    public void setUp() {
        metadata = cont.metadata();
        persistence = cont.persistence();
        dataManager = AppBeans.get(DataManager.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getUserByLoginName() {
        UserHelper userHelper = AppBeans.get(UserHelper.class);
        User user = userHelper.getUserByLoginName("admin");
        assertEquals("Administrator", user.getName());
    }

}