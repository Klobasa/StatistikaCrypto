package cz.stolz.statistikakrypto.data;

public class PriceStats {
    private String currency;
    private Double minPrice;
    private Double maxPrice;
    private Double avgPrice;

    public PriceStats(String currency, Double minPrice, Double maxPrice, Double avgPrice) {
        this.currency = currency;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.avgPrice = avgPrice;
    }

    public PriceStats() {}

    public String getCurrency() {
        return currency;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }
}
