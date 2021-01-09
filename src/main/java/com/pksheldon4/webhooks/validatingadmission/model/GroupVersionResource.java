package com.pksheldon4.webhooks.validatingadmission.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupVersionResource {

    private String group;
    private String version;
    private String resource;
}
