package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class WeatherParser {
    public Weather parseWeather(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(jsonResponse);

        String cityName = rootNode.get("name").asText();
        double temperature = rootNode.get("main").get("temp").asDouble();
        int humidity = rootNode.get("main").get("humidity").asInt();
        String description = rootNode.get("weather").get(0).get("description").asText();

        return new Weather(cityName, temperature,humidity,description);
    }
}
