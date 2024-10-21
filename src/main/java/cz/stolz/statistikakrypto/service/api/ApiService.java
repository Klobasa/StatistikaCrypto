package cz.stolz.statistikakrypto.service.api;

import cz.stolz.statistikakrypto.data.Crypto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ApiService {
    Optional<List<Crypto>> getPrice(String currency);

}
