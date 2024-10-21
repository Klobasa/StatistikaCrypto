package cz.stolz.statistikakrypto.service;

import cz.stolz.statistikakrypto.data.CryptoCronConfig;
import cz.stolz.statistikakrypto.repository.CryptoCronConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CryptoCronService {
    private final CryptoCronConfigRepository cryptoCronConfigRepository;
    private final DynamicSchedulerService dynamicSchedulerService;

    @Autowired
    public CryptoCronService(CryptoCronConfigRepository cryptoCronConfigRepository, DynamicSchedulerService dynamicSchedulerService) {
        this.cryptoCronConfigRepository = cryptoCronConfigRepository;
        this.dynamicSchedulerService = dynamicSchedulerService;
    }

    public void saveNewCron(CryptoCronConfig cryptoCronConfig) {
        cryptoCronConfigRepository.save(cryptoCronConfig);
        dynamicSchedulerService.updateTask(cryptoCronConfig);
        System.out.println(LocalDateTime.now() + "  Saved new cron: " + cryptoCronConfig.getCurrency() + ", " + cryptoCronConfig.getUpdateInterval() +"s");
    }

    public void saveNewCron(String currency, int interval) {
        saveNewCron(new CryptoCronConfig(currency, interval));
    }

    public void removeCron(String currency) {
        CryptoCronConfig cryptoCronConfig = cryptoCronConfigRepository.findFirstByCurrency(currency);
        dynamicSchedulerService.removeTask(cryptoCronConfig.getId());
        cryptoCronConfigRepository.delete(cryptoCronConfig);
        System.out.println(LocalDateTime.now() + "  Removed currency \"" + currency + "\" from cron");
    }

    public List<CryptoCronConfig> getAllCrons() {
        return cryptoCronConfigRepository.findAll();
    }


}
