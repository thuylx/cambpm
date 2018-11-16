package vn.tki.erp.cambpm.web.bpmprocessdefinition;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import vn.tki.erp.cambpm.entity.BpmProcessDefinitionGroup;
import vn.tki.erp.cambpm.service.BpmRepositoryService;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.util.Map;
import java.util.UUID;

public class BpmProcessDefinitionDeploy extends AbstractWindow {
    @Named("resourceFiles")
    private FileMultiUploadField multiUploadField;
    @Inject
    private FileUploadingAPI fileUploadingAPI;
    @Named("selectedFiles")
    private Label lblSelectedFiles;
    @Named("deployBtn")
    private Button deployBtn;
    @Inject
    private BpmRepositoryService repositoryService;
    @Inject
    private PickerField groupPicker;

    @Override
    public void init(Map<String, Object> params) {
        deployBtn.setEnabled(false);

        multiUploadField.addQueueUploadCompleteListener(() -> {
            showNotification(getMessage("uploadedFilesCaption") + multiUploadField.getUploadsMap().values(), NotificationType.HUMANIZED);
            deployBtn.setEnabled(true);
        });

        multiUploadField.addFileUploadFinishListener(event ->
                lblSelectedFiles.setValue(multiUploadField.getUploadsMap().values()));

        multiUploadField.addFileUploadStartListener(event ->
                deployBtn.setEnabled(false));

        multiUploadField.addFileUploadErrorListener(event ->
                showNotification(getMessage("uploadErrorNotification"), NotificationType.HUMANIZED));

    }

    public void onDeployBtnClick() {
        if (validateAll()) {
            for (Map.Entry<UUID, String> entry : multiUploadField.getUploadsMap().entrySet()) {
                File resourceFile = fileUploadingAPI.getFile(entry.getKey());
                String deploymentName = ((BpmProcessDefinitionGroup) groupPicker.getValue()).getName();
                //Deploy process
                repositoryService.deployProcessDefinition(deploymentName, entry.getValue(), resourceFile);
                StringBuilder notificationString = new StringBuilder();
                StringBuilder warningString = new StringBuilder();

                showNotification(getMessage("deploySuccessfullyNotification"));
                multiUploadField.clearUploads();
                close(Window.COMMIT_ACTION_ID);
            }
        }
    }

    public void onCancelBtnClick() {
        close(Window.CLOSE_ACTION_ID);
    }

    public void onClearBtnClick() {
        multiUploadField.clearUploads();
        lblSelectedFiles.setValue("");
    }
}