package Thor0002.CurrencyService.Service;

import Thor0002.CurrencyService.FeignClient.FeignCurrency;
import Thor0002.CurrencyService.Model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CurrencyService {

    @Autowired
    FeignCurrency client;

    @Value("${currency.app.id}")
    private String appId;

    @Value("${currency.base}")
    private String base;

    public Double getCurrentRate(String symbols){
        Rate rate = client.getLatestCurrency(appId, base, symbols);
        if (rate == null) return null;
        return rate.getRates().get(symbols);
    }

    public Double getYesterdaysRate(String symbols){
        LocalDate today = java.time.LocalDate.now();
        String yesterday = today.minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Rate rate = client.getHistoricalCurrency(yesterday, appId, base, symbols);
        if (rate == null) return null;
        return rate.getRates().get(symbols);
    }
}
