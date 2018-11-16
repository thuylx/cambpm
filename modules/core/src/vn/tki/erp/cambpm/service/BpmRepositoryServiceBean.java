package vn.tki.erp.cambpm.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.security.entity.EntityOp;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.entity.BpmProcessDefinition;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.helper.BpmEntityConverter;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service(BpmRepositoryService.NAME)
public class BpmRepositoryServiceBean implements BpmRepositoryService {
    @Inject
    private CamPlatform camPlatform;
    @Inject
    private Security security;
    @Inject
    private Persistence persistence;
    @Inject
    private BpmEntityConverter entityConverter;

    // create logger
    private final Logger log = LoggerFactory.getLogger(BpmRepositoryServiceBean.class);

    @Override
    public void deployProcessDefinition(String deploymentName, String resourceName, File resourceFile) {
        if (!security.isEntityOpPermitted(BpmProcessDefinition.class, EntityOp.CREATE)) {
            throw new BpmAuthorizationException("Deploy process", "Create BpmProcessDefinition");
        }

        DeploymentBuilder deploymentBuilder = camPlatform.getProcessEngine().getRepositoryService().createDeployment()
                .name(deploymentName);
        try {
            deploymentBuilder.addInputStream(resourceName, new FileInputStream(resourceFile));
            Deployment deployment = deploymentBuilder.deploy();

            syncProcessDefinition(deployment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void syncProcessDefinition() {
        if (!security.isEntityOpPermitted(BpmProcessDefinition.class, EntityOp.UPDATE)) {
            throw new BpmAuthorizationException("Sync process definition", "Update BpmProcessDefinition");
        }

        syncProcessDefinition(null);
    }

    @Override
    public String getProcessDefinitionDiagram(String processDefinitionVersionId) {
        InputStream inputStream = getProcessDiagram(processDefinitionVersionId);
        try {
            return IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            return null;
        }
    }

    private InputStream getProcessDiagram(String processDefinitionVersionId) {
        return camPlatform.getProcessEngine().getRepositoryService().getProcessModel(processDefinitionVersionId);
    }

    private void syncProcessDefinition(@Nullable Deployment deployment) {
        List<ProcessDefinition> processDefinitions;
        if (deployment != null) {
            processDefinitions = camPlatform.getProcessEngine().getRepositoryService()
                    .createProcessDefinitionQuery()
                    .deploymentId(deployment.getId())
                    .active()
                    .list();
        } else {
            processDefinitions = camPlatform.getProcessEngine().getRepositoryService()
                    .createProcessDefinitionQuery()
                    .active()
                    .latestVersion()
                    .list();
        }

        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            for (ProcessDefinition processDefinition : processDefinitions) {
                TypedQuery<BpmProcessDefinition> query = em.createQuery(
                        "select e from cambpm$BpmProcessDefinition e where e.key = :key",
                        BpmProcessDefinition.class
                );
                query.setParameter("key", processDefinition.getKey());
                BpmProcessDefinition bpmProcessDefinition = query.getFirstResult();

                if (bpmProcessDefinition == null) {
                    bpmProcessDefinition = entityConverter.toBpmProcessDefinition(processDefinition);
                    em.persist(bpmProcessDefinition);
                } else {
                    bpmProcessDefinition.setName(processDefinition.getName());
                    bpmProcessDefinition.setDescription(processDefinition.getDescription());
                    em.persist(bpmProcessDefinition);
                }
            }

            tx.commit();
        }
    }

}