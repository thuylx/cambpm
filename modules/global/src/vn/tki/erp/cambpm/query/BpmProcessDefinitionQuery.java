package vn.tki.erp.cambpm.query;

import vn.tki.erp.cambpm.entity.BpmProcessDefinitionVersion;

public class BpmProcessDefinitionQuery extends BpmQueryImpl<BpmProcessDefinitionQuery, BpmProcessDefinitionVersion> implements BpmQuery {
    public BpmProcessDefinitionQuery processDefinitionId(String processDefinitionId) {
        addString("processDefinitionId", processDefinitionId);
        return this;
    }


    public BpmProcessDefinitionQuery processDefinitionIdIn(String... ids) {
        addStringArray("processDefinitionIdIn", ids);
        return this;
    }


    public BpmProcessDefinitionQuery processDefinitionCategory(String processDefinitionCategory) {
        addString("processDefinitionCategory", processDefinitionCategory);
        return this;
    }


    public BpmProcessDefinitionQuery processDefinitionCategoryLike(String processDefinitionCategoryLike) {
        addString("processDefinitionCategoryLike", processDefinitionCategoryLike);
        return this;
    }


    public BpmProcessDefinitionQuery processDefinitionName(String processDefinitionName) {
        addString("processDefinitionName", processDefinitionName);
        return this;
    }


    public BpmProcessDefinitionQuery processDefinitionNameLike(String processDefinitionNameLike) {
        addString("processDefinitionNameLike", processDefinitionNameLike);
        return this;
    }


    public BpmProcessDefinitionQuery deploymentId(String deploymentId) {
        addString("deploymentId", deploymentId);
        return this;
    }


    public BpmProcessDefinitionQuery processDefinitionKey(String processDefinitionKey) {
        addString("processDefinitionKey",processDefinitionKey);
        return this;
    }


    public BpmProcessDefinitionQuery processDefinitionKeyLike(String processDefinitionKeyLike) {
        addString("processDefinitionKeyLike", processDefinitionKeyLike);
        return this;
    }


    public BpmProcessDefinitionQuery processDefinitionVersion(Integer processDefinitionVersion) {
        addInteger("processDefinitionVersion",processDefinitionVersion);
        return this;
    }


    public BpmProcessDefinitionQuery latestVersion() {
        add("latestVersion");
        return this;
    }


    public BpmProcessDefinitionQuery processDefinitionResourceName(String resourceName) {
        addString("processDefinitionResourceName", resourceName);
        return this;
    }


    public BpmProcessDefinitionQuery processDefinitionResourceNameLike(String resourceNameLike) {
        addString("processDefinitionResourceNameLike", resourceNameLike);
        return this;
    }


    public BpmProcessDefinitionQuery startableByUser(String userId) {
        addString("startableByUser", userId);
        return this;
    }


    public BpmProcessDefinitionQuery suspended() {
        add("suspended");
        return this;
    }


    public BpmProcessDefinitionQuery active() {
        add("active");
        return this;
    }


    public BpmProcessDefinitionQuery incidentType(String incidentType) {
        addString("incidentType", incidentType);
        return this;
    }


    public BpmProcessDefinitionQuery incidentId(String incidentId) {
        addString("incidentId", incidentId);
        return this;
    }


    public BpmProcessDefinitionQuery incidentMessage(String incidentMessage) {
        addString("incidentMessage", incidentMessage);
        return this;
    }


    public BpmProcessDefinitionQuery incidentMessageLike(String incidentMessageLike) {
        addString("incidentMessageLike", incidentMessageLike);
        return this;
    }


    public BpmProcessDefinitionQuery versionTag(String versionTag) {
        addString("versionTag",versionTag);
        return this;
    }


    public BpmProcessDefinitionQuery versionTagLike(String versionTagLike) {
        addString("versionTagLike", versionTagLike);
        return this;
    }


    public BpmProcessDefinitionQuery messageEventSubscription(String messageName) {
        addString("messageEventSubscription",messageName);
        return this;
    }


    public BpmProcessDefinitionQuery messageEventSubscriptionName(String messageName) {
        addString("messageEventSubscriptionName", messageName);
        return this;
    }


    public BpmProcessDefinitionQuery tenantIdIn(String... tenantIds) {
        addStringArray("tenantIdIn", tenantIds);
        return this;
    }


    public BpmProcessDefinitionQuery withoutTenantId() {
        add("withoutTenantId");
        return this;
    }


    public BpmProcessDefinitionQuery includeProcessDefinitionsWithoutTenantId() {
        add("includeProcessDefinitionsWithoutTenantId");
        return this;
    }


    public BpmProcessDefinitionQuery orderByProcessDefinitionCategory() {
        add("orderByProcessDefinitionCategory");
        return this;
    }


    public BpmProcessDefinitionQuery orderByProcessDefinitionKey() {
        add("orderByProcessDefinitionKey");
        return this;
    }


    public BpmProcessDefinitionQuery orderByProcessDefinitionId() {
        add("orderByProcessDefinitionId");
        return this;
    }


    public BpmProcessDefinitionQuery orderByProcessDefinitionVersion() {
        add("orderByProcessDefinitionVersion");
        return this;
    }


    public BpmProcessDefinitionQuery orderByProcessDefinitionName() {
        add("orderByProcessDefinitionName");
        return this;
    }


    public BpmProcessDefinitionQuery orderByDeploymentId() {
        add("orderByDeploymentId");
        return this;
    }


    public BpmProcessDefinitionQuery orderByTenantId() {
        add("orderByTenantId");
        return this;
    }


    public BpmProcessDefinitionQuery orderByVersionTag() {
        add("orderByVersionTag");
        return this;
    }
}
