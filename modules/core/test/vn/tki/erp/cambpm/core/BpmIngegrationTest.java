package vn.tki.erp.cambpm.core;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import vn.tki.erp.cambpm.CambpmTestContainer;
import vn.tki.erp.cambpm.core.impl.CamPlatformImpl;

@Deployment
public class BpmIngegrationTest {
    @ClassRule
    public static CambpmTestContainer cont = CambpmTestContainer.Common.INSTANCE;

    protected Metadata metadata;
    protected Persistence persistence;
    protected DataManager dataManager;
    protected ProcessEngine processEngine;

    @Before
    public void setUp() throws Exception {
        metadata = cont.metadata();
        persistence = cont.persistence();
        dataManager = AppBeans.get(DataManager.class);
        CamPlatformImpl camPlatform = AppBeans.get(CamPlatform.NAME);
        camPlatform.initialize();
        processEngine = AppBeans.get(CamPlatformImpl.class).getProcessEngine();
    }

    @After
    public void tearDown() throws Exception {
    }
}