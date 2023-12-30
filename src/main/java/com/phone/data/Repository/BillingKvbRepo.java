package com.phone.data.Repository;

import com.phone.data.Entity.KVBTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingKvbRepo extends JpaRepository<KVBTransaction,String> {
    @Query(value = "SELECT top 1 (RequestRouteTime) FROM kvb_transaction order by RequestRouteTime desc", nativeQuery = true)
    String findByRespRecTimeKvb();

    @Query(value = "select count(*) from kvb_transaction where  RequestRouteTime like ?1% ",nativeQuery = true)
//    @Query(value = "select count(*) from kvb_transaction ",nativeQuery = true)
    String yesterdayCount(String yesterday);
}
