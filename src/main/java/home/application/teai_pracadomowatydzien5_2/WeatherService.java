package home.application.teai_pracadomowatydzien5_2;

import home.application.teai_pracadomowatydzien5_2.Weather.ConsolidatedWeather;
import home.application.teai_pracadomowatydzien5_2.Weather.Weather;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private City city;
    private Weather weather;
    private ConsolidatedWeather consolidatedWeather;
    private int id = 44418;

    public WeatherService() {
        city = new City();
        weather = new Weather();
        consolidatedWeather = new ConsolidatedWeather();
    }

    public void getCityWeoid(String title) {

        City selectedCity = new City();
        HttpEntity httpEntity = new HttpEntity(selectedCity);

        RestTemplate restTemplateCity = new RestTemplate();
        ResponseEntity<City[]> responseEntity = restTemplateCity.exchange(
                String.format("https://www.metaweather.com/api/location/search/?query=%s", title),
                HttpMethod.GET,
                httpEntity,
                City[].class);

        if (responseEntity.getBody().length != 0) {
            this.city = responseEntity.getBody()[0];
            this.setId(responseEntity.getBody()[0].getWoeid());
        }

    }

    public Weather getWeather() {
        Weather weather = new Weather();
        HttpEntity httpEntity = new HttpEntity(weather);
        RestTemplate restTemplateWeather = new RestTemplate();

        ResponseEntity<Weather> weatherResponseEntity = restTemplateWeather.exchange(String.format("https://www.metaweather.com/api/location/%s/", getId()),
                HttpMethod.GET,
                httpEntity,
                Weather.class);
        this.weather = weatherResponseEntity.getBody();
        return this.weather;
    }

    public ConsolidatedWeather getConsolidatedWeather(){
        String linkForImage = String.format("https://www.metaweather.com/static/img/weather/%s.svg", getWeather().getConsolidatedWeather().get(0).getWeatherStateAbbr());
        consolidatedWeather = getWeather().getConsolidatedWeather().get(0);
        consolidatedWeather.setWeatherStateAbbrSrc(linkForImage);
        return consolidatedWeather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}





















