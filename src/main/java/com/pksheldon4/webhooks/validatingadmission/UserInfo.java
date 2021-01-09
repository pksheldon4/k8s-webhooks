package com.pksheldon4.webhooks.validatingadmission;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserInfo {

    private String username;
    private String uid;
    private List<String> groups;
    private Map<String, List<Object>> extra;
}