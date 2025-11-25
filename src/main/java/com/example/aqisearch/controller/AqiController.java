package com.example.aqisearch.controller;

import com.example.aqisearch.dto.AqiResponse;
import com.example.aqisearch.service.AqiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AqiController {

    private final AqiService aqiService;

    public AqiController(AqiService aqiService) {
        this.aqiService = aqiService;
    }

    @GetMapping("/aqi")
    public AqiResponse getAqi(@RequestParam String city) {
        return aqiService.getAqiData(city);
    }
}
