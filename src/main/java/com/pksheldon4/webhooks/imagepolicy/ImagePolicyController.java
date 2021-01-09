package com.pksheldon4.webhooks.imagepolicy;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pksheldon4.webhooks.imagepolicy.model.ImageReview;
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
    private final ObjectMapper objectMapper;

    public ImagePolicyController(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }


    @PostMapping("/check-image")
    public ImageReview imagePolicyWebhook(@RequestBody ImageReview imageReview) throws Exception {
        log.info(objectMapper.writeValueAsString(imageReview));

        List<String> registriesInUse = imageReview.getSpec().getContainers().stream()
            .filter(c -> c.getImage().contains("/"))
            .map(c -> c.getImage().substring(0, c.getImage().indexOf("/")))
            .collect(Collectors.toList());

        registriesInUse.removeAll(IMAGE_REGISTRY_WHITELIST);
        if (registriesInUse.size() == 0) {
            imageReview.getStatus().setAllowed(true);
        }
        return imageReview;
    }
}
