package com.phone.data.Repository;

import com.phone.data.Entity.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepo extends JpaRepository<BillingEntity,String> {
    @Query(value = "SELECT top 1 (RequestRouteTime) FROM Billing_Data order by RequestRouteTime desc", nativeQuery = true)
    String findByRespRecTime();


}
