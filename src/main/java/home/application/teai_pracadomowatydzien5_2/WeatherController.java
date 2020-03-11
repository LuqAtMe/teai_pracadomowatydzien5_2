package home.application.teai_pracadomowatydzien5_2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController() {
        this.weatherService = new WeatherService();
    }

    @GetMapping("/city")
    public String showWeather(Model model) {
        model.addAttribute("newCity", new City());
        model.addAttribute("weatherInfo", weatherService.getWeather());
        model.addAttribute("weatherDetails", weatherService.getConsolidatedWeather());
        return "cityWeatherView";
    }

    @PostMapping("/find")
    public String find(@ModelAttribute City newCity){
        weatherService.getCityWeoid(newCity.getTitle());
        return "redirect:/city";
    }

}
