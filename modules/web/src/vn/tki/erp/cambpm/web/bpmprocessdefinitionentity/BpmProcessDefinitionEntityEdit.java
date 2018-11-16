package vn.tki.erp.cambpm.web.bpmprocessdefinitionentity;

import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.LookupField;
import vn.tki.erp.cambpm.entity.BpmProcessDefinitionEntity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BpmProcessDefinitionEntityEdit extends AbstractEditor<BpmProcessDefinitionEntity> {
    @Inject
    private Metadata metadata;
    @Inject
    private LookupField entityLookupField;

    @Override
    public void init(Map<String, Object> params) {
        Collection<MetaClass> classes = metadata.getTools().getAllPersistentMetaClasses();
        List<String> options = new ArrayList<>();

        for (MetaClass metaClass:classes) {
            options.add(metaClass.getName());
        }

        options.add("*");

        entityLookupField.setOptionsList(options);
    }
}