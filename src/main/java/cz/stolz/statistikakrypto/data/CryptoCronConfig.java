package cz.stolz.statistikakrypto.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cron_config")
public class CryptoCronConfig {
    @Id
    @GeneratedValue
    private Long id;
    private String currency;
    private int updateInterval;

    public CryptoCronConfig(String currency, int interval) {
        this.currency = currency;
        this.updateInterval = interval;
    }

    public CryptoCronConfig() {

    }

    public Long getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setUpdateInterval(int interval) {
        this.updateInterval = interval;
    }
}
