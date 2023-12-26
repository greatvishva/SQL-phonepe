package com.phone.data.Repository;

import com.phone.data.Entity.HitachiTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingHitachiRepo extends JpaRepository<HitachiTransaction,String> {
    @Query(value = "SELECT top 1 (RequestRouteTime) FROM Billing_Data_Hitachi order by RequestRouteTime desc", nativeQuery = true)
    String findByRespRecTimeHitachi();

    @Query(value = "select count(*) from Billing_Data_Hitachi where  RequestRouteTime like '?1%'",nativeQuery = true)
    String yesterdayCount(String yesterday);
}
