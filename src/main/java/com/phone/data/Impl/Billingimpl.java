package com.phone.data.Impl;


import com.opencsv.CSVWriter;
import com.phone.data.Entity.*;
import com.phone.data.Repository.*;
import com.phone.data.Service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.SystemException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class Billingimpl implements BillingService {



    private final Logger logger = LoggerFactory.getLogger(Billingimpl.class);

    private final Logger phonepeInfo = LoggerFactory.getLogger("Phonepe_Info");
    private final Logger kVBInfo = LoggerFactory.getLogger("KVB_Info");
    private final Logger axisInfo = LoggerFactory.getLogger("Axis_Info");
    private final Logger hitachiInfo = LoggerFactory.getLogger("Hitachi_Info");
    private final Logger mobikwikInfo = LoggerFactory.getLogger("Mobikwik_Info");
    private final Logger staticqrInfo = LoggerFactory.getLogger("Staticqr_Info");
    private final Logger notiDataInfo = LoggerFactory.getLogger("NotiData_Info");
    private final Logger notifieldsInfo = LoggerFactory.getLogger("Notifields_Info");
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

    @Autowired
    private NotificationDataRepo notificationDataRepo;

    @Autowired
    private  NotificationFieldsRepo notificationFieldsRepo;

    @Value("${switch.datasource.url}")
    private static String url;
    @Value("${switch.datasource.username}")
    private static String username;
    @Value("${switch.datasource.password}")
    private static String password;



    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


    @Override
    public void fetchAndSaveTxnData(List<Object[]> transactionDatas, int action) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (action == 1) {
                    //phonepe
                    System.gc();
                    logger.info("Phonepe Data after fetch");
                    try {
                        saveSettlementRecords(transactionDatas);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    logger.info("Phonepe Data Completed");

                } else if (action == 2) {
                    //Axis
                    System.gc();
                    logger.info("Axis Data after fetch");
                    try {
                        saveSettlementRecordsAxis(transactionDatas);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    logger.info("Axis Data Completed");
                } else if (action == 3) {
                    //kvb
                    System.gc();
                    logger.info("KVB Data after fetch");
                    try {
                        saveSettlementRecordskvb(transactionDatas);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
                    try {
                        saveSettlementRecordsHitachi(transactionDatas);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    logger.info("Hitachi Data Completed");

                } else if (action == 6) {
                    //NotificationData
                    System.gc();
                    logger.info("NotificationData Data after fetch");
                    try {
                        saveSettlementRecordsNotiData(transactionDatas);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    logger.info("NotificationData Data Completed");
                } else if (action == 7) {
                    //NotificationFields
                    System.gc();
                    logger.info("NotificationFields Data after fetch");
                    try {
                        saveSettlementRecordsNotifields(transactionDatas);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    logger.info("NotificationFields Data Completed");
                } else if (action == 8) {
                    //staticQR
                    System.gc();
                    logger.info("staticQR Data after fetch");
                    try {
                        saveSettlementRecordsStaticqr(transactionDatas);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    logger.info("StaticQR Data Completed");
                } else {
                    logger.info("else part");
                }
            }
        }).start();

    }


    @Transactional()
    public void saveSettlementRecords(List<Object[]> objects) throws IOException {
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
                    data.setResponseReceivedTime(String.valueOf(o[count++]));
                    data.setBIN(String.valueOf(o[count++]));
                    data.setCardInfo(String.valueOf(o[count++]));
                    data.setPANEntryMode(String.valueOf(o[count++]));
                    data.setDeviceInvoiceNumber(String.valueOf(o[count]));
                    transactions.add(data);
                    count = 0;
                });
                phonepeInfo.info("phonepe Data set completed");

                billingModelRepository.saveAll(transactions);

                phonepeInfo.info("phonepe Data saved in DB");

}
    }catch(Exception e) {
            phonepeInfo.error("Error while save record phonepe----------{}",e.getMessage());

            e.printStackTrace();


            try {
                CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("./CSV_Files/Phonepe/phonepe" + new Random().nextInt() + ".csv").toString().replace("-",""), true), ',', CSVWriter.NO_QUOTE_CHARACTER);
                List<String[]> finalOut = new ArrayList<>();
                String[] out = null;
                for (Object[] obj : objects) {       //writing data to sheet
                    out = Arrays.asList(String.valueOf(obj[0]), String.valueOf(obj[1]),
                            String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]),
                            String.valueOf(obj[5]), String.valueOf(obj[6]), String.valueOf(obj[7]),
                            String.valueOf(obj[8]), String.valueOf(obj[9]), String.valueOf(obj[10]),
                            String.valueOf(obj[11]), String.valueOf(obj[12]), String.valueOf(obj[13]),
                            String.valueOf(obj[14]), String.valueOf(obj[15])).toArray(new String[0]);
                    finalOut.add(out);
                }
                csvWriter.writeAll(finalOut);
                csvWriter.close();
                phonepeInfo.info("CSV File Generated Successfully for Phonepe ----!!!");
            }catch (Exception f){
                f.printStackTrace();
                phonepeInfo.info("Error while save csv file phonepe----------{}",f.getMessage());


            }
            }
            }


    @Transactional()
    public void saveSettlementRecordsAxis(List<Object[]> objects) throws IOException {
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
            axisInfo.error("Error while save record Axis----------{}",e.getMessage());
            e.printStackTrace();
            try {
                CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("./CSV_Files/AXIS/Axis" + new Random().nextInt() + ".csv").toString().replace("-",""), true), ',', CSVWriter.NO_QUOTE_CHARACTER);
                List<String[]> finalOut = new ArrayList<>();
                String[] out = null;
                for (Object[] obj : objects) {       //writing data to sheet
                    out = Arrays.asList(String.valueOf(obj[0]), String.valueOf(obj[1]),
                            String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]),
                            String.valueOf(obj[5]), String.valueOf(obj[6]), String.valueOf(obj[7]),
                            String.valueOf(obj[8]), String.valueOf(obj[9]), String.valueOf(obj[10]),
                            String.valueOf(obj[11]), String.valueOf(obj[12]), String.valueOf(obj[13]),
                            String.valueOf(obj[14]), String.valueOf(obj[15])).toArray(new String[0]);
                    finalOut.add(out);
                }
                csvWriter.writeAll(finalOut);
                csvWriter.close();
                axisInfo.info("CSV File Generated Successfully for AXIS ----!!!");
            }catch (Exception f){
                f.printStackTrace();
                axisInfo.error("Error while saving csv file for Axis---{}",f.getMessage());
            }
        }
    }

    @Transactional()
    public void saveSettlementRecordskvb(List<Object[]> objects) throws IOException {
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
            kVBInfo.error("Error while save record kvb ----------{}",e.getMessage());
            e.printStackTrace();
            try {
                CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("./CSV_Files/KVB/kvb" + new Random().nextInt() + ".csv").toString().replace("-",""), true), ',', CSVWriter.NO_QUOTE_CHARACTER);
                List<String[]> finalOut = new ArrayList<>();
                String[] out = null;
                for (Object[] obj : objects) {       //writing data to sheet
                    out = Arrays.asList(String.valueOf(obj[0]), String.valueOf(obj[1]),
                            String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]),
                            String.valueOf(obj[5]), String.valueOf(obj[6]), String.valueOf(obj[7]),
                            String.valueOf(obj[8]), String.valueOf(obj[9]), String.valueOf(obj[10]),
                            String.valueOf(obj[11]), String.valueOf(obj[12]), String.valueOf(obj[13]),
                            String.valueOf(obj[14]), String.valueOf(obj[15])).toArray(new String[0]);
                    finalOut.add(out);
                }
                csvWriter.writeAll(finalOut);
                csvWriter.close();
                kVBInfo.info("CSV File Generated Successfully for KVB ----!!!");
            }catch (Exception f){
                f.printStackTrace();
                kVBInfo.error("Error while saving csv file for kvb ----{}",f.getMessage());
            }
        }
    }


    @Transactional()
    public void saveSettlementRecordsHitachi(List<Object[]> objects) throws IOException {
        List<HitachiTransaction> transactions = new ArrayList<>();
        hitachiInfo.info("Transaction Size Hitachi objects {}", objects.size());
        try{
//            createCsv(objects);
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


                hitachiInfo.info("Hitachi Data saved in DB");

            }
        }catch(Exception e) {
            hitachiInfo.error("Error while save record Hitachi and processing to csv ----------{}",e.getMessage());
            e.printStackTrace();
            try {
                CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("./CSV_Files/Hitachi/hitachi" + new Random().nextInt() + ".csv").toString().replace("-",""), true), ',', CSVWriter.NO_QUOTE_CHARACTER);
                List<String[]> finalOut = new ArrayList<>();
                String[] out = null;
                for (Object[] obj : objects) {       //writing data to sheet
                    out = Arrays.asList(String.valueOf(obj[0]), String.valueOf(obj[1]),
                            String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]),
                            String.valueOf(obj[5]), String.valueOf(obj[6]), String.valueOf(obj[7]),
                            String.valueOf(obj[8]), String.valueOf(obj[9]), String.valueOf(obj[10]),
                            String.valueOf(obj[11]), String.valueOf(obj[12]), String.valueOf(obj[13]),
                            String.valueOf(obj[14]), String.valueOf(obj[15])).toArray(new String[0]);
                    finalOut.add(out);
                }
                csvWriter.writeAll(finalOut);
                csvWriter.close();
                hitachiInfo.info("CSV File Generated Successfully for Hitachi ----!!!");
            }catch (Exception f){
                f.printStackTrace();
                hitachiInfo.error("Error while saving csv file for Hitachi ---{}",f.getMessage());
            }
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
            mobikwikInfo.error("Error while save record Mobikwik ----------{}",e.getMessage());
            e.printStackTrace();

            try {
                CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("./CSV_Files/Mobikwik/Mobikwik" + new Random().nextInt() + ".csv").toString().replace("-",""), true), ',', CSVWriter.NO_QUOTE_CHARACTER);
                List<String[]> finalOut = new ArrayList<>();
                String[] out = null;
                for (Object[] obj : objects) {       //writing data to sheet
                    out = Arrays.asList(String.valueOf(obj[0]), String.valueOf(obj[1]),
                            String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]),
                            String.valueOf(obj[5]), String.valueOf(obj[6]), String.valueOf(obj[7]),
                            String.valueOf(obj[8]), String.valueOf(obj[9]), String.valueOf(obj[10]),
                            String.valueOf(obj[11]), String.valueOf(obj[12]), String.valueOf(obj[13]),
                            String.valueOf(obj[14]), String.valueOf(obj[15])).toArray(new String[0]);
                    finalOut.add(out);
                }
                csvWriter.writeAll(finalOut);
                csvWriter.close();
                mobikwikInfo.info("CSV File Generated Successfully for Mobikwik ----!!!");
            }catch (Exception f){
                f.printStackTrace();
                mobikwikInfo.error("Error while saving csv file for Mobikwik ---{}",f.getMessage());
            }
        }
    }
    @Transactional()
    public void saveSettlementRecordsStaticqr(List<Object[]> objects) throws IOException {
        List<StaticQrTxn> transactions = new ArrayList<>();
        staticqrInfo.info("Transaction Size StaticQR objects {}", objects.size());
//        String s = createCsv(objects);
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    StaticQrTxn data = new StaticQrTxn();
                    data.setTxnId(String.valueOf(o[count++]));
                    data.setChecksum(String.valueOf(o[count++]));
                    data.setCreatedAt(String.valueOf(o[count++]).replace("T"," "));
                    data.setCreditVpa(String.valueOf(o[count++]));
                    data.setCustomerVpa(String.valueOf(o[count++]));
                    data.setGatewayResponseCode(String.valueOf(o[count++]));
                    data.setGatewayResponseMessage(String.valueOf(o[count++]));
                    data.setGatewayTransactionId(String.valueOf(o[count++]));
                    data.setInit_mode(String.valueOf(o[count++]));
                    data.setMerchantChannelId(String.valueOf(o[count++]));
                    data.setMerchantId(String.valueOf(o[count++]));
                    data.setMerchantTransactionId(String.valueOf(o[count++]));
                    data.setPayeractype(String.valueOf(o[count++]));
                    data.setPurpose_code(String.valueOf(o[count++]));
                    data.setRrn(String.valueOf(o[count++]));
                    data.setTransactionAmount(String.valueOf(o[count++]));
                    data.setTransactionTimestamp(String.valueOf(o[count]).replace("T"," "));
                    data.setBin(String.valueOf("bin"));
                    transactions.add(data);
                    count = 0;
                });
                staticqrInfo.info("StaticQR Data set completed");

                staticQrRepo.saveAll(transactions);

                staticqrInfo.info("StaticQR Data saved in DB");
            }
        }catch(Exception e) {
            staticqrInfo.error("Error while save record StaticQR ----------{}",e.getMessage());
            e.printStackTrace();

            try {
                CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("./CSV_Files/StaticQR/staticqr" + new Random().nextInt() + ".csv").toString().replace("-",""), true), ',', CSVWriter.NO_QUOTE_CHARACTER);
                List<String[]> finalOut = new ArrayList<>();
                String[] out = null;
                for (Object[] obj : objects) {       //writing data to sheet
                    out = Arrays.asList(String.valueOf(obj[0]), String.valueOf(obj[1]),
                            String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]),
                            String.valueOf(obj[5]), String.valueOf(obj[6]), String.valueOf(obj[7]),
                            String.valueOf(obj[8]), String.valueOf(obj[9]), String.valueOf(obj[10]),
                            String.valueOf(obj[11]), String.valueOf(obj[12]), String.valueOf(obj[13]),
                            String.valueOf(obj[14]), String.valueOf(obj[15]), String.valueOf(obj[16]),
                            String.valueOf(obj[17])).toArray(new String[0]);
                    finalOut.add(out);
                }
                csvWriter.writeAll(finalOut);
                csvWriter.close();
                staticqrInfo.info("CSV File Generated Successfully for StaticQR ----!!!");
            }catch (Exception f){
                f.printStackTrace();
                staticqrInfo.error("Error while saving csv file for StaticQR ---{}",f.getMessage());
            }

        }
    }

    @Transactional()
    public void saveSettlementRecordsNotiData(List<Object[]> objects) throws IOException {
        List<NotificationData> transactions = new ArrayList<>();
        notiDataInfo.info("Transaction Size NotificationData objects {}", objects.size());

        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    NotificationData data = new NotificationData();
                    data.setTxnCorrelationId(String.valueOf(o[count++]));
                    data.setTerminalId(String.valueOf(o[count++]));
                    data.setMerchant_id(String.valueOf(o[count++]));
                    data.setBatch_number(String.valueOf(o[count++]));
                    data.setInvoice_number(String.valueOf(o[count++]));
                    data.setResponse_code(String.valueOf(o[count++]));
                    data.setRrn(String.valueOf(o[count++]));
                    data.setTransaction_auth_code(String.valueOf(o[count++]));
                    data.setTransaction_date_time(String.valueOf(o[count++]).replace("T"," "));
                    data.setDateTime(String.valueOf(o[count++]).replace("T", " "));
                    data.setTransactionId(String.valueOf(o[count++]));
                    data.setOrgTransactionId(String.valueOf(o[count++]));
                    data.setTransactionType(String.valueOf(o[count++]));
                    data.setNotificationType(String.valueOf(o[count++]));
                    data.setStan(String.valueOf(o[count++]));
                    data.setSettlement_status(String.valueOf(o[count++]));
                    data.setNotification_fields_id(Long.valueOf(String.valueOf(o[count])));

                    transactions.add(data);
                    count = 0;
                });
                notiDataInfo.info("NotificationData Data set completed");

                notificationDataRepo.saveAll(transactions);

                notiDataInfo.info("NotificationData Data saved in DB");

            }
        }catch(Exception e) {
            notiDataInfo.error("Error while save record NotificationData ----------{}",e.getMessage());
            e.printStackTrace();

            try {
                CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("./CSV_Files/NotificationData/NotificationData" + new Random().nextInt() + ".csv").toString().replace("-",""), true), ',', CSVWriter.NO_QUOTE_CHARACTER);
                List<String[]> finalOut = new ArrayList<>();
                String[] out = null;
                for (Object[] obj : objects) {       //writing data to sheet
                    out = Arrays.asList(String.valueOf(obj[0]), String.valueOf(obj[1]),
                            String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]),
                            String.valueOf(obj[5]), String.valueOf(obj[6]), String.valueOf(obj[7]),
                            String.valueOf(obj[8]), String.valueOf(obj[9]), String.valueOf(obj[10]),
                            String.valueOf(obj[11]), String.valueOf(obj[12]), String.valueOf(obj[13]),
                            String.valueOf(obj[14]), String.valueOf(obj[15]),String.valueOf(obj[16])).toArray(new String[0]);
                    finalOut.add(out);
                }
                csvWriter.writeAll(finalOut);
                csvWriter.close();
                notiDataInfo.info("CSV File Generated Successfully for NotificationData ----!!!");
            }catch (Exception f){
                f.printStackTrace();
                notiDataInfo.error("Error while saving csv file for Notificationdata ---{}",f.getMessage());
            }

        }
    }
    @Transactional()
    public void saveSettlementRecordsNotifields(List<Object[]> objects) throws IOException {
        List<NotificationFields> transactions = new ArrayList<>();
        notifieldsInfo.info("Transaction Size NotificationFields objects {}", objects.size());
//        String s = createCsv(objects);
        try{
            if (objects.size() > 0) {
                objects.forEach(o -> {
                    int count = 0;
                    NotificationFields data = new NotificationFields();
                    data.setId(Long.valueOf(String.valueOf(o[count++])));
                    data.setPos_Device_Id(String.valueOf(o[count++]));
                    data.setCard_holder_name(String.valueOf(o[count++]));
                    data.setMasked_card_number(String.valueOf(o[count++]));
                    data.setTransaction_mode(String.valueOf(o[count++]));
                    data.setAcquirer_bank(String.valueOf(o[count++]));
                    data.setCard_type(String.valueOf(o[count++]));
                    data.setCard_network(String.valueOf(o[count++]));
                    data.setCard_issuer_country_code(String.valueOf(o[count++]));
                    data.setAmount(String.valueOf(o[count++]));
                    data.setInvoice_number(String.valueOf(o[count++]));
                    data.setBatch_number(String.valueOf(o[count++]));
                    data.setTerminalId(String.valueOf(o[count]));
                    transactions.add(data);
                    count = 0;
                });
                notifieldsInfo.info("NotificationFields Data set completed");

                notificationFieldsRepo.saveAll(transactions);

                notifieldsInfo.info("NotificationFields Data saved in DB");

            }
        }catch(Exception e) {
            notifieldsInfo.error("Error while save record NotificationFields ----------{}",e.getMessage());
            e.printStackTrace();
            try {
                CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("./CSV_Files/NotificationFields/NotificationFields" + new Random().nextInt() + ".csv").toString().replace("-",""), true), ',', CSVWriter.NO_QUOTE_CHARACTER);
                List<String[]> finalOut = new ArrayList<>();
                String[] out = null;
                for (Object[] obj : objects) {       //writing data to sheet
                    out = Arrays.asList(String.valueOf(obj[0]), String.valueOf(obj[1]),
                            String.valueOf(obj[2]), String.valueOf(obj[3]), String.valueOf(obj[4]),
                            String.valueOf(obj[5]), String.valueOf(obj[6]), String.valueOf(obj[7]),
                            String.valueOf(obj[8]), String.valueOf(obj[9]), String.valueOf(obj[10]),
                            String.valueOf(obj[11]), String.valueOf(obj[12])).toArray(new String[0]);
                    finalOut.add(out);
                }
                csvWriter.writeAll(finalOut);
                csvWriter.close();
                notifieldsInfo.info("CSV File Generated Successfully for NotificationFields ----!!!");
            }catch (Exception f){
                f.printStackTrace();
                notifieldsInfo.error("Error while saving csv file for NotificationFields ---{}",f.getMessage());
            }


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
    public String findByDateTimeNotificationData() {
        return notificationDataRepo.findbyDateTime();
    }

    @Override
    public Long findByNotificationFields() {
        return notificationFieldsRepo.findbyfieldId();
    }


    @Override
    public List<String> insertedcounts() {

        LocalDate curdate = LocalDate.now();
        LocalDate yesterday =curdate.minusDays(1);
//        String yesterday = "2022";
        List<String> array = new ArrayList<>();
//        String query1 = "select count(*) from phonepe_transaction where  RequestRouteTime like '"+yesterday+"%'";
//        phonepeInfo.info(query1);
//        logger.info(String.valueOf(yesterday));
//        System.out.println(yesterday);
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
        String notidata = notificationDataRepo.yesterdayCount(String.valueOf(yesterday));
        notiDataInfo.info("notification data count {}",yesterday +"-"+notidata);
//        array= Arrays.asList(phonepe,axis,hitachi,kvb,mobikwik);
        array.add(phonepe);
        array.add(axis);
        array.add(hitachi);
        array.add(kvb);
        array.add(mobikwik);
        array.add(staticqr);
        array.add(notidata);

     return array;
    }


    public String createCsv(List<Object[]> objects) throws IOException {


        CSVWriter csvWriter = new CSVWriter(new FileWriter("/home/rameshkumar/Documents/scvgen/file.csv",true),',',CSVWriter.NO_QUOTE_CHARACTER);
//    csvWriter.writeAll((List<String[]>) objects.stream().map(object -> Objects.toString(object, null)).collect(Collectors.toList()));
     objects.stream().forEach(t -> Arrays.stream(t)
              .collect(Collectors.toList())
              .forEach(s -> System.out.println(Arrays.stream(new String[]{(String) s}).toArray())));


       return "csv";
    }

    public static List createList(ResultSet resultSet) throws SQLException, SQLException {

        ResultSetMetaData metadata = resultSet.getMetaData();
        int numberOfColumns = metadata.getColumnCount();

        List<String> list = new ArrayList<>(numberOfColumns);
        while (resultSet.next()) {
            int i = 1;
            while (i <= numberOfColumns) {
                list.add(resultSet.getString(i++));
            }
        }
        return list;
    }


    public static ResultSet getIntervenantById() throws SQLException {


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        String date = simpleDateFormat.format(calendar.getTime());
        System.out.println(date);

        ResultSet resultset = null;
        try {
            Statement statement = getConnection().createStatement();
            String query = "Select distinct MTI from switch_request where RequestRouteTime like '" + date + "%' ;";
//            String query = "Select MTI,count(*) from switch_request  group by MTI;";
            resultset = statement.executeQuery(query);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultset;
    }
}
