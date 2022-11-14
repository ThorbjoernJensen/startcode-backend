package dtos;

public class WeatherDTO {
    private String temp;
    private String wind;
    private String iconURL;

    private long cityId;

    public WeatherDTO(String temp, String wind, String iconURL, long cityId) {
        this.temp = temp;
        this.wind = wind;
        this.iconURL = iconURL;
        this.cityId = cityId;
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
