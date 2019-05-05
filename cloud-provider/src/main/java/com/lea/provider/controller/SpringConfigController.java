package com.lea.provider.controller;

import com.lea.api.config.HanProviderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SpringConfigController {

    @Autowired
    private HanProviderConfiguration hanProviderConfiguration;

    @RequestMapping(value = "han")
    public Map<String, String> getValue() {
        Map<String, String> map = new HashMap<>();
        map.put("host", hanProviderConfiguration.getHost());
        return map;
    }

}
