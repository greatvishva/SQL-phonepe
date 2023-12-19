package com.phone.data.Repository;

import com.phone.data.Entity.BillingEntityMobikwik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingMobikwikRepo extends JpaRepository<BillingEntityMobikwik,String> {

    @Query(value = "SELECT top 1 (RequestRouteTime) FROM Billing_Data_Mobikwik order by RequestRouteTime desc", nativeQuery = true)
    String findByRespRecTimeMobikwik();
}
