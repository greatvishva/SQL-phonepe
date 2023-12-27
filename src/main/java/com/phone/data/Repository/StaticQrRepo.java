package com.phone.data.Repository;

import com.phone.data.Entity.StaticQrTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaticQrRepo extends JpaRepository<StaticQrTxn,Long> {
    @Query(value = "SELECT top 1 (created_at) FROM static_qr_txn order by created_at desc", nativeQuery = true)
    String findByRespRecTime();

    @Query(value = "select count(*) from static_qr_txn where  created_at like '?1%'",nativeQuery = true)
//    @Query(value = "select count(*) from static_qr_txn ",nativeQuery = true)
    String yesterdayCount(String yesterday);
}
