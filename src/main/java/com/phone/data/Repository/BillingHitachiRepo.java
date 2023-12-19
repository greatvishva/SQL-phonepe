package com.phone.data.Repository;

import com.phone.data.Entity.BillingEntityHitachi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingHitachiRepo extends JpaRepository<BillingEntityHitachi,String> {
    @Query(value = "SELECT top 1 (RequestRouteTime) FROM Billing_Data_Hitachi order by RequestRouteTime desc", nativeQuery = true)
    String findByRespRecTimeHitachi();
}
