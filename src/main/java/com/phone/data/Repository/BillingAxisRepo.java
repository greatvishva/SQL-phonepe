package com.phone.data.Repository;

import com.phone.data.Entity.AxisTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAxisRepo extends JpaRepository<AxisTransaction,String> {

    @Query(value = "SELECT top 1 (RequestRouteTime) FROM axis_transaction order by RequestRouteTime desc", nativeQuery = true)
    String findByRespRecTimeAxis();

    @Query(value = "select count(*) from axis_transaction where  RequestRouteTime like ?1% ",nativeQuery = true)
//    @Query(value = "select count(*) from axis_transaction ",nativeQuery = true)
    String yesterdayCount(String yesterday);
}
