package vn.tki.erp.cambpm.service;

import vn.tki.erp.cambpm.model.BpmForm;

import java.util.UUID;

public interface BpmFormService {
    String NAME = "cambpm_BpmFormService";

    /**
     * Form name pattern <br>
     *     ^(frame|editor|browser|window|FRAME|EDITOR|BROWSER|WINDOW)(:([a-zA-Z0-9_\\-.]+\\$[a-zA-Z0-9_\\-.]+))?(:(EMBEDDED|embedded|DIALOG|dialog|NEW_WINDOW|new_window|NEW_TAB|new_tab|THIS_TAB|this_tab))?
     */
    String FORM_NAME_REGEX = "^(frame|editor|browser|window|FRAME|EDITOR|BROWSER|WINDOW)" + //1
            "(:" +
            "([a-zA-Z0-9_\\-.]+\\$[a-zA-Z0-9_\\-.]+))?" + //3
            "(:" +
            "(EMBEDDED|embedded|DIALOG|dialog|NEW_WINDOW|new_window|NEW_TAB|new_tab|THIS_TAB|this_tab))?"; //5

    /**
     * Key of form field property to specify if this field is stored (updated) to the entity (which map to process instance business key) <br>
     *     Key: isEntityAttribute<br>
     *     Accepted property value: blank (means true), true, yes, false, no. Default is false
     */
    String IS_ENTITY_ATTRIBUTE_PROPERTY_KEY = "isEntityAttribute";
    /**
     * Key of form field property to specify if this field is stored as process variable. <br>
     *     Key:isProcessVariable<br>
     *     Accepted value: blank (means true), true, yes, false, no. Default is true
     */
    String IS_PROCESS_VARIABLE_PROPERTY_KEY = "isProcessVariable";
    /**
     * Key of form field property to indicate this field should be visible or not.<br>
     *     Key:visible<br>
     *     Accepted value: blank (means true), true, yes, false, no. Default is true.
     */
    String VISIBLE_PROPERTY_KEY = "visible";
    /**
     * Key of form field property to indicate row number of textArea for string field.<br>
     *     Key:line<br>
     *     Accepted value: any integer number. Default is 1
     */
    String STRING_LINE_NUMBER_PROPERTY_KEY = "line";

    /**
     * Permission required: READ BpmTask
     * @return BpmForm object created base on task form data, meaning that all of expression if any have been fetched.
     */
    BpmForm getTaskForm(UUID taskId);

    /**
     * Permission required: READ BpmProcessDefinition
     * @return BpmForm object created base on start form data, meaning that all of expression if any have been fetched.
     */
    BpmForm getStartForm(String processDefinitionId);
}