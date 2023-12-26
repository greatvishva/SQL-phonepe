package com.phone.data.Repository;

import com.phone.data.Entity.PhonepeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepo extends JpaRepository<PhonepeTransaction,String> {
    @Query(value = "SELECT top 1 (RequestRouteTime) FROM Billing_Data order by RequestRouteTime desc", nativeQuery = true)
    String findByRespRecTime();


    @Query(value = "select count(*) from Billing_Data where  RequestRouteTime like '?1%'",nativeQuery = true)
    String yesterdayCount(String yesterday);
}
