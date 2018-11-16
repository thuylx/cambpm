package vn.tki.erp.cambpm.model;

import java.io.Serializable;
import java.util.List;

/**
 * Store Camunda Form data configured in bpmn file to generate task form
 */
public class BpmForm implements Serializable {
    private String formName = "";
    private String openType = "";
    private ScreenType screenType;
    private boolean isEmbeddable = false;
    private List<BpmFormField> formFields;

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }

    public boolean isEmbeddable() {
        return isEmbeddable;
    }

    public void setEmbeddable(boolean embeddable) {
        isEmbeddable = embeddable;
    }

    public List<BpmFormField> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<BpmFormField> formFields) {
        this.formFields = formFields;
    }

    /**
     * The screen type to open task form in Cuba Platform
     */
    public enum ScreenType {
        /**
         * indicate that task form should be opened as an editor (using openEditor())
         * if formName is empty, it will try to get default editor
         */
        EDITOR,
        /**
         * indicate that task form should be opened as a browser (using openWindow())
         * if formName is empty, it will try to get default browser
         */
        BROWSER,
        /**
         * indicate that task form should be opened as a window (using openWindow())
         */
        WINDOW
    }

}
