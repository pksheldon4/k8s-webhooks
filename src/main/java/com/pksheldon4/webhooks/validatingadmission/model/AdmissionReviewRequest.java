package com.pksheldon4.webhooks.validatingadmission.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdmissionReviewRequest {

    private String uid;
    private GroupVersionResource kind;
    private GroupVersionResource resource;
    private String subResource;
    private GroupVersionResource requestKind;
    private GroupVersionResource requestResource;
    private String requestSubResource;
    private String name;
    private String namespace;
    private String operation;
    private UserInfo userInfo;
    private Map<String, Object> object;
    private Map<String, Object> options;
    private boolean dryRun;
}
