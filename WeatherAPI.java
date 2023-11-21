/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * The {@code WeatherAPI} class provides methods to retrieve weather data from the WeatherAPI (https://www.weatherapi.com/).
 * The class uses the API endpoint URL with parameters to retrieve weather data for London, Ontario.
 */
public class WeatherAPI {

    /**
     * The API endpoint URL with parameters to retrieve weather data for London, Ontario.
     */
    private static final String ENDPOINT = "http://api.weatherapi.com/v1/current.json?key=7c8bc3a9e8274a9ea6c195207232403&q=london,Ontario&aqi=no";

    /**
     * Retrieves the URL of the weather icon for London, Ontario from the API response.
     *
     * @return a URL object representing the URL of the weather icon for London, Ontario
     * @throws IOException if an I/O error occurs while retrieving the response from the API
     */
    public static URL getWeatherIconUrl() throws IOException {
        /**
         * Opens a stream to the API endpoint URL to retrieve the weather data.
         */
        InputStream response = new URL(ENDPOINT).openStream();

        /**
         * Creates a Scanner object to read the response from the stream.
         */
        Scanner scanner = new Scanner(response);

        /**
         * Uses the Scanner object to read the entire response into a String.
         */
        String responseBody = scanner.useDelimiter("\\A").next();

        /**
         * Closes the Scanner and InputStream objects.
         */
        scanner.close();
        response.close();

        /**
         * Parses the JSON response to retrieve the URL of the weather icon for London, Ontario.
         */
        String iconUrl = "";
        int index = responseBody.indexOf("\"icon\":\"");
        if (index >= 0) {
            iconUrl = responseBody.substring(index + "\"icon\":\"".length());
            iconUrl = iconUrl.substring(0, iconUrl.indexOf("\""));
            iconUrl = "https:" + iconUrl;
        }

        /**
         * Returns a URL object representing the URL of the weather icon for London, Ontario.
         */
        return new URL(iconUrl);
    }

    /**
     * Retrieves the current temperature in Celsius for London, Ontario from the API response.
     *
     * @return the current temperature in Celsius for London, Ontario
     * @throws IOException if an I/O error occurs while retrieving the response from the API
     */
    public static double getCurrentTemperature() throws IOException {
        /**
         * Opens a stream to the API endpoint URL to retrieve the weather data.
         */
        InputStream response = new URL(ENDPOINT).openStream();

        /**
         * Creates a Scanner object to read the response from the stream.
         */
        Scanner scanner = new Scanner(response);

        /**
         * Uses the Scanner object to read the entire response into a String.
         */
        String responseBody = scanner.useDelimiter("\\A").next();

        /**
         * Closes the Scanner and InputStream objects.
         */
        scanner.close();
        response.close();

        /**
         * Parses the JSON response to retrieve the current temperature in Celsius for London, Ontario.
         */
        double temperature = 0.0;
        int index = responseBody.indexOf("\"temp_c\":");
        if (index >= 0) {
            String tempStr = responseBody.substring(index + "\"temp_c\":".length());
            tempStr = tempStr.substring(0, tempStr.indexOf(","));
            temperature = Double.parseDouble(tempStr);
        }

        /**
         * Returns the current temperature in Celsius for London, Ontario.
         */
        return temperature;
    }
}

