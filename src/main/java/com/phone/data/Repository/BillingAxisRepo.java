package com.phone.data.Repository;

import com.phone.data.Entity.BillingEntityAxis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAxisRepo extends JpaRepository<BillingEntityAxis,String> {

    @Query(value = "SELECT top 1 (RequestRouteTime) FROM Billing_Data_Axis order by RequestRouteTime desc", nativeQuery = true)
    String findByRespRecTimeAxis();
}
