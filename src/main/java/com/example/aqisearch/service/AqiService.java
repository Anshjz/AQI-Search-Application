package com.example.aqisearch.service;

import com.example.aqisearch.dto.AqiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Service
public class AqiService {

    private static final String API_KEY = "YOUR_API_KEY";

    @Cacheable(
        value = "aqiCache",
        key = "#city.toLowerCase()",
        unless = "#result == null"
    )
    public AqiResponse getAqiData(String city) {

        System.out.println("Calling external AQI API for city: " + city);

        Map<String, Object> rawResponse = fetchFromApi(city);
        return convertToCleanResponse(city, rawResponse);
    }

    private Map<String, Object> fetchFromApi(String city) {
        try {
            String apiUrl = "https://api.waqi.info/feed/" + city + "/?token=" + API_KEY;

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(con.getInputStream(), Map.class);

        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("error", "Unable to fetch AQI data");
        }
    }

   private AqiResponse convertToCleanResponse(String city, Map<String, Object> raw) {

    if (!"ok".equals(raw.get("status"))) {
        return errorResponse(city);
    }

    Map<String, Object> data = (Map<String, Object>) raw.get("data");

    int aqi = (int) data.get("aqi");
    String dominant = (String) data.get("dominentpol");
    String timestamp = (String) ((Map<?, ?>) data.get("time")).get("s");

    AqiResponse response = new AqiResponse();
    response.setCity(city);
    response.setAqi(aqi);
    response.setDominantPollutant(dominant);
    response.setTimestamp(timestamp);
    response.setCategory(calculateCategory(aqi));

    return response;
}


    private AqiResponse errorResponse(String city) {
        AqiResponse error = new AqiResponse();
        error.setCity(city);
        error.setAqi(-1);
        error.setCategory("Unavailable");
        error.setDominantPollutant("-");
        error.setTimestamp("-");
        return error;
    }

    private String calculateCategory(int aqi) {
        if (aqi <= 50) return "Good";
        if (aqi <= 100) return "Moderate";
        if (aqi <= 200) return "Poor";
        if (aqi <= 300) return "Very Poor";
        return "Severe";
    }
}
