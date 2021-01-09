package com.pksheldon4.webhooks.validatingadmission;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pksheldon4.webhooks.validatingadmission.model.AdmissionReview;
import com.pksheldon4.webhooks.validatingadmission.model.AdmissionReviewResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ValidatingAdmissionController {

    private final ObjectMapper objectMapper;

    public ValidatingAdmissionController(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }

    @PostMapping("/validate")
    public AdmissionReview validatingWebhook(@RequestBody AdmissionReview reviewRequest) throws Exception {
        log.info(objectMapper.writeValueAsString(reviewRequest));
        AdmissionReviewResponse reviewResponse = AdmissionReviewResponse.allowed(reviewRequest.getRequest().getUid());
        AdmissionReview response = new AdmissionReview(reviewRequest.getApiVersion(), reviewRequest.getKind(), reviewResponse);

        return response;
    }
}
