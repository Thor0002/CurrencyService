package Thor0002.CurrencyService.FeignClient;

import Thor0002.CurrencyService.Model.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gif", url = "${gif.url}")
public interface FeignGif {

    @GetMapping
    public Gif getRandomGif(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);

}
