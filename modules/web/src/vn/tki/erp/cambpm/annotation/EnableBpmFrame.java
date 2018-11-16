package vn.tki.erp.cambpm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add this annotation before any editor screen to turn on BpmFrame, which shows below components:
 * Buttons to start relevant business processes<br>
 * Buttons to complete relevant pending task which assigned to current user or belong to current user's role <br>
 * Button to refresh pending tasks and <br>
 * Button to see all of running process instances which have business key contain editing entity loading info.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableBpmFrame {
    /**
     * Set this parameter of "disableBpmFrame" to false to disable frame temporarily
     */
    String DISABLED_FLAG_PARAM_NAME = "disableBpmFrame";

    /**
     * @return container id for task completion buttons. No button will show if blank
     */
    String taskActionContainerId() default "";

    /**
     * @return container id for process definition start buttons. No button will show if blank
     */
    String processDefinitionActionContainerId() default "";

    /**
     * @return container id for showing running process instance button. No button will show if blank
     */
    String runningProcessActionContainerId() default "";

    /**
     * @return container id for pending task refreshing button. No button will show if blank
     */
    String taskRefreshActionContainerId() default "";
}
