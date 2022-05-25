package com.example.testapp;

public class WeatherModel {

    // Stores parameter needed for widget created in weather_item.xml

    private String time;
    private String temperature;
    private String icon;
    private String wind_speed;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public WeatherModel(String time, String temperature, String icon, String wind_speed) {
        this.time = time;
        this.temperature = temperature;
        this.icon = icon;
        this.wind_speed = wind_speed;


    }
}
