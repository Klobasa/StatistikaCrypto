package cz.stolz.statistikakrypto.controller;

import cz.stolz.statistikakrypto.data.PriceStats;
import cz.stolz.statistikakrypto.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path="/statistics", produces = "application/json")
public class CryptoController {
    private final CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping(value = "/get")
    public PriceStats getStats(@RequestParam String currency,
                               @RequestParam String startTime,
                               @RequestParam String endTime) {
        return cryptoService.getPriceStats(currency, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
    }
}
