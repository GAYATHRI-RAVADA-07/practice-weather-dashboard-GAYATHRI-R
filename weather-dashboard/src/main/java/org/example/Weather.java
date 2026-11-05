package org.example;

public class Weather {

    private final String cityName;
    private final double temperature;
    private final int humidity;
    private final String description;

    public Weather(String cityName, double temperature, int humidity, String description) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }


    public double getTemperature() {
        return temperature;
    }


    public int getHumidity() {
        return humidity;
    }


    public String getDescription() {
        return description;
    }



}
