package dtos;

public class WeatherDTO {
    private final String temp;
    private final String wind;
    private final String iconURL;

    public WeatherDTO(String temp, String wind, String iconURL) {
        this.temp = temp;
        this.wind = wind;
        this.iconURL = iconURL;
    }

    public String getTemp() {
        return temp;
    }

    public String getWind() {
        return wind;
    }

    public String getIconURL() {
        return iconURL;
    }
}
