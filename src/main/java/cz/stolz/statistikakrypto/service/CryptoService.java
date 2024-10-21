package cz.stolz.statistikakrypto.service;

import cz.stolz.statistikakrypto.data.Crypto;
import cz.stolz.statistikakrypto.data.PriceStats;
import cz.stolz.statistikakrypto.repository.CryptoRepository;
import cz.stolz.statistikakrypto.service.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoService {
    private final CryptoRepository cryptoRepository;
    private final ApiService apiService;

    @Autowired
    public CryptoService(CryptoRepository cryptoRepository, ApiService apiService) {
        this.apiService = apiService;
        this.cryptoRepository = cryptoRepository;
    }

    public void getDataAndSave(String currency) {
        Optional<List<Crypto>> cryptos = apiService.getPrice(currency);
        if (cryptos.isPresent()) {
            for (Crypto crypto : cryptos.get()) {
                saveNewRecord(crypto);
            }
        }
    }

    public void saveNewRecord(Crypto record) {
        cryptoRepository.save(record);
    }

    public PriceStats getPriceStats(String currency, LocalDateTime startDate, LocalDateTime endDate) {
        return cryptoRepository.getPriceStatsBetweenDates(currency, startDate, endDate);
    }
}
