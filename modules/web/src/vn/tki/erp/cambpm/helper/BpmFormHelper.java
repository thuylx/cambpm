package vn.tki.erp.cambpm.helper;

import com.google.common.base.Strings;
import com.haulmont.chile.core.datatypes.Datatypes;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.global.EntityLoadInfo;
import com.haulmont.cuba.core.global.EntityLoadInfoBuilder;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.config.WindowConfig;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.web.gui.components.WebComponentsHelper;
import com.vaadin.ui.Layout;
import org.camunda.bpm.engine.form.FormFieldValidationConstraint;
import org.slf4j.LoggerFactory;
import vn.tki.erp.cambpm.entity.BpmProcessDefinition;
import vn.tki.erp.cambpm.entity.BpmProcessDefinitionEntity;
import vn.tki.erp.cambpm.entity.BpmProcessInstance;
import vn.tki.erp.cambpm.entity.BpmTask;
import vn.tki.erp.cambpm.model.BpmForm;
import vn.tki.erp.cambpm.model.BpmFormField;
import vn.tki.erp.cambpm.web.toolkit.ui.bpmnviewer.BpmnViewerServerComponent;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Component(BpmFormHelper.NAME)
public class BpmFormHelper {
    public static final String NAME = "cambpm_BpmFormHelper";

    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private EntityLoadInfoBuilder entityLoadInfoBuilder;
    @Inject
    private WindowConfig windowConfig;
    @Inject
    private Metadata metadata;

    /**
     * Create GUI component for BpmForm fields inside given container.
     * After user entering data, the data may be updated back to BomForm field values using function loadFormData of this BpmFormHelper
     * @param container Cuba GUI container
     * @param bpmForm   BpmForm object which fields will be initialized in given container
     */
    public void initBpmFormFields(Component.Container container, BpmForm bpmForm){
        for (BpmFormField formField:bpmForm.getFormFields()) {
            initBpmFormField(container, formField);
        }
    }

    /**
     * Generate form component for given BpmFormField in specific container for process engine variable
     * @param container     Cuba GUI container
     * @param field         BpmFormField
     */
    private void initBpmFormField(Component.Container container, BpmFormField field) {
        Component component = null;
        switch (field.getTypeName()) {
            case "string":
                try {
                    if (field.getStringLineNumber() > 1) {
                        component = addTextAreaField(container,field);
                    } else {
                        component = addTextField(container,field);
                    }
                } catch (NumberFormatException e) {
                    component = addTextField(container,field); //default
                }
                break;
            case "long":
                TextField textField = addTextField(container,field);
                textField.setDatatype(Datatypes.getNN(Long.class));
                component = textField;
                break;
            case "enum":
                component = addOptionsGroup(container,field);
                break;
            case "date":
                component = addDateField(container,field);
                break;
            case "boolean":
                component = addCheckBoxField(container,field);
                break;
            default:
                LoggerFactory.getLogger(BpmFormHelper.class).debug("Data type " + field.getTypeName() + " is not supported");
        }

        if (component != null && !field.isVisible()) {
            component.setVisible(false);
        }
    }

    private void initInputComponent(Field component, BpmFormField field) {
        component.setId(field.getId());
        component.setCaption(field.getLabel());

        for (FormFieldValidationConstraint validationConstraint:field.getValidationConstraints()){
            switch (validationConstraint.getName()) {
                case "required":
                    component.setRequired(true);
                    component.setRequiredMessage(field.getLabel() + " is required");
                    break;
                case "readonly":
                    component.setEnabled(false);
                    break;
            }
        }
    }

    private void initTextInputComponent(TextInputField.MaxLengthLimited component, BpmFormField field) {
        initInputComponent(component, field);
        component.setSizeFull();
        component.setValue(field.getDefaultValue().getValue());
        for (FormFieldValidationConstraint validationConstraint:field.getValidationConstraints()){
            switch (validationConstraint.getName()) {
                case "maxlength":
                    component.setMaxLength(Integer.valueOf((String) validationConstraint.getConfiguration()));
                    break;
                case "minlength":
                    //TODO: is not support
                    LoggerFactory.getLogger(BpmFormHelper.class).debug("Form validation type of "+validationConstraint.getName()+" is not supported");
                    break;
            }
        }
    }

    private TextArea addTextAreaField(Component.Container container, BpmFormField field) {
        TextArea textArea = componentsFactory.createComponent(TextArea.class);
        initTextInputComponent(textArea,field);
        textArea.setRows(field.getStringLineNumber());
        container.add(textArea);
        return textArea;
    }

    private TextField addTextField(Component.Container container, BpmFormField field){
        TextField textField = componentsFactory.createComponent(TextField.class);
        initTextInputComponent(textField, field);
        container.add(textField);
        return textField;
    }

