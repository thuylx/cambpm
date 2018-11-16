package vn.tki.erp.cambpm.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;

@Source(type = SourceType.DATABASE)
public interface BpmConfig extends Config {
    @Property("bpm.defaultStartFormOpenType")
    @Default("THIS_TAB")
    String getDefaultStartFormOpenType();

    @Property("bpm.defaultTaskFormOpenType")
    @Default("THIS_TAB")
    String getDefaultTaskFormOpenType();

    @Property("bpm.maxResultsQuery")
    @Default("50")
    int getMaxResults();

    @Property("bpm.taskListFilterValueDelimiter")
    @Default("|")
    String getTaskListFilterValueDelimiter();
}
