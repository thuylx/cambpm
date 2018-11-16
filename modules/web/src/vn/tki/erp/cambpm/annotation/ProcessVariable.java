package vn.tki.erp.cambpm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add this annotation to the field of task form frame which extends BpmTaskAbstractFrame to mark field value is a process variable
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ProcessVariable {
    /**
     * @return name of process variable. Set to component Id if blank
     */
    String name() default "";

    /**
     * Load process variable to the component value if true and do not load otherwise.
     */
    boolean loadValue() default true;
}
