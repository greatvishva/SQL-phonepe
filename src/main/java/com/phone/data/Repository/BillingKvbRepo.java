package com.phone.data.Repository;

import com.phone.data.Entity.BillingEntityKvb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingKvbRepo extends JpaRepository<BillingEntityKvb,String> {
    @Query(value = "SELECT top 1 (RequestRouteTime) FROM Billing_Data_Kvb order by RequestRouteTime desc", nativeQuery = true)
    String findByRespRecTimeKvb();

}
