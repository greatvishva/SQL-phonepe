package com.phone.data.Service;

import java.util.List;

public interface BillingService {
    void fetchAndSaveTxnData(List<Object[]> transactionDatas, int i);
    void saveSettlementRecords(List<Object[]> objects);

    String findByRespRecTime();
    String findByRespRecTimeAxis();
    String findByRespRecTimeKvb();
    String findByRespRecTimeHitachi();
    String findByRespRecTimeMobikwik();
}
