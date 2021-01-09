package com.pksheldon4.webhooks.validatingadmission;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ValidatingAdmissionController {

    @PostMapping("/validate")
    public AdmissionReview validatingWebhook(@RequestBody Object body) {
        log.info(body.toString());
        return null;
    }
}
