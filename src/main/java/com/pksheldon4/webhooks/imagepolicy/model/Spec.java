package com.pksheldon4.webhooks.imagepolicy.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Spec {

    private List<Container> containers;
    private Map<String, String> annotations;
    private String namespace;
}
