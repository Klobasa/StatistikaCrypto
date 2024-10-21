package cz.stolz.statistikakrypto.data;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoPrices {
    private Map<String, Crypto> cryptocurrencies = new HashMap<>();

    @JsonAnySetter
    public void setCryptocurrency(String name, Crypto value) {
        cryptocurrencies.put(name, value);
    }

    public Map<String, Crypto> getCryptocurrencies() {
        return cryptocurrencies;
    }
}
