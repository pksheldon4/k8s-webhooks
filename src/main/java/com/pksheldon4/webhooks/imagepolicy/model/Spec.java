package com.pksheldon4.webhooks.imagepolicy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Spec {

    private List<Container> containers;
    private Map<String, String> annotations;
    private String namespace;
}
