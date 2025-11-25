package com.example.aqisearch.service;

import com.example.aqisearch.dto.AqiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class AqiService {

    private final Map<String, Map<String, Object>> cache = new HashMap<>();

    private static final String API_KEY = "adb27248808b2066232fbf3b6a57f4bff8a378be";
    private static final long CACHE_EXPIRY = 10 * 60 * 1000;

    public AqiResponse getAqiData(String city) {

        Map<String, Object> rawResponse;

        // return from cache if available
        if (cache.containsKey(city)) {
            rawResponse = cache.get(city);
        } else {
            rawResponse = fetchFromApi(city);
            cache.put(city, rawResponse);
        }

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

        if (raw.containsKey("error")) {
            AqiResponse error = new AqiResponse();
            error.setCity(city);
            error.setAqi(-1);
            error.setCategory("Unavailable");
            error.setDominantPollutant("-");
            error.setTimestamp("-");
            return error;
        }

        Map<String, Object> data = (Map<String, Object>) raw.get("data");

        int aqi = (int) data.get("aqi");
        String dominant = (String) data.get("dominentpol");
        String timestamp = (String) ((Map) data.get("time")).get("s");

        AqiResponse response = new AqiResponse();
        response.setCity(city);
        response.setAqi(aqi);
        response.setDominantPollutant(dominant);
        response.setTimestamp(timestamp);
        response.setCategory(calculateCategory(aqi));

        return response;
    }

    private String calculateCategory(int aqi) {
        if (aqi <= 50) return "Good";
        else if (aqi <= 100) return "Moderate";
        else if (aqi <= 200) return "Poor";
        else if (aqi <= 300) return "Very Poor";
        else return "Severe";
    }
}
