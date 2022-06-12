package Thor0002.CurrencyService.Service;

import Thor0002.CurrencyService.FeignClient.FeignGif;
import Thor0002.CurrencyService.Model.Data;
import Thor0002.CurrencyService.Model.Gif;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class GifServiceTest {

    @Autowired
    GifService gifService;

    @MockBean
    private FeignGif client;

    @Value("${gif.api.key}")
    private String apiKey;

    @Test
    void testGetGifId(){
        Data data = new Data("h8IDmgTZmCqSfuwidi");
        Gif gif = new Gif(data);

        Mockito.when(client.getRandomGif(apiKey, "rich")).thenReturn(gif);
        String result = gifService.getGifId("rich");
        assert(result != null);
    }

}
