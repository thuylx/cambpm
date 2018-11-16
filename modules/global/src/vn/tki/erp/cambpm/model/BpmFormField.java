package vn.tki.erp.cambpm.model;

import org.camunda.bpm.engine.form.FormFieldValidationConstraint;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * BpmFormField to cary camunda bpmn form data for a specified field
 */
public class BpmFormField implements Serializable {

    private String id;

    private String label;

    private String typeName;

    private TypedValue defaultValue;

    private Object value;

    private Map<String, String> enumValues;

    private int stringLineNumber;

    private List<FormFieldValidationConstraint> validationConstraints;

    private boolean isEntityAttribute;

    private boolean isProcessVariable;

    private boolean visible;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setDefaultValue(TypedValue defaultValue) {
        this.defaultValue = defaultValue;
    }

    public TypedValue getDefaultValue() {
        return defaultValue;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setEnumValues(Map<String, String> enumValues) {
        this.enumValues = enumValues;
    }

    public Map<String, String> getEnumValues() {
        return enumValues;
    }

    public List<FormFieldValidationConstraint> getValidationConstraints() {
        return validationConstraints;
    }

    public void setValidationConstraints(List<FormFieldValidationConstraint> validationConstraints) {
        this.validationConstraints = validationConstraints;
    }

    public boolean isEntityAttribute() {
        return isEntityAttribute;
    }

    public boolean isProcessVariable() {
        return isProcessVariable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setEntityAttribute(boolean entityAttribute) {
        isEntityAttribute = entityAttribute;
    }

    public void setProcessVariable(boolean processVariable) {
        isProcessVariable = processVariable;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getStringLineNumber() {
        return stringLineNumber;
    }

    public void setStringLineNumber(int stringLineNumber) {
        this.stringLineNumber = stringLineNumber;
    }
}