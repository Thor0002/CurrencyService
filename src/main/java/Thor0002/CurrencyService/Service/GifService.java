package Thor0002.CurrencyService.Service;

import Thor0002.CurrencyService.FeignClient.FeignGif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifService {

    @Autowired
    private FeignGif client;

    @Value("${gif.api.key}")
    private String apiKey;

    public String getGifId(String tag){
        return client.getRandomGif(apiKey, tag).getData().getId();
    }
}
