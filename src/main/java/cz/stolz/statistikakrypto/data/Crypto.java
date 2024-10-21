package cz.stolz.statistikakrypto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import java.time.LocalDateTime;

@Entity
public class Crypto {
    @Id
    @GeneratedValue
    private Long id;

    private String currencyId;
    @JsonProperty("last_updated_at")
    private Long timestamp;
    private LocalDateTime dateTime;
    @JsonProperty("eur")
    private Double price;

    public Crypto(String currencyId, Long timestamp, LocalDateTime dateTime, Double price) {
        this.currencyId = currencyId;
        this.timestamp = timestamp;
        this.dateTime = dateTime;
        this.price = price;
    }

    public Crypto() {

    }

    public String getCurrencyId() {
        return currencyId;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
