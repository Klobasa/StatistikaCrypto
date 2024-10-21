package cz.stolz.statistikakrypto.repository;

import cz.stolz.statistikakrypto.data.CryptoCronConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CryptoCronConfigRepository extends JpaRepository<CryptoCronConfig, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM CryptoCronConfig cc WHERE cc.currency = :currency")
    void deleteByCurrency(@Param("currency") String currency);

    CryptoCronConfig findFirstByCurrency(String currency);
}
