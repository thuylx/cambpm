package vn.tki.erp.cambpm.web.toolkit.ui.bpmnviewer;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript({"bpmnviewer-connector.js","bpmn-viewer.js"})
@StyleSheet({"bpmn-viewer.css"})
public class BpmnViewerServerComponent extends AbstractJavaScriptComponent {
    public BpmnViewerServerComponent() {
    }

    @Override
    protected BpmnViewerState getState() {
        return (BpmnViewerState) super.getState();
    }

    public String getBpmnXml() {
        return getState().bpmnXml;
    }

    public void setBpmnXml(String bpmnXml) {
        getState().bpmnXml = bpmnXml;
    }

    public String[] getActiveElements() {
        return getState().activeElements;
    }

    public void setActiveElements(String[] activeElements) {
        getState().activeElements = activeElements;
    }
}