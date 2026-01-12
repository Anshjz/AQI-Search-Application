package com.example.aqisearch.controller;

import com.example.aqisearch.dto.AqiResponse;
import com.example.aqisearch.service.AqiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AqiController {

    private final AqiService aqiService;

    public AqiController(AqiService aqiService) {
        this.aqiService = aqiService;
    }

    @GetMapping("/aqi")
    public ResponseEntity<AqiResponse> getAqi(@RequestParam String city) {
        return ResponseEntity.ok(aqiService.getAqiData(city));
    }
}
