package com.pksheldon4.webhooks.validatingadmission.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdmissionReviewResponse {

    private final String uid;
    private final boolean allowed;
    private final AdmissionStatus status;

    private AdmissionReviewResponse(String uid, boolean allowed) {
        this.uid = uid;
        this.allowed = allowed;
        this.status = null;
    }


    private AdmissionReviewResponse(String uid, AdmissionStatus status) {
        this.uid = uid;
        this.allowed = false;
        this.status = status;
    }

    public static AdmissionReviewResponse allowed(String uid) {
        return new AdmissionReviewResponse(uid, true);
    }

    public static AdmissionReviewResponse notAllowed(String uid, String message) {
        AdmissionStatus status = new AdmissionStatus(500, message);
        return new AdmissionReviewResponse(uid, status);
    }
}
