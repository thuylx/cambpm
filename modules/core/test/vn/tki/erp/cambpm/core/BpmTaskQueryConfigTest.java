package vn.tki.erp.cambpm.core;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.camunda.bpm.engine.ProcessEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import vn.tki.erp.cambpm.CambpmTestContainer;
import vn.tki.erp.cambpm.core.impl.CamPlatformImpl;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.query.BpmQueryExecutor;
import vn.tki.erp.cambpm.query.BpmTaskQuery;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BpmTaskQueryConfigTest {

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
        CamPlatformImpl camPlatform = AppBeans.get(CamPlatformImpl.class);
        camPlatform.initialize();
        processEngine = AppBeans.get(CamPlatformImpl.class).getProcessEngine();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void list() {
        BpmTaskQuery taskQueryContext = new BpmTaskQuery();

        List<BpmTask> bpmTasks = taskQueryContext
                .processInstanceBusinessKey("cambpm$NewEntity-eac7cd6c-498a-529a-2dd6-8e1b88d568d7")
//                .processInstanceId("2de77122-e27a-11e8-8267-1c4d70ecce9e")
                .or()
                .taskAssignee("admin")
                .taskCandidateGroupIn(Arrays.asList("Administrators", "asdf"))
                .includeAssignedTasks()
                .endOr()
                .orderByTaskCreateTime()
                .asc()
                .list();
        assertEquals(2, bpmTasks.size());
    }
}