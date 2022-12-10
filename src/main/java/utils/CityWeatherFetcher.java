package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.WeatherDTO;

import javax.ws.rs.WebApplicationException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CityWeatherFetcher {

    public static WeatherDTO getData(String cityName, long cityId) throws IOException {
        System.out.println(cityName);
        try {
            String fetchUrl = String.format("https://weatherdbi.herokuapp.com/data/weather/%s/", cityName);
            System.out.println(fetchUrl);
            String weatherJSON = HttpUtils.fetchData(fetchUrl);
            JsonObject json = JsonParser.parseString(weatherJSON).getAsJsonObject();

            String iconURL = json.get("currentConditions").getAsJsonObject().get("iconURL").getAsString();
            String temp = json.get("currentConditions").getAsJsonObject().get("temp").getAsJsonObject().get("c").getAsString();
            String wind = json.get("currentConditions").getAsJsonObject().get("wind").getAsJsonObject().get("km").getAsString();

            WeatherDTO fetchedDTO = new WeatherDTO(temp, wind, iconURL, cityId, cityName);
            return fetchedDTO;

        } catch (FileNotFoundException e) {
            throw new WebApplicationException("Weather data not found", 404);
        }
    }
}
