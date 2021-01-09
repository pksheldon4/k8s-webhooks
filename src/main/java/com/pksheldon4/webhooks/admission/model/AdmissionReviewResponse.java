package com.pksheldon4.webhooks.admission.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdmissionReviewResponse {

    private String uid;
    private boolean allowed;
    private AdmissionStatus status;
    private String patchType;
    private String patch;

    private AdmissionReviewResponse(String uid, String jsonPatch) {
        this.uid = uid;
        this.allowed = true;
        if (jsonPatch != null) {
            this.patchType = "JSONPatch";
            this.patch = jsonPatch;
        }
    }


    private AdmissionReviewResponse(String uid, AdmissionStatus status) {
        this.uid = uid;
        this.allowed = false;
        this.status = status;
    }

    public static AdmissionReviewResponse allowed(String uid) {
        return AdmissionReviewResponse.allowed(uid, null);
    }

    public static AdmissionReviewResponse allowed(String uid, String jsonPatch) {
        return new AdmissionReviewResponse(uid, jsonPatch);
    }


    public static AdmissionReviewResponse notAllowed(String uid, String message) {
        AdmissionStatus status = new AdmissionStatus(500, message);
        return new AdmissionReviewResponse(uid, status);
    }
}
