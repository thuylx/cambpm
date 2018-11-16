package vn.tki.erp.cambpm.core.impl;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.core.sys.events.AppContextStartedEvent;
import com.haulmont.cuba.security.entity.User;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.persistence.StrongUuidGenerator;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.spin.plugin.impl.SpinProcessEnginePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import vn.tki.erp.cambpm.core.BpmExposedBean;
import vn.tki.erp.cambpm.core.CamPlatform;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(CamPlatform.NAME)
public class CamPlatformImpl implements CamPlatform {
    private ProcessEngine processEngine;

    @Inject
    private DataSource dataSource;
    @Inject
    private PlatformTransactionManager transactionManager;
    @Inject
    private UserSessionSource userSessionSource;

    private final Logger log = LoggerFactory.getLogger(CamPlatformImpl.class);

    @EventListener
    private void appStarted(AppContextStartedEvent event) {
        initialize();
    }

    public ProcessEngine getProcessEngine(){
        if (userSessionSource.checkCurrentUserSession()) {
            User user =  userSessionSource.getUserSession().getUser();
            processEngine.getIdentityService().setAuthenticatedUserId(user.getLogin());
        } else {
            processEngine.getIdentityService().clearAuthentication();
            log.warn("CapmPlatform cannot get user session to authenticate. Authentication cleared");
        }
        return processEngine;
    }

    public void initialize() {
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();

        config.setProcessEngineName("default");
        config.setDataSource(dataSource);
        config.setTransactionManager(transactionManager);

        config.setDatabaseSchemaUpdate("true");
        config.setHistory(SpringProcessEngineConfiguration.HISTORY_FULL);
        config.setJobExecutorActivate(true);
        config.setIdGenerator(new StrongUuidGenerator());
        config.setEnableExpressionsInAdhocQueries(true);

        //Add plugins
        List<ProcessEnginePlugin> processEnginePlugins = new ArrayList<>();
        //Spin serializer
        SpinProcessEnginePlugin spinPlugin = new SpinProcessEnginePlugin();
        processEnginePlugins.add(spinPlugin);
        config.setProcessEnginePlugins(processEnginePlugins);

        //Default serialization format (JSON)
        config.setDefaultSerializationFormat("application/json");

        //Expose beans
        Map<Object, Object> exposedBeans = new HashMap<>();
        AppBeans.getAll(BpmExposedBean.class).forEach(exposedBeans::put);
        config.setBeans(exposedBeans);

        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(config);
        processEngineFactoryBean.setApplicationContext(AppContext.getApplicationContext());
        try {
            processEngine = processEngineFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
