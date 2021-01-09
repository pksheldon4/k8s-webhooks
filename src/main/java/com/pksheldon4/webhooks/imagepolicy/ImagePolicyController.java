package com.pksheldon4.webhooks.imagepolicy;


import com.pksheldon4.webhooks.imagepolicy.model.ImageReview;
import com.pksheldon4.webhooks.imagepolicy.model.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ImagePolicyController {

    public static final String API_VERSION = "imagepolicy.k8s.io/v1alpha1";
    public static final String KIND = "ImageReview";
    //TOOD: externalize this.
    private static final List<String> IMAGE_REGISTRY_WHITELIST = Arrays.asList("k8s.gcr.io", "docker.io");

    @PostMapping("/check-image")
    public ImageReview imagePolicyWebhook(@RequestBody ImageReview imageReview) {
        log.info(imageReview.toString());

        List<String> registriesInUse = imageReview.getSpec().getContainers().stream()
            .filter(c -> c.getImage().contains("/"))
            .map(c -> c.getImage().substring(0, c.getImage().indexOf("/")))
            .collect(Collectors.toList());

        registriesInUse.removeAll(IMAGE_REGISTRY_WHITELIST);
        Status returnStatus = new Status(true);
        if (registriesInUse.size() > 0) {
            returnStatus = new Status(false);
        }
        return new ImageReview(API_VERSION, KIND, returnStatus);
    }
}
