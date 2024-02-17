package com.phone.data.Service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillingService {
    void fetchAndSaveTxnData(List<Object[]> transactionDatas, int i);
//    void saveSettlementRecords(List<Object[]> objects);

    String findByRespRecTime();
    String findByRespRecTimeAxis();
    String findByRespRecTimeKvb();
    String findByRespRecTimeHitachi();
    String findByRespRecTimeMobikwik();
    String findByRespRecTimestaticqr();
    String findByDateTimeNotificationData();
    Long findByNotificationFields();
    List<String> insertedcounts();


}
