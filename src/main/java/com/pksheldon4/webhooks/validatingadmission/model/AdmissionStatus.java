package com.pksheldon4.webhooks.validatingadmission.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdmissionStatus {

    private final int code;
    private final String message;
}
