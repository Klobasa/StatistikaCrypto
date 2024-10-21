package cz.stolz.statistikakrypto.service;

import cz.stolz.statistikakrypto.data.CryptoCronConfig;
import cz.stolz.statistikakrypto.repository.CryptoCronConfigRepository;
import cz.stolz.statistikakrypto.service.api.CoinGeckoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
@EnableScheduling
public class DynamicSchedulerService implements SchedulingConfigurer {
    private final CryptoCronConfigRepository repository;
    private ScheduledTaskRegistrar taskRegistrar;
    private final CryptoService cryptoService;
    private final Map<Long, ScheduledFuture<?>> scheduledTasks = new HashMap<>();


    @Autowired
    public DynamicSchedulerService(CryptoCronConfigRepository repository, CryptoService cryptoService) {
        this.repository = repository;
        this.cryptoService = cryptoService;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
        scheduleAllTasks();
    }

    public void scheduleAllTasks() {
        List<CryptoCronConfig> configs = repository.findAll();
        for (CryptoCronConfig config : configs) {
            scheduleTask(config);
        }
    }

    private void scheduleTask(CryptoCronConfig cron) {
        ScheduledFuture<?> scheduledTask = taskRegistrar.getScheduler().scheduleAtFixedRate(() -> {
                cryptoService.getDataAndSave(cron.getCurrency());
            },
            Duration.ofSeconds(cron.getUpdateInterval())
        );
        scheduledTasks.put(cron.getId(), scheduledTask);
    }

    public void updateTask(CryptoCronConfig cron) {
        ScheduledFuture<?> existingTask = scheduledTasks.remove(cron.getId());
        if (existingTask != null) {
            existingTask.cancel(false);
        }
        scheduleTask(cron);
    }

    public void removeTask(Long configId) {
        ScheduledFuture<?> existingTask = scheduledTasks.remove(configId);
        if (existingTask != null) {
            existingTask.cancel(false);
        }
    }
}