    private OptionsGroup addOptionsGroup(Component.Container container, BpmFormField field){
        OptionsGroup optionsGroup = componentsFactory.createComponent(OptionsGroup.class);
        optionsGroup.setOptionsMap(field.getEnumValues());
        initInputComponent(optionsGroup, field);
        optionsGroup.setValue(field.getDefaultValue().getValue());

        container.add(optionsGroup);
        return optionsGroup;
    }

    private DateField addDateField(Component.Container container, BpmFormField field){
        DateField dateField = componentsFactory.createComponent(DateField.class);
        initInputComponent(dateField,field);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dateField.setValue(dateFormat.parse(field.getDefaultValue().getValue().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        container.add(dateField);
        return dateField;
    }

    private CheckBox addCheckBoxField(Component.Container container, BpmFormField field){
        CheckBox checkBox = componentsFactory.createComponent(CheckBox.class);
        initInputComponent(checkBox, field);
        checkBox.setValue(field.getDefaultValue().getValue());
        container.add(checkBox);
        return checkBox;
    }

    private void standardizeFormName(BpmForm bpmForm, MetaClass metaClass) {
        if (metaClass == null) return;

        String formName = bpmForm.getFormName().toUpperCase();
        if (formName.equals(BpmForm.ScreenType.EDITOR.toString())) {
            bpmForm.setFormName(windowConfig.getEditorScreenId(metaClass));
            bpmForm.setScreenType(BpmForm.ScreenType.EDITOR);
        } else if (formName.equals(BpmForm.ScreenType.BROWSER.toString())) {
            bpmForm.setFormName(windowConfig.getBrowseScreenId(metaClass));
            bpmForm.setScreenType(BpmForm.ScreenType.BROWSER);
        }
    }

    public void standardizeFormName(BpmForm bpmForm, BpmProcessDefinition processDefinition) throws IllegalStateException {
        String formName = bpmForm.getFormName().toUpperCase();
        if (formName.equals(BpmForm.ScreenType.EDITOR.toString()) || formName.equals(BpmForm.ScreenType.BROWSER.toString())) {
            List<BpmProcessDefinitionEntity> processDefinitionEntities = processDefinition.getEntities();
            if (processDefinitionEntities.size() != 1) throw new IllegalStateException();

            String entityName = processDefinitionEntities.get(0).getEntityName();
            if (Strings.isNullOrEmpty(entityName)) return;

            MetaClass metaClass = metadata.getSession().getClassNN(entityName);
            standardizeFormName(bpmForm, metaClass);
        }
    }

    public void standardizeFormName(BpmForm bpmForm, BpmTask bpmTask) {
        String formName = bpmForm.getFormName().toUpperCase();
        if (formName.equals(BpmForm.ScreenType.EDITOR.toString()) || formName.equals(BpmForm.ScreenType.BROWSER.toString())) {
            BpmProcessInstance processInstance = bpmTask.getProcessInstance();
            if (processInstance == null || Strings.isNullOrEmpty(processInstance.getBusinessKey())) {
                return;
            }

            EntityLoadInfo entityLoadInfo = entityLoadInfoBuilder.parse(processInstance.getBusinessKey());
            if (entityLoadInfo == null)  {
                return;
            }

            MetaClass metaClass = Objects.requireNonNull(entityLoadInfoBuilder.parse(processInstance.getBusinessKey())).getMetaClass();
            standardizeFormName(bpmForm, metaClass);
        }
    }

    /**
     * Load data user entered in GUI component inside given container to BpmForm fields
     * @param bpmForm   BpmForm object which will be updated field value accordingly
     * @param container Container in which form components reside
     */
    public void loadFormData(BpmForm bpmForm, Component.Container container) {
        for (BpmFormField formField:bpmForm.getFormFields()) {
            Field field = (Field) container.getComponent(formField.getId());
            formField.setValue(field != null ? field.getValue() : null);
        }
    }

    public void initBpmnViewer(Component.Container container, String xmlDigaram, @Nullable String[] highlightedElementKeys) {
        //Load bpmn viewer
        BpmnViewerServerComponent viewerServerComponent = new BpmnViewerServerComponent();
        viewerServerComponent.setBpmnXml(xmlDigaram);
        viewerServerComponent.setWidth("100%");
        viewerServerComponent.setHeight("100%");
        if (highlightedElementKeys != null) {
            viewerServerComponent.setActiveElements(highlightedElementKeys);
        }

        Layout layout = (Layout) WebComponentsHelper.unwrap(container);
        layout.addComponent(viewerServerComponent);
    }
}
