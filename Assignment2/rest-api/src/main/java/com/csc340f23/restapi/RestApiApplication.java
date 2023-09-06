package com.csc340f23.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
        getWeather();
        System.exit(0);
    }
        // It using the 3rd party API from getWeather and get the information from the City of Greensboro Weather
        // It using the Double
    public static void getWeather() {
        try {
            String url = "http://api.openweathermap.org/data/2.5/weather?q=Greensboro&units=metric&appid=1c927e88bcb8b6c3de5038faf9987962";

            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonWeather = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonWeather);

            String cityName = root.path("name").asText();
            JsonNode main = root.path("main");
            JsonNode weather = root.path("weather");
            // get the temp max and min from the 3rd party API from JsonNode
            double CurrentTemperature = main.path("temp").asDouble();
            double maxTemperature = main.path("temp_max").asDouble();
            double minTemperature = main.path("temp_min").asDouble();
            double humidity = main.path("humidity").asDouble();

            // Print data
            System.out.println("City: " + cityName);
            System.out.println("Current Temperature:" + CurrentTemperature + "°C" );
            System.out.println("Max Temperature: " + maxTemperature + "°C");
            System.out.println("Min Temperature: " + minTemperature + "°C");
            System.out.println("Humidity: " + humidity + "%");

        } catch (Exception ex) {
            System.out.println("Error in getWeather: " + ex.getMessage());
        }
    }
}
