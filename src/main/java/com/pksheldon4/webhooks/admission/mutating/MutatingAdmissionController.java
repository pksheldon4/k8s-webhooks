package com.pksheldon4.webhooks.admission.mutating;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pksheldon4.webhooks.admission.model.AdmissionReview;
import com.pksheldon4.webhooks.admission.model.AdmissionReviewResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@Slf4j
public class MutatingAdmissionController {

    private final ObjectMapper objectMapper;

    public MutatingAdmissionController(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }

    @PostMapping("/mutate")
    public AdmissionReview validatingWebhook(@RequestBody AdmissionReview reviewRequest) throws Exception {
        log.info(objectMapper.writeValueAsString(reviewRequest));
        String patch = getLabelPatch();
        AdmissionReviewResponse reviewResponse = AdmissionReviewResponse.allowed(reviewRequest.getRequest().getUid(), patch);
        return new AdmissionReview(reviewRequest.getApiVersion(), reviewRequest.getKind(), reviewResponse);
    }

    private String getLabelPatch() {
        String patch = "[{\"op\": \"add\", \"path\": \"/metadata/labels/mutated\", \"value\": \"true\"}]";
        return Base64.getEncoder().encodeToString(patch.getBytes());
    }
}

