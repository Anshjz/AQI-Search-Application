package com.example.aqisearch.dto;

public class AqiResponse {
    private String city;
    private int aqi;
    private String category;
    private String dominantPollutant;
    private String timestamp;

    // Getters & Setters
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public int getAqi() { return aqi; }
    public void setAqi(int aqi) { this.aqi = aqi; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDominantPollutant() { return dominantPollutant; }
    public void setDominantPollutant(String dominantPollutant) { this.dominantPollutant = dominantPollutant; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
