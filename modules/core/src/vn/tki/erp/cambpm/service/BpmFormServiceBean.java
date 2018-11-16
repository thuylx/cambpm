package vn.tki.erp.cambpm.service;

import com.google.common.base.Strings;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.security.entity.EntityOp;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.form.FormData;
import org.camunda.bpm.engine.form.FormField;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.tki.erp.cambpm.config.BpmConfig;
import vn.tki.erp.cambpm.core.CamPlatform;
import vn.tki.erp.cambpm.entity.BpmProcessDefinition;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.exception.BpmAuthorizationException;
import vn.tki.erp.cambpm.exception.BpmProcessEngineException;
import vn.tki.erp.cambpm.model.BpmForm;
import vn.tki.erp.cambpm.model.BpmFormField;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service(BpmFormService.NAME)
public class BpmFormServiceBean implements BpmFormService {
    @Inject
    private CamPlatform camPlatform;
    @Inject
    private Security security;
    @Inject
    private Configuration configuration;

    @Override
    public BpmForm getTaskForm(UUID taskId) {
        if (!security.isEntityOpPermitted(BpmTask.class, EntityOp.READ))
            throw new BpmAuthorizationException("Get task form", "Read BpmTask");

        try {
            FormData formData = camPlatform.getProcessEngine().getFormService().
                    getTaskFormData(taskId.toString());
            BpmForm taskForm = toBpmForm(formData);
            if (Strings.isNullOrEmpty(taskForm.getOpenType())) {
                BpmConfig bpmConfig = configuration.getConfig(BpmConfig.class);
                taskForm.setOpenType(bpmConfig.getDefaultTaskFormOpenType());
            }
            return taskForm;
        } catch (ProcessEngineException e) {
            throw new BpmProcessEngineException("Complete task failed.", e);
        }
    }

    @Override
    public BpmForm getStartForm(String processDefinitionId) {
        if (!security.isEntityOpPermitted(BpmProcessDefinition.class, EntityOp.READ))
            throw new BpmAuthorizationException("Get start form", "Read BpmProcessDefinition");

        FormData formData = camPlatform.getProcessEngine().getFormService()
                .getStartFormData(processDefinitionId);
        BpmForm startForm = toBpmForm(formData);
        if (Strings.isNullOrEmpty(startForm.getOpenType())) {
            BpmConfig bpmConfig = configuration.getConfig(BpmConfig.class);
            startForm.setOpenType(bpmConfig.getDefaultStartFormOpenType());
        }
        return startForm;
    }

    /////////////////////////////////////////////
    BpmForm toBpmForm(FormData formData) {
        BpmForm bpmForm = new BpmForm();
        if (!Strings.isNullOrEmpty(formData.getFormKey())) {
            Pattern pattern = Pattern.compile(FORM_NAME_REGEX);
            Matcher matcher = pattern.matcher(formData.getFormKey());

            if (matcher.matches()) {
                String screenType = (matcher.group(1) == null) ? null : matcher.group(1).toLowerCase();
                String formName = matcher.group(3);
                String openType = (matcher.group(5) == null) ? null : matcher.group(5).toLowerCase();

                if (screenType != null) {
                    if ("frame".equals(screenType)) {
                        bpmForm.setEmbeddable(true);
                    } else {
                        bpmForm.setScreenType(BpmForm.ScreenType.valueOf(screenType.toUpperCase()));
                    }
                }

                if (formName != null) {
                    bpmForm.setFormName(formName);
                } else if ("editor".equals(screenType) || "browser".equals(screenType)) {
                    bpmForm.setFormName(screenType); //will be standardize later to proper screen name
                }

                if (openType != null) {
                    if ("embedded".equals(matcher.group(5).toLowerCase())) {
                        bpmForm.setEmbeddable(true);
                    } else {
                        bpmForm.setOpenType(openType.toUpperCase());
                    }
                }
            }
        }
        bpmForm.setFormFields(formData.getFormFields().stream().map(this::toBpmFormField).collect(Collectors.toList()));
        return bpmForm;
    }

    BpmFormField toBpmFormField(FormField formField) {
        BpmFormField bpmFormField = new BpmFormField();
        bpmFormField.setId(formField.getId());
        bpmFormField.setLabel(formField.getLabel());
        bpmFormField.setTypeName(formField.getTypeName());
        bpmFormField.setDefaultValue(formField.getValue());

        if (formField.getTypeName().equals("enum")) {
            //Swap key and value of Map first
            //noinspection unchecked
            Map<String, String> optionMap = ((Map<String, String>) formField.getType().getInformation("values"))
                    .entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
            bpmFormField.setEnumValues(optionMap);
        }

        bpmFormField.setValidationConstraints(formField.getValidationConstraints());

        //camunda form field properties
        bpmFormField.setEntityAttribute(false); // false by default
        if (formField.getProperties().containsKey(IS_ENTITY_ATTRIBUTE_PROPERTY_KEY)) {
            String entityAttribute = formField.getProperties().get(IS_ENTITY_ATTRIBUTE_PROPERTY_KEY);
            if (Strings.isNullOrEmpty(entityAttribute) || "yes".equals(entityAttribute) || "true".equals(entityAttribute)) {
                bpmFormField.setEntityAttribute(true);
            }
        }

        bpmFormField.setProcessVariable(true); //true by default
        if (formField.getProperties().containsKey(IS_PROCESS_VARIABLE_PROPERTY_KEY)) {
            String processVariable = formField.getProperties().get(IS_ENTITY_ATTRIBUTE_PROPERTY_KEY);
            if (Strings.isNullOrEmpty(processVariable) || "yes".equals(processVariable) || "true".equals(processVariable)) {
                bpmFormField.setProcessVariable(true);
            }
        }

        bpmFormField.setVisible(true); //true by default
        if (formField.getProperties().containsKey(VISIBLE_PROPERTY_KEY)) {
            String visible = formField.getProperties().get(VISIBLE_PROPERTY_KEY);
            if (Strings.isNullOrEmpty(visible) || "yes".equals(visible) || "true".equals(visible)) {
                bpmFormField.setVisible(true);
            }
        }

        bpmFormField.setStringLineNumber(1); //default is 1
        if (formField.getProperties().containsKey(STRING_LINE_NUMBER_PROPERTY_KEY)) {
            String line = formField.getProperties().get(STRING_LINE_NUMBER_PROPERTY_KEY);
            try {
                bpmFormField.setStringLineNumber(Integer.parseInt(line));
            } catch (NumberFormatException e) {
                LoggerFactory.getLogger(BpmFormServiceBean.class).debug("Line number property of a String field is invalid. See BPMN file to fix.");
            }
        }

        return bpmFormField;
    }
}