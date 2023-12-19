package com.phone.data.Impl;


import com.phone.data.Entity.*;
import com.phone.data.Repository.*;
import com.phone.data.Service.BillingService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class Billingimpl implements BillingService {



    private final Logger logger = LoggerFactory.getLogger(Billingimpl.class);

    private final Logger phonepeInfo = LoggerFactory.getLogger("Phonepe_Info");
    private final Logger kVBInfo = LoggerFactory.getLogger("KVB_Info");
    private final Logger axisInfo = LoggerFactory.getLogger("Axis_Info");
    private final Logger hitachiInfo = LoggerFactory.getLogger("Hitachi_Info");
    private final Logger mobikwikInfo = LoggerFactory.getLogger("Mobikwik_Info");
    @Autowired
    private BillingRepo billingModelRepository;
    @Autowired
    private BillingAxisRepo billingAxisRepo;
    @Autowired
    private BillingKvbRepo billingKvbRepo;
    @Autowired
    private BillingHitachiRepo billingHitachiRepo;
    @Autowired
    private BillingMobikwikRepo billingMobikwikRepo;

    @Autowired
    private SimpleJpaRepository simpleJpaRepository;

    @Override
    public void fetchAndSaveTxnData(List<Object[]> transactionDatas, int action) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (action == 1) {
                    //phonepe
                    System.gc();
                    logger.info("Phonepe Data after fetch");
                    saveSettlementRecords(transactionDatas);
                    logger.info("Phonepe Data Completed");

                } else if (action == 2) {
                    //Axis
                    System.gc();
                    logger.info("Axis Data after fetch");
                    saveSettlementRecordsAxis(transactionDatas);
                    logger.info("Axis Data Completed");
                } else if (action == 3) {
                    //kvb
                    System.gc();
                    logger.info("KVB Data after fetch");
                    saveSettlementRecordskvb(transactionDatas);
                    logger.info("KVB Data Completed");
                } else if (action == 4) {
                    //kvb
                    System.gc();
                    logger.info("Mobikwik Data after fetch");
                    saveSettlementRecordsMobikwik(transactionDatas);
                    logger.info("Mobikwik Data Completed");
                } else if (action == 5) {
                    //kvb
                    System.gc();
                    logger.info("Hitachi Data after fetch");
                    saveSettlementRecordsHitachi(transactionDatas);
                    logger.info("Hitachi Data Completed");
                } else {
                    logger.info("else part");
                }
            }
        }).start();

    }


    @Transactional()
    public void saveSettlementRecords(List<Object[]> objects) {
        List<BillingEntity> transactions = new ArrayList<>();
        phonepeInfo.info("Transaction Size phonepe {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    BillingEntity data = new BillingEntity();
                    data.setId(String.valueOf(o[count++]));
                    data.setTerminalId(String.valueOf(o[count++]));
                    data.setMerchantId(String.valueOf(o[count++]));
                    data.setMTI(String.valueOf(o[count++]));
                    data.setTxnType(String.valueOf(o[count++]));
                    data.setStan(String.valueOf(o[count++]));
                    data.setAuthResponseCode(String.valueOf(o[count++]));
                    data.setTxnResponseCode(String.valueOf(o[count++]));
                    data.setTxnAmount(String.valueOf(o[count++]));
                    data.setTxnAdditionalAmount(String.valueOf(o[count++]));
                    data.setBatchNumber(String.valueOf(o[count++]));
                    data.setInvoiceNumber(String.valueOf(o[count++]));
                    data.setRRNumber(String.valueOf(o[count++]));
                    data.setInstitutionId(String.valueOf(o[count++]));
                    data.setRequestRouteTime(String.valueOf(o[count++]));
                    data.setResponseReceivedTime(String.valueOf(o[count]));
                    transactions.add(data);
                    count = 0;
                });
                phonepeInfo.info("phonepe Data set completed");

                billingModelRepository.saveAll(transactions);

                phonepeInfo.info("phonepe Data saved in DB");

}
    }catch(Exception e) {
            phonepeInfo.info("Error while save record phonepe----------{}",e.getMessage());

            e.printStackTrace();
            }
            }


    @Transactional()
    public void saveSettlementRecordsAxis(List<Object[]> objects) {
        List<BillingEntityAxis> transactions = new ArrayList<>();
        axisInfo.info("Axis Transaction Size {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    BillingEntityAxis data = new BillingEntityAxis();
                    data.setId(String.valueOf(o[count++]));
                    data.setTerminalId(String.valueOf(o[count++]));
                    data.setMerchantId(String.valueOf(o[count++]));
                    data.setMTI(String.valueOf(o[count++]));
                    data.setTxnType(String.valueOf(o[count++]));
                    data.setStan(String.valueOf(o[count++]));
                    data.setAuthResponseCode(String.valueOf(o[count++]));
                    data.setTxnResponseCode(String.valueOf(o[count++]));
                    data.setTxnAmount(String.valueOf(o[count++]));
                    data.setTxnAdditionalAmount(String.valueOf(o[count++]));
                    data.setBatchNumber(String.valueOf(o[count++]));
                    data.setInvoiceNumber(String.valueOf(o[count++]));
                    data.setRRNumber(String.valueOf(o[count++]));
                    data.setInstitutionId(String.valueOf(o[count++]));
                    data.setRequestRouteTime(String.valueOf(o[count++]));
                    data.setResponseReceivedTime(String.valueOf(o[count]));
                    transactions.add(data);
                    count = 0;
                });
                axisInfo.info("Axis Data set completed");

                billingAxisRepo.saveAll(transactions);

                axisInfo.info("Axis Data saved in DB");

            }
        }catch(Exception e) {
            axisInfo.info("Error while save record Axis----------{}",e.getMessage());
            e.printStackTrace();
        }
    }

    @Transactional()
    public void saveSettlementRecordskvb(List<Object[]> objects) {
        List<BillingEntityKvb> transactions = new ArrayList<>();
        kVBInfo.info("Transaction Size kvb {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    BillingEntityKvb data = new BillingEntityKvb();
                    data.setId(String.valueOf(o[count++]));
                    data.setTerminalId(String.valueOf(o[count++]));
                    data.setMerchantId(String.valueOf(o[count++]));
                    data.setMTI(String.valueOf(o[count++]));
                    data.setTxnType(String.valueOf(o[count++]));
                    data.setStan(String.valueOf(o[count++]));
                    data.setAuthResponseCode(String.valueOf(o[count++]));
                    data.setTxnResponseCode(String.valueOf(o[count++]));
                    data.setTxnAmount(String.valueOf(o[count++]));
                    data.setTxnAdditionalAmount(String.valueOf(o[count++]));
                    data.setBatchNumber(String.valueOf(o[count++]));
                    data.setInvoiceNumber(String.valueOf(o[count++]));
                    data.setRRNumber(String.valueOf(o[count++]));
                    data.setInstitutionId(String.valueOf(o[count++]));
                    data.setRequestRouteTime(String.valueOf(o[count++]));
                    data.setResponseReceivedTime(String.valueOf(o[count]));
                    transactions.add(data);
                    count = 0;
                });
                kVBInfo.info("kvb Data set completed");

                billingKvbRepo.saveAll(transactions);

                kVBInfo.info("kvb Data saved in DB");

            }
        }catch(Exception e) {
            kVBInfo.info("Error while save record kvb ----------{}",e.getMessage());
            e.printStackTrace();
        }
    }


    @Transactional()
    public void saveSettlementRecordsHitachi(List<Object[]> objects) {
        List<BillingEntityHitachi> transactions = new ArrayList<>();
        hitachiInfo.info("Transaction Size Hitachi {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    BillingEntityHitachi data = new BillingEntityHitachi();
                    data.setId(String.valueOf(o[count++]));
                    data.setTerminalId(String.valueOf(o[count++]));
                    data.setMerchantId(String.valueOf(o[count++]));
                    data.setMTI(String.valueOf(o[count++]));
                    data.setTxnType(String.valueOf(o[count++]));
                    data.setStan(String.valueOf(o[count++]));
                    data.setAuthResponseCode(String.valueOf(o[count++]));
                    data.setTxnResponseCode(String.valueOf(o[count++]));
                    data.setTxnAmount(String.valueOf(o[count++]));
                    data.setTxnAdditionalAmount(String.valueOf(o[count++]));
                    data.setBatchNumber(String.valueOf(o[count++]));
                    data.setInvoiceNumber(String.valueOf(o[count++]));
                    data.setRRNumber(String.valueOf(o[count++]));
                    data.setInstitutionId(String.valueOf(o[count++]));
                    data.setRequestRouteTime(String.valueOf(o[count++]));
                    data.setResponseReceivedTime(String.valueOf(o[count]));
                    transactions.add(data);
                    count = 0;
                });
                hitachiInfo.info("Hitachi Data set completed");

                billingHitachiRepo.saveAll(transactions);
