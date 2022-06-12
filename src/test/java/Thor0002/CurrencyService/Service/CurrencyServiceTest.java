package Thor0002.CurrencyService.Service;

import Thor0002.CurrencyService.FeignClient.FeignCurrency;
import Thor0002.CurrencyService.Model.Rate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SpringBootTest
class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @MockBean
    private FeignCurrency client;

    @Value("${currency.app.id}")
    private String appId;

    @Value("${currency.base}")
    private String base;

    @Test
    void testGetCurrentRate() {
        LocalDate today = java.time.LocalDate.now();
        String yesterday = today.minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String symbols = "EUR";
        Rate rate = new Rate(Map.of(base, 0.95057));

        Mockito.when(client.getHistoricalCurrency(yesterday, appId, base, symbols)).thenReturn(rate);
        Double result = currencyService.getYesterdaysRate(symbols);
        assert(result != null );
    }

    @Test
    void testGetYesterdaysRate(){
        String symbols = "EUR";
        Rate rate = new Rate(Map.of(base, 0.95057));

        Mockito.when(client.getLatestCurrency(appId, base, symbols)).thenReturn(rate);
        Double result = currencyService.getCurrentRate(symbols);
        assert(result != null );
    }
}