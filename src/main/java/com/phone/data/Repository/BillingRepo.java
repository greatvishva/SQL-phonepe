package com.phone.data.Repository;

import com.phone.data.Entity.PhonepeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepo extends JpaRepository<PhonepeTransaction,String> {
    @Query(value = "SELECT top 1 (RequestRouteTime) FROM phonepe_transaction order by RequestRouteTime desc", nativeQuery = true)
    String findByRespRecTime();


    @Query(value = "select count(*) from phonepe_transaction where  RequestRouteTime like '?1%'",nativeQuery = true)
//    @Query(value = "select count(*) from phonepe_transaction ",nativeQuery = true)
    String yesterdayCount(String yesterday);
}
