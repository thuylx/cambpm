cambpm
==========
Camunda - CubaPlatform intergration  
This application component bootstrap Camunda process engine for BPM functions.
It provides basic BUI for BPM as well.

#Enable bpm frame on entity editor
Add annotation @EnableBpmFrame to the editor controller  
The annotation executor will add below component to the editor screen:

1. Set of relevant process definition starting buttons
2. Show running process instances button
3. Buttons to complete pending tasks related to the opening entity
3. A button to reload task buttons
  
Parameters: 

* Set this parameter of "disableBpmFrame" to false to disable frame temporarily

        String DISABLED_FLAG_PARAM_NAME = "disableBpmFrame";

* Container id for task completion buttons. No button will show if blank

        String taskActionContainerId() default "";

* Container id for process definition start buttons. No button will show if blank

        String processDefinitionActionContainerId() default "";

* Container id for showing running process instance button. No button will show if blank

        String runningProcessActionContainerId() default "";

* Container id for pending task refreshing button. No button will show if blank

        String taskRefreshActionContainerId() default "";
      
#BPMN formkey config to work with Cuba Platform:
Formkey pattern:  

       ^(frame|editor|browser|window|FRAME|EDITOR|BROWSER|WINDOW)(:([a-zA-Z0-9_\\-.]+\\$[a-zA-Z0-9_\\-.]+))?(:(EMBEDDED|embedded|DIALOG|dialog|NEW_WINDOW|new_window|NEW_TAB|new_tab|THIS_TAB|this_tab))?

#Embedded form frame:
Frame to embedded in user task form have to extends BpmTaskAbstractFrame.  
BpmTaskAbstractFrame provide below functions: 
 
* `getBpmTask()`: Return opening BpmTask object
* `getProcessVariables()`: return map of process variables (Map<String, Object>) to complete the task.  
To declare a frame field (component) should be stored in process variable when completing task
 (to be available in bpmn expression), annotation `@ProcessVariable` should be use.
* `onCompleteTask()`: override this function to implement code which will be call right before completing the task

#Generated usertask form
Form field properties to auto generate user task form:

* Key of form field property to specify if this field is stored (updated) to the entity (which map to process instance business key)

        Key: isEntityAttribute
        Accepted property value: blank (means true), true, yes, false, no. Default is false

* Key of form field property to specify if this field is stored as process variable. <br>

        Key:isProcessVariable
        Accepted value: blank (means true), true, yes, false, no. Default is true

* Key of form field property to indicate this field should be visible or not.<br>

        Key:visible<br>
        Accepted value: blank (means true), true, yes, false, no. Default is true.

* Key of form field property to indicate row number of textArea for string field.<br>
    
        Key:line<br>
        Accepted value: any integer number. Default is 1
      
#Beans to use in BPMN expression:
1. ProcessApplication, bean name: app

        java.lang.String	getCurrentOrSubstitutedUserName() 
        java.lang.String	getCurrentUserName() 
        java.util.Collection<java.lang.String>	getCurrentUserRoles() 
        com.haulmont.cuba.core.entity.Entity<?>	getEntity(org.camunda.bpm.engine.delegate.DelegateExecution execution) 
        com.haulmont.cuba.core.entity.Entity<?>	getEntity(org.camunda.bpm.engine.delegate.DelegateExecution execution, java.lang.String viewName) 
        java.lang.String	getSubstitutedUserName() 
        com.haulmont.cuba.security.entity.User	getUser(java.lang.String loginName) 
        com.haulmont.cuba.security.global.UserSession	getUserSession() 

    Example: `${app.getCurrentUserName()}`

2. All of other beans which implement `BpmExposedBean` interface



