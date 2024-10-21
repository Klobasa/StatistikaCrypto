package cz.stolz.statistikakrypto.repository;

import cz.stolz.statistikakrypto.data.Crypto;
import cz.stolz.statistikakrypto.data.PriceStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Long> {

    @Query("SELECT new cz.stolz.statistikakrypto.data.PriceStats(c.currencyId, MIN(c.price), MAX(c.price), AVG(c.price)) " +
            "FROM Crypto c " +
            "WHERE c.currencyId = :currency AND c.dateTime BETWEEN :startDate AND :endDate")
    PriceStats getPriceStatsBetweenDates(@Param("currency") String currency,
                                         @Param("startDate") LocalDateTime startDate,
                                         @Param("endDate") LocalDateTime endDate);
}
