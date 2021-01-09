package com.pksheldon4.webhooks.imagepolicy.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageReview {

    private final String apiVersion;
    private final String kind;
    private Spec spec;
    private Status status;

    public ImageReview(String apiVersion, String kind, Status status) {
        this.apiVersion = apiVersion;
        this.kind = kind;
        this.status = status;
    }
}
