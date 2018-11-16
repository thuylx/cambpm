package vn.tki.erp.cambpm.service;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.camunda.bpm.engine.ProcessEngine;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import vn.tki.erp.cambpm.CambpmTestContainer;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.core.impl.CamPlatformImpl;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.query.BpmTaskQuery;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BpmQueryServiceBeanTest {

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

    @Test
    public void getList() {
        BpmTaskQuery queryContext = new BpmTaskQuery();
//        BpmQueryExecutor query = AppBeans.get(BpmQueryExecutor.NAME);
//        List<BpmTask> list = query.list(queryContext);
        List<BpmTask> list = queryContext.list();
        assertEquals(5, list.size());
    }
}