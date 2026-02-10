package com.chitkara.bfhl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    private static final String OFFICIAL_EMAIL = "aaryan1010.be23@chitkara.edu.in";

    @GetMapping("/health")
    public Map<String, Object> healthCheck() {

        Map<String, Object> response = new HashMap<>();
        response.put("is_success", true);
        response.put("official_email", OFFICIAL_EMAIL);

        return response;
    }
}
