
package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class WeatherService {

    public String getWeatherData(String city)
            throws IOException, InterruptedException {

        String apiKey = System.getenv("OPENWEATHER_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "API key is missing from environment variables."
            );
        }

        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException(
                    "City name cannot be empty."
            );
        }

        HttpClient client = HttpClient.newHttpClient();

        String encodedCity = URLEncoder.encode(
                city,
                StandardCharsets.UTF_8
        );

        String url = "https://api.openweathermap.org/data/2.5/weather"
                + "?q=" + encodedCity
                + "&appid=" + apiKey
                + "&units=imperial";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() == 401) {
            throw new RuntimeException("Error: Invalid API key. Please check your configuration.");
        } else if (response.statusCode() == 404) {
            throw new RuntimeException("Error: City not found. Please check the spelling.");
        } else if (response.statusCode() != 200) {
            throw new RuntimeException("Error: General API request failure. Status code: " + response.statusCode());
        }


        return response.body();
    }
}
