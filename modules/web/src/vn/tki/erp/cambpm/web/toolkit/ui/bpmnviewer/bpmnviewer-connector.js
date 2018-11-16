vn_tki_erp_cambpm_web_toolkit_ui_bpmnviewer_BpmnViewerServerComponent = function() {
    var connector = this;
    var element = connector.getElement();
    var bpmnViewer = new BpmnJS({ container: element});

//    connector.onStateChange = function(){
        var state = connector.getState();
        var xml = state.bpmnXml;
        bpmnViewer.importXML(xml, function(err) {
              if (err) {
                console.log('error rendering', err);
              } else {
                console.log('rendered');

                // access viewer components
                var canvas = bpmnViewer.get('canvas');
                var overlays = bpmnViewer.get('overlays');


                // zoom to fit full viewport
                canvas.zoom('fit-viewport');

                var activeElements = state.activeElements;
                for(var i=0, len=activeElements.length; i < len; i++){
                    //set active
                    canvas.addMarker(activeElements[i],'highlight');
                }

                // attach an overlay to a node
//                overlays.add('SCAN_OK', 'note', {  //SCAN_OK is Id of the bpmn element
//                position: {
//                  bottom: 0,
//                  right: 0
//                },
//                html: '<div class="diagram-note">Mixed up the labels?</div>'
//                });
//
//                // add marker
//                canvas.addMarker('SCAN_OK', 'needs-discussion');
              }
            });
//    }
}