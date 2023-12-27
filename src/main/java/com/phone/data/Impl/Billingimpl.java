package com.phone.data.Impl;


import com.phone.data.Entity.*;
import com.phone.data.Repository.*;
import com.phone.data.Service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Billingimpl implements BillingService {



    private final Logger logger = LoggerFactory.getLogger(Billingimpl.class);

    private final Logger phonepeInfo = LoggerFactory.getLogger("Phonepe_Info");
    private final Logger kVBInfo = LoggerFactory.getLogger("KVB_Info");
    private final Logger axisInfo = LoggerFactory.getLogger("Axis_Info");
    private final Logger hitachiInfo = LoggerFactory.getLogger("Hitachi_Info");
    private final Logger mobikwikInfo = LoggerFactory.getLogger("Mobikwik_Info");
    private final Logger staticqrInfo = LoggerFactory.getLogger("Staticqr_Info");
    @Autowired
    private BillingRepo billingModelRepository;
    @Autowired
    private StaticQrRepo staticQrRepo;
    @Autowired
    private BillingAxisRepo billingAxisRepo;
    @Autowired
    private BillingKvbRepo billingKvbRepo;
    @Autowired
    private BillingHitachiRepo billingHitachiRepo;
    @Autowired
    private BillingMobikwikRepo billingMobikwikRepo;



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
                } else if (action == 6) {
                    //staticQR
                    System.gc();
                    logger.info("staticQR Data after fetch");
                    saveSettlementRecordsStaticqr(transactionDatas);
                    logger.info("StaticQR Data Completed");
                } else {
                    logger.info("else part");
                }
            }
        }).start();

    }


    @Transactional()
    public void saveSettlementRecords(List<Object[]> objects) {
        List<PhonepeTransaction> transactions = new ArrayList<>();
        phonepeInfo.info("Transaction Size phonepe objects {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    PhonepeTransaction data = new PhonepeTransaction();
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
        List<AxisTransaction> transactions = new ArrayList<>();
        axisInfo.info("Axis Transaction Size objects {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    AxisTransaction data = new AxisTransaction();
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
        List<KVBTransaction> transactions = new ArrayList<>();
        kVBInfo.info("Transaction Size kvb objects {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    KVBTransaction data = new KVBTransaction();
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
        List<HitachiTransaction> transactions = new ArrayList<>();
        hitachiInfo.info("Transaction Size Hitachi objects {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    HitachiTransaction data = new HitachiTransaction();
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
        List<MobikwikTransaction> transactions = new ArrayList<>();
        mobikwikInfo.info("Transaction Size Mobikwik objects {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    MobikwikTransaction data = new MobikwikTransaction();
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
    @Transactional()
    public void saveSettlementRecordsStaticqr(List<Object[]> objects) {
        List<StaticQrTxn> transactions = new ArrayList<>();
        staticqrInfo.info("Transaction Size StaticQR objects {}", objects.size());
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    StaticQrTxn data = new StaticQrTxn();
                    data.setTxnId(String.valueOf(o[count++]));
                    data.setChecksum(String.valueOf(o[count++]));
                    data.setCreatedAt(String.valueOf(o[count++]));
                    data.setCreditVpa(String.valueOf(o[count++]));
                    data.setCustomerVpa(String.valueOf(o[count++]));
                    data.setGatewayResponseCode(String.valueOf(o[count++]));
                    data.setGatewayResponseMessage(String.valueOf(o[count++]));
                    data.setGatewayTransactionId(String.valueOf(o[count++]));
                    data.setMerchantChannelId(String.valueOf(o[count++]));
                    data.setMerchantId(String.valueOf(o[count++]));
                    data.setMerchantTransactionId(String.valueOf(o[count++]));
                    data.setRrn(String.valueOf(o[count++]));
                    data.setTransactionAmount(String.valueOf(o[count++]));
                    data.setTransactionTimestamp(String.valueOf(o[count++]));
                    data.setPayeractype(String.valueOf(o[count++]));
                    data.setInit_mode(String.valueOf(o[count++]));
                    data.setPurpose_code(String.valueOf(o[count]));
                    data.setBin(String.valueOf("bin"));
                    transactions.add(data);
                    count = 0;
                });
                staticqrInfo.info("StaticQR Data set completed");

                staticQrRepo.saveAll(transactions);

                staticqrInfo.info("StaticQR Data saved in DB");

            }
        }catch(Exception e) {
            staticqrInfo.info("Error while save record StaticQR ----------{}",e.getMessage());
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

    @Override
    public String findByRespRecTimestaticqr() {
        return staticQrRepo.findByRespRecTime();
    }


    @Override
    public List<String> insertedcounts() {

        LocalDate curdate = LocalDate.now();
        LocalDate yesterday =curdate.minusDays(1);
        List<String> array = new ArrayList<>();
        String phonepe = billingModelRepository.yesterdayCount(String.valueOf(yesterday));
        phonepeInfo.info("phonepe count {}",yesterday +"-"+ phonepe);
        String axis    = billingAxisRepo.yesterdayCount(String.valueOf(yesterday));
        axisInfo.info("axis count {}",yesterday +"-"+ axis);
        String hitachi = billingHitachiRepo.yesterdayCount(String.valueOf(yesterday));
        hitachiInfo.info("hitachi count {}",yesterday +"-"+hitachi);
        String kvb = billingKvbRepo.yesterdayCount(String.valueOf(yesterday));
        kVBInfo.info("kvb count {}",yesterday +"-"+kvb);
        String mobikwik = billingMobikwikRepo.yesterdayCount(String.valueOf(yesterday));
        mobikwikInfo.info("mobikwik count {}",yesterday +"-"+mobikwik);
        String staticqr = staticQrRepo.yesterdayCount(String.valueOf(yesterday));
        staticqrInfo.info("staticqr count {}",yesterday +"-"+staticqr);
//        array= Arrays.asList(phonepe,axis,hitachi,kvb,mobikwik);
        array.add(phonepe);
        array.add(axis);
        array.add(hitachi);
        array.add(kvb);
        array.add(mobikwik);
        array.add(staticqr);

     return array;
    }
}
