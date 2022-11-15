package dtos;

public class WeatherDTO {
    private String temp;
    private String wind;
    private String iconURL;

    private long cityId;
    private String cityName;

    public WeatherDTO(String temp, String wind, String iconURL, long cityId, String cityName) {
        this.temp = temp;
        this.wind = wind;
        this.iconURL = iconURL;
        this.cityId = cityId;
        this.cityName = cityName;
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
