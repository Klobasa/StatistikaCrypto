package cz.stolz.statistikakrypto.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.stolz.statistikakrypto.data.Crypto;
import cz.stolz.statistikakrypto.data.CryptoPrices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CoinGeckoApiService implements ApiService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${app.coingecko.api-url}")
    private String url;

    @Value("${app.coingecko.api-key}")
    private String apiKey;


    @Override
    public Optional<List<Crypto>> getPrice(String currency) {
        System.out.println(LocalDateTime.now() + "  Calling CoingGeckoAPI for \"" + currency +"\"");
        HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + currency))
                .GET()
                .header("accept", "application/json")
                .header("x-cg-api-key", apiKey)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                CryptoPrices cryptoPrices = objectMapper.readValue(response.body(), CryptoPrices.class);
                List<Crypto> cryptoList = new ArrayList<>();

                for (Map.Entry<String, Crypto> entry : cryptoPrices.getCryptocurrencies().entrySet()) {
                    cryptoList.add(new Crypto(entry.getKey(), entry.getValue().getTimestamp(),
                                        LocalDateTime.now(), entry.getValue().getPrice()));
                }
                return Optional.of(cryptoList);

            } else {
                System.out.println("LocalDateTime.now() + " + response.statusCode() + " " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("LocalDateTime.now() + " + e);
        }
        return Optional.empty();
    }
}
