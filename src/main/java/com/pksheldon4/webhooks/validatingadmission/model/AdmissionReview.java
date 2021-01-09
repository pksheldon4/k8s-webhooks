package com.pksheldon4.webhooks.validatingadmission.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdmissionReview {

    private String apiVersion;
    private String kind;
    private AdmissionReviewRequest request;
    private AdmissionReviewResponse response;

    public AdmissionReview(String apiVersion, String kind, AdmissionReviewRequest reviewRequest) {
        this.apiVersion = apiVersion;
        this.kind = kind;
        this.request = reviewRequest;
        this.response = null;
    }

    public AdmissionReview(String apiVersion, String kind, AdmissionReviewResponse reviewResponse) {
        this.apiVersion = apiVersion;
        this.kind = kind;
        this.request = null;
        this.response = reviewResponse;
    }
}
