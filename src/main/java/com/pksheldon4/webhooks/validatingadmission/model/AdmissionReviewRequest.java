package com.pksheldon4.webhooks.validatingadmission.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdmissionReviewRequest {

    private final String uid;
    private final GroupVersionResource kind;
    private final GroupVersionResource resource;
    private final String subResource;
    private final GroupVersionResource requestKind;
    private final GroupVersionResource requestResource;
    private final String requestSubResource;
    private final String name;
    private final String namespace;
    private final String operation;
    private final UserInfo userInfo;
    private final Map<String, Object> object;
    private final Map<String, Object> options;
    private boolean dryRun;
}
