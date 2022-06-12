package Thor0002.CurrencyService.Controller;

import Thor0002.CurrencyService.Service.CurrencyService;
import Thor0002.CurrencyService.Service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class RestApiController {

    @Autowired
    private GifService gifService;
    @Autowired CurrencyService currencyService;

    @GetMapping("/{currency}")
    public String resultPage(@PathVariable(name = "currency") String currency, Model model){
        Double currentRate = currencyService.getCurrentRate(currency);
        Double yesterdayRate = currencyService.getYesterdaysRate(currency);
        if(currentRate == null || yesterdayRate == null) return "<p style=\"font-size: 20px;\">Такой валюты нет</p>";
        if(currentRate > yesterdayRate){
            String id = gifService.getGifId("rich");
            String gifUrl = "https://i.giphy.com/" + id + ".gif";
            return "<p style=\"font-size: 20px;\">Курс выше чем вчера</p>" + "<br>" +
                    "<img src=\"" + gifUrl + "\"/>";
        } else if(currentRate < yesterdayRate){
            String id = gifService.getGifId("broke");
            String gifUrl = "https://i.giphy.com/" + id + ".gif";
            return "<p style=\"font-size: 20px;\">Курс ниже чем вчера</p>" + "<br>" +
                    "<img src=\"" + gifUrl + "\"/>";
        } else {
            String id = gifService.getGifId("waiting");
            String gifUrl = "https://i.giphy.com/" + id + ".gif";
            return "<p style=\"font-size: 20px;\">Курс не изменился</p>" + "<br>" +
                    "<img src=\"" + gifUrl + "\"/>";
        }


    }
}