//                billingHitachiRepo.ge

                hitachiInfo.info("Hitachi Data saved in DB");

            }
        }catch(Exception e) {
            hitachiInfo.info("Error while save record Hitachi ----------{}",e.getMessage());
            e.printStackTrace();
        }
    }

    @Transactional()
    public void saveSettlementRecordsMobikwik(List<Object[]> objects) {
        List<BillingEntityMobikwik> transactions = new ArrayList<>();
        mobikwikInfo.info("Transaction Size Mobikwik {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    BillingEntityMobikwik data = new BillingEntityMobikwik();
                    data.setId(String.valueOf(o[count++]));
                    data.setTerminalId(String.valueOf(o[count++]));
                    data.setMerchantId(String.valueOf(o[count++]));
                    data.setMTI(String.valueOf(o[count++]));
                    data.setTxnType(String.valueOf(o[count++]));
                    data.setStan(String.valueOf(o[count++]));
                    data.setAuthResponseCode(String.valueOf(o[count++]));
                    data.setTxnResponseCode(String.valueOf(o[count++]));
                    data.setTxnAmount(String.valueOf(o[count++]));
                    data.setTxnAdditionalAmount(String.valueOf(o[count++]));
                    data.setBatchNumber(String.valueOf(o[count++]));
                    data.setInvoiceNumber(String.valueOf(o[count++]));
                    data.setRRNumber(String.valueOf(o[count++]));
                    data.setInstitutionId(String.valueOf(o[count++]));
                    data.setRequestRouteTime(String.valueOf(o[count++]));
                    data.setResponseReceivedTime(String.valueOf(o[count]));
                    transactions.add(data);
                    count = 0;
                });
                mobikwikInfo.info("Mobikwik Data set completed");

                billingMobikwikRepo.saveAll(transactions);

                mobikwikInfo.info("Mobikwik Data saved in DB");

            }
        }catch(Exception e) {
            mobikwikInfo.info("Error while save record Mobikwik ----------{}",e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String findByRespRecTime() {        return billingModelRepository.findByRespRecTime();
        }
    @Override
    public String findByRespRecTimeAxis() {
        return billingAxisRepo.findByRespRecTimeAxis();
    }

    @Override
    public String findByRespRecTimeKvb() {
        return billingKvbRepo.findByRespRecTimeKvb();
    }

    @Override
    public String findByRespRecTimeHitachi() {
        return billingHitachiRepo.findByRespRecTimeHitachi();
    }

    @Override
    public String findByRespRecTimeMobikwik() {
        return billingMobikwikRepo.findByRespRecTimeMobikwik();
    }
}
