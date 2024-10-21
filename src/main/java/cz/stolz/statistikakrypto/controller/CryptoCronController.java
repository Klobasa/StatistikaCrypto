package cz.stolz.statistikakrypto.controller;

import cz.stolz.statistikakrypto.data.CryptoCronConfig;
import cz.stolz.statistikakrypto.service.CryptoCronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/cron", produces="application/json")
public class CryptoCronController {
    private final CryptoCronService cryptoCronService;


    @Autowired
    public CryptoCronController(CryptoCronService cryptoCronService) {
        this.cryptoCronService = cryptoCronService;
    }

    @GetMapping(value = "", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<CryptoCronConfig> getAllCrons() {
        return cryptoCronService.getAllCrons();
    }

    @PostMapping(value = "/new")
    public void newCron(@RequestParam String currency, @RequestParam int interval) {
        cryptoCronService.saveNewCron(currency, interval);
    }

    @PostMapping(value = "/remove")
    public void removeCron(@RequestParam String currency) {
        cryptoCronService.removeCron(currency);
    }



}
