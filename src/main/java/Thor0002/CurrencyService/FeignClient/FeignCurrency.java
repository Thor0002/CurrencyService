package Thor0002.CurrencyService.FeignClient;

import Thor0002.CurrencyService.Model.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "currency", url = "${currency.url}")
public interface FeignCurrency {

    @GetMapping(value = "/historical/{date}.json", produces = "application/json")
    public Rate getHistoricalCurrency(@PathVariable("date") String date, @RequestParam("app_id") String appId, @RequestParam("base") String base, @RequestParam("symbols") String symbols);

    @GetMapping(value = "/latest.json")
    public Rate getLatestCurrency(@RequestParam("app_id")  String apiId, @RequestParam("base") String base, @RequestParam("symbols") String symbols);


}
