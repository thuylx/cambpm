package vn.tki.erp.cambpm.core;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.stereotype.Component;

/**
 * Bootstrap camunda engine
 */
@Component(CamPlatform.NAME)
public interface CamPlatform {
    String NAME="cambpm_CamPlatform";

    //---------------------------------------------------------------------------------------------------
    // ENTRY TO CAMUNDA ORIGIN SERVICES
    //---------------------------------------------------------------------------------------------------

    /**
     * Provide entry to access directly to Camunda engine. See Camunda document for more details.
     * Current user login name will be set authenticated before returning to tracking process starter <br>
     *     If current user session is not available, authentication will be cleared before returning
     * @return camunda processEngine object
     */
    ProcessEngine getProcessEngine();
}
