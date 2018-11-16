package vn.tki.erp.cambpm.service;

import org.camunda.bpm.engine.impl.form.FormFieldImpl;
import org.camunda.bpm.engine.impl.form.TaskFormDataImpl;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.junit.Before;
import org.junit.Test;
import vn.tki.erp.cambpm.model.BpmForm;
import vn.tki.erp.cambpm.model.BpmFormField;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BpmFormServiceBeanTest {

    private BpmFormServiceBean formService;

    @Before
    public void setUp() {
        formService = new BpmFormServiceBean();
    }

    @Test
    public void toBpmForm() {

        BpmForm bpmForm;
        TaskFormDataImpl formData = new TaskFormDataImpl();


        bpmForm = formService.toBpmForm(formData);
        assertEquals("", bpmForm.getFormName());
        assertFalse(bpmForm.isEmbeddable());

        formData.setFormKey("frame:cambpm$Test.edit:DIALOG");
        bpmForm = formService.toBpmForm(formData);

        assertEquals("cambpm$Test.edit", bpmForm.getFormName());
        assertTrue(bpmForm.isEmbeddable());

        formData.setFormKey("editor:cambpm$Test.edit:EMBEDDED");
        bpmForm = formService.toBpmForm(formData);

        assertEquals("cambpm$Test.edit", bpmForm.getFormName());
        assertEquals("EDITOR", bpmForm.getScreenType().toString());
        assertTrue(bpmForm.isEmbeddable());

        formData.setFormKey("editor");
        bpmForm = formService.toBpmForm(formData);
        assertEquals("EDITOR", bpmForm.getScreenType().toString());
        assertEquals(false, bpmForm.isEmbeddable());
    }

    @Test
    public void toBpmFormField() {
        FormFieldImpl formField = new FormFieldImpl();
        formField.setLabel("Test label");
        formField.setId("testId");
        formField.setDefaultValue("key1");

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        formField.setType(new EnumFormType(map));

        BpmFormField testField = formService.toBpmFormField(formField);

        Map<String, String> swapped = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        assertEquals("testId", testField.getId());
        assertEquals("Test label", testField.getLabel());
//        assertEquals("key1", testField.getDefaultValue().getValue());
        assertEquals(swapped, testField.getEnumValues());

    }
}