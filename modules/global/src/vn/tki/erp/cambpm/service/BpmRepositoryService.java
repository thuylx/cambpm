package vn.tki.erp.cambpm.service;


import java.io.File;

public interface BpmRepositoryService {
    String NAME = "cambpm_BpmRepositoryService";

    /**
     * Permission required: Create process definition
     * @param deploymentName    Camunda deployment name
     * @param resourceName      Example: bpmn file name
     * @param resourceFile      File object contains xml definition. One file may contain several process definition. See Camunda document for more details.
     */
    void deployProcessDefinition(String deploymentName, String resourceName, File resourceFile);

    /**
     * Permission required: Update process definition
     * Load information of process definitions in camunda engine to cuba entities.
     * Be aware that Cuba platform maintain BpmProcessDefinition as a persistent entities for row level securities and BpmProcessDefinitionVersion is instance of Camunda ProcessDefinition
     */
    void syncProcessDefinition();


    /**
     * Get diagram xml string of the deployed process definition version
     * @param processDefinitionVersionId Id of the camunda process definition
     * @return xml string
     */
    String getProcessDefinitionDiagram(String processDefinitionVersionId);
}