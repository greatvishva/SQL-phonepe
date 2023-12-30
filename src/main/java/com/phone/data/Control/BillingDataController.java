package com.phone.data.Control;

import com.phone.data.Entity.PhonepeTransaction;

import com.phone.data.Impl.Mailhandler;
import com.phone.data.Service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


@RequestMapping("/api")
@RestController
public class BillingDataController {


    private static Logger logger = LoggerFactory.getLogger(BillingDataController.class);

    private final Logger phonepeInfo = LoggerFactory.getLogger("Phonepe_Info");
    private final Logger kVBInfo = LoggerFactory.getLogger("KVB_Info");
    private final Logger axisInfo = LoggerFactory.getLogger("Axis_Info");
    private final Logger hitachiInfo = LoggerFactory.getLogger("Hitachi_Info");
    private final Logger mobikwikInfo = LoggerFactory.getLogger("Mobikwik_Info");

    //    @Autowired
//     CacheManager cacheManager;
    @Autowired
    private BillingService billingDataService;
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private Mailhandler mailhandler;
    @Value("${switch.datasource.url}")
    private String url;
    @Value("${switch.datasource.username}")
    private String username;
    @Value("${switch.datasource.password}")
    private String password;

    @Value("${bpdata.datasource.url}")
    private String url1;
    @Value("${bpdata.datasource.username}")
    private String username1;
    @Value("${bpdata.datasource.password}")
    private String password1;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }





    //This function commented temporary
    // This function now running --fetchdataAxis()

    /* Data fetch from switch transactions and insert to Earth server only for WL002 Institutions */
//    @Transactional()
//    @Scheduled(cron = "0 0/1 * * * *")
//    @GetMapping("/phonepeonly")
//    public void fetchdata()throws Exception{
//        Connection con = null;
//        Statement stmt = null;
//        BillingEntity data = new BillingEntity();
//
//        String initTime = billingDataService.findByRespRecTime();
//
//
//        try {
//            logger.info("SWITCH-TXN : Getting the connection to BPSwitchOn DB to fetch data: ");
//            con = getConnection();
//            stmt = con.createStatement();
//            String query = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
//                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='WL002' and req.RequestRouteTime  between '" + initTime +
//                    "' AND DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL 2 MINUTE) ORDER BY req.RequestRouteTime limit 200";
//            logger.info("Output query : {}", query);
//
//            List<Object[]> transactionDatas = new ArrayList<>();
//            ResultSet resultSet = stmt.executeQuery(query);
//            while (resultSet.next()) {
//                int cols = resultSet.getMetaData().getColumnCount();
//                Object[] arr = new Object[cols];
//                for (int i = 0; i < cols; i++) {
//                    arr[i] = resultSet.getObject(i + 1);
//                }
//                transactionDatas.add(arr);
//
//            }
//            logger.info("SWITCH-TXN : transactions size : {}", transactionDatas.size());
//            resultSet.close();
//            stmt.close();
//            con.close();
//            billingDataService.fetchAndSaveTxnData(transactionDatas,1);
//
//        } catch (Exception e) {
//            logger.info("Error on controller page");
//            e.printStackTrace();
//        }
//
//    }


    /* Data fetch from switch transactions and insert to Earth server only for All Institutions */
    @Transactional()
    @Scheduled(cron = "0 0/1 * * * *")
    @GetMapping("/billing")
    public void fetchdataAxis()throws Exception{
        Connection con = null;
        Statement stmt = null;



        String phonepe = billingDataService.findByRespRecTime();
        String axis = billingDataService.findByRespRecTimeAxis();
        String kvb = billingDataService.findByRespRecTimeKvb();
        String hitachi = billingDataService.findByRespRecTimeHitachi();
        String mobikwik = billingDataService.findByRespRecTimeMobikwik();


        try {
            logger.info("SWITCH-TXN : Getting the connection to BPSwitchOn DB to fetch data: ");
            con = getConnection();
            stmt = con.createStatement();


//            String query = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
//                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='WL002' and req.RequestRouteTime  between '" + phonepe +
//                    "' AND DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL 2 MINUTE) ORDER BY req.RequestRouteTime limit 500";
            String query = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='WL002' and req.RequestRouteTime  > '" + phonepe +
                    "'  ORDER BY req.RequestRouteTime limit 500";
            logger.info("Output query : {}", query);

            List<Object[]> transactionDatasphonepe = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int cols = resultSet.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    arr[i] = resultSet.getObject(i + 1);
                }
                transactionDatasphonepe.add(arr);

            }
            logger.info("SWITCH-TXN : transactions size Phonepe : {}", transactionDatasphonepe.size());
            resultSet.close();

            stmt.close();

            //Axis
          Statement  stmt1 = con.createStatement();


//            String queryaxis = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
//                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='WLBP1' and req.RequestRouteTime  between '" + axis +
//                    "' AND DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL 2 MINUTE) ORDER BY req.RequestRouteTime limit 200";
            String queryaxis = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='WLBP1' and req.RequestRouteTime > '" + axis +
                    "' ORDER BY req.RequestRouteTime limit 200";
            logger.info("Output query Axis : {}", queryaxis);

            List<Object[]> transactionDatasaxis = new ArrayList<>();
            ResultSet resultSet1 = stmt1.executeQuery(queryaxis);
            while (resultSet1.next()) {
                int cols = resultSet1.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    arr[i] = resultSet1.getObject(i + 1);
                }
                transactionDatasaxis.add(arr);

            }
            logger.info("SWITCH-TXN : transactions size Axis : {}", transactionDatasaxis.size());
            resultSet1.close();
            stmt1.close();


            //kvb
            Statement  stmt2 = con.createStatement();


//            String querykvb = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
//                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='WL001' and req.RequestRouteTime  between '" + kvb +
//                    "' AND DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL 2 MINUTE) ORDER BY req.RequestRouteTime limit 200";
            String querykvb = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='WL001' and req.RequestRouteTime > '" + kvb +
                    "'  ORDER BY req.RequestRouteTime limit 200";
            logger.info("Output query KVB : {}", querykvb);

            List<Object[]> transactionDataskvb = new ArrayList<>();
            ResultSet resultSet2 = stmt2.executeQuery(querykvb);
            while (resultSet2.next()) {
                int cols = resultSet2.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    arr[i] = resultSet2.getObject(i + 1);
                }
                transactionDataskvb.add(arr);

            }
            logger.info("SWITCH-TXN : transactions size Kvb : {}", transactionDataskvb.size());
            resultSet2.close();
            stmt2.close();


            //hitachi

            Statement  stmt3 = con.createStatement();


//            String queryhitachi = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
//                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='00' and req.RequestRouteTime  between '" + hitachi +
//                    "' AND DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL 2 MINUTE) ORDER BY req.RequestRouteTime limit 500";
            String queryhitachi = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='00' and req.RequestRouteTime  > '" + hitachi +
                    "'  ORDER BY req.RequestRouteTime limit 500";
            logger.info("Output query Hitachi : {}", queryhitachi);

            List<Object[]> transactionDatashitachi = new ArrayList<>();
            ResultSet resultSet3 = stmt3.executeQuery(queryhitachi);
            while (resultSet3.next()) {
                int cols = resultSet3.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    arr[i] = resultSet3.getObject(i + 1);
                }
                transactionDatashitachi.add(arr);

            }
            logger.info("SWITCH-TXN : transactions size Hitachi : {}", transactionDatashitachi.size());
            resultSet3.close();
            stmt3.close();



            //Mobikwik
            Statement  stmt4 = con.createStatement();


//            String querymobikwik = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
//                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='WL003' and req.RequestRouteTime  between '" + mobikwik +
//                    "' AND DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL 2 MINUTE) ORDER BY req.RequestRouteTime limit 200";
            String querymobikwik = "SELECT req.TxnCorrelationId,req.TerminalId, req.MerchantId, req.MTI, req.TxnType, res.Stan, res.AuthResponseCode,res.TxnResponseCode, req.TxnAmount, req.TxnAdditionalAmount,req.BatchNumber,req.InvoiceNumber, res.RRNumber, req.InstitutionId, req.RequestRouteTime, res.ResponseReceivedTime FROM switch_request req left join switch_response res" +
                    " ON req.TxnCorrelationId=res.TxnCorrelationId where req.InstitutionId ='WL003' and req.RequestRouteTime  > '" + mobikwik +
                    "' ORDER BY req.RequestRouteTime limit 200";
            logger.info("Output query Mobikwik : {}", querymobikwik);

            List<Object[]> transactionDatasmobikwik = new ArrayList<>();
            ResultSet resultSet4 = stmt4.executeQuery(querymobikwik);
            while (resultSet4.next()) {
                int cols = resultSet4.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    arr[i] = resultSet4.getObject(i + 1);
                }
                transactionDatasmobikwik.add(arr);

            }
            logger.info("SWITCH-TXN : transactions size Mobikwik : {}", transactionDatasmobikwik.size());
            resultSet4.close();
            stmt4.close();

            con.close();
            logger.info("Connection closed Status  {}",con.isClosed());
            billingDataService.fetchAndSaveTxnData(transactionDatasphonepe,1);
            billingDataService.fetchAndSaveTxnData(transactionDatashitachi,5);
            billingDataService.fetchAndSaveTxnData(transactionDatasaxis,2);
            billingDataService.fetchAndSaveTxnData(transactionDataskvb,3);
            billingDataService.fetchAndSaveTxnData(transactionDatasmobikwik,4);


        } catch (Exception e) {
            logger.info("Error on controller page");
            e.printStackTrace();
        }finally {
            con.close();
            staticqrTxn();
        }

    }

    public void staticqrTxn() throws SQLException {

        try {
            String init = billingDataService.findByRespRecTimestaticqr();
            Connection con1 = DriverManager.getConnection(url1, username1, password1);
            Statement statement = con1.createStatement();
            List<Object[]> transactionDatastaticqr = new ArrayList<>();
            String query = "select * from static_qr_txn where created_at > '" + init + "' order by created_at desc limit 100 ";
           logger.info("StaticQR txn Quert {}",query);
            ResultSet resultSet5 = statement.executeQuery(query);
            while (resultSet5.next()) {
                int cols = resultSet5.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    arr[i] = resultSet5.getObject(i + 1);
                }
                transactionDatastaticqr.add(arr);

            }
            logger.info("SWITCH-TXN : transactions size StaticQR : {}", transactionDatastaticqr.size());
            resultSet5.close();
            statement.close();

            con1.close();
            billingDataService.fetchAndSaveTxnData(transactionDatastaticqr, 6);
        }catch (Exception e){
            logger.info("Error on static qr controller page");
            e.printStackTrace();
        }
    }
    @Scheduled(cron = "0 45 2 * * *")
    @GetMapping("/count")
    public void insertedCount() throws Exception{
        final String TO = "rameshkumarm@bijlipay.co.in";
        LocalDate curdate = LocalDate.now();
        LocalDate yesterday =curdate.minusDays(1);
        try {
            List<String> countArray = billingDataService.insertedcounts();
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

            message.setFrom("docs@bijlipay.co.in");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO));

            String msgBody = "Dear All," + "\n\n" + "Greetings from Bijlipay!!" + "\n\n" + "Here mentioned  " + " yesterday data inserted count institution wise, please inform if count 0 for any institution. " + "\n\n" + "Regards," + "\n" + "Team " + "Bijlipay\n\n Note :" + "This is an auto generated email. Please do not respond to this email id...\n\n " +
                   "<html> <head> <title> Institution wise count details </title> </head>"+
                    "<body> <table > " +
                    "<tr> <td> " + "phonepe -"+ "</td>"+
                    "<td> "+countArray.get(0)+ "</td> </tr></b>"+
                    "<tr> <td> " + "Axis -"+ "</td>"+
                    "<td> "+countArray.get(1)+ "</td> </tr></b>"+
                    "<tr> <td> " + "Hitachi -"+ "</td>"+
                    "<td> "+countArray.get(2)+ "</td> </tr></b>"+
                    "<tr> <td> " + "KVB -"+ "</td>"+
                    "<td> "+countArray.get(3)+ "</td> </tr></b>"+
                    "<tr> <td> " + "Mobikwik -"+ "</td>"+
                    "<td> "+countArray.get(4)+ "</td> </tr></b>"+
                    "<tr> <td> " + "StaticQR -"+ "</td>"+
                    "<td> "+countArray.get(5)+ "</td> </tr></b>"+
                    "</table> </body> <html>";

//                    "Phonepe  - " + countArray.get(0)+ "\n\n"+
//                    "Axis     - " + countArray.get(1)+ "\n\n"+
//                    "Hitachi  - " + countArray.get(2)+ "\n\n"+
//                    "KVB      - " + countArray.get(3)+ "\n\n"+
//                    "Mobikwik - " + countArray.get(4)+ "\n\n";


            String subject = "Phonepe_billing data count for the date of _";
            message.setSubject(subject+yesterday);
            message.setContent(msgBody, "text/html");
//            mailhandler.commonSendMail(msgBody, subject);
            sender.send(message);
            logger.info("mail sent");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Scheduled(cron = "0 */5 * * * *")
    @GetMapping("/reversalcount")
    public void reversalCount() throws Exception{
        final String TO = "rameshkumarm@bijlipay.co.in";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        String date = simpleDateFormat.format(calendar.getTime());
        System.out.println(date);


        try {
            Statement statement =getConnection().createStatement();
            String query = "Select MTI,count(*) from switch_request where RequestRouteTime like '"+date+"%' group by MTI;";
//            String query = "Select MTI,count(*) from switch_request  group by MTI;";
            logger.info(query);
            ResultSet resultset = statement.executeQuery(query);
            List<Object[]> transactionDatasmobikwik = new ArrayList<>();
            List<String> countArray = new ArrayList<>();
            while (resultset.next()) {
                int cols = resultset.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    arr[i] = resultset.getObject(i+1 );
//                    logger.info(arr.toString());
//                    for (Object fi:arr){

//                    }
                }
                for(int i1=0;i1 <arr.length;i1++) {


                    if (arr.length % 2 == 0) {
                        logger.info(String.valueOf(arr[i1]));
                        countArray.add(String.valueOf(arr[i1]));
                    }
                }
                transactionDatasmobikwik.add(arr);
//                logger.info(transactionDatasmobikwik.toString());
//                logger.info(arr.toString());

            }

        MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

            message.setFrom("docs@bijlipay.co.in");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO));


            String msgBody = "Dear All," + "\n\n" + "Greetings from Bijlipay!!" + "\n\n" + "Here mentioned  " + " MTI wise transaction count, please inform if count more than 1000 for reversal. " + "\n\n" + "Regards," + "\n" + "Team " + "Bijlipay\n\n Note :" + "This is an auto generated email. Please do not respond to this email id...\n\n " +
                    "<html> <head> <title> MTI wise count details </title> </head>"+
                    "<body> <table > " +
                    "<tr> <td> " + "MTI  "+ "</td>"+
                    "<td> "+"Count"+"</td> </tr></b>"+
                    "<tr> <td> " + countArray.get(0)+ "</td>"+
                    "<td> "+countArray.get(1)+ "</td> </tr></b>"+
                    "<tr> <td> " + countArray.get(2)+ "</td>"+
                    "<td> "+countArray.get(3)+ "</td> </tr></b>"+
                    "<tr> <td> " + countArray.get(4)+ "</td>"+
                    "<td> "+countArray.get(5)+ "</td> </tr></b>"+
                    "<tr> <td> " + countArray.get(6)+ "</td>"+
                    "<td> "+countArray.get(7)+ "</td> </tr></b>"+
                    "<tr> <td> " + countArray.get(8)+ "</td>"+
                    "<td> "+countArray.get(9)+ "</td> </tr></b>"+
                    "<tr> <td> " + countArray.get(10)+ "</td>"+
                    "<td> "+countArray.get(11)+ "</td> </tr></b>"+
                    "<tr> <td> " + countArray.get(12)+ "</td>"+
                    "<td> "+countArray.get(13)+ "</td> </tr></b>"+
                    "</table> </body> <html>";



            String subject = "Transactions count for the Time of _";
            message.setSubject(subject+date);
            message.setContent(msgBody, "text/html");
//            mailhandler.commonSendMail(msgBody, subject);
//            if(Integer.parseInt(countArray.get(8)) >= 1000) {
//                sender.send(message);
//                logger.info("mail sent");
//            }
            sender.send(message);
                logger.info("mail sent");
//            logger.info("reversal count less than 1000");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap resultSetToHashMap(ResultSet rs) throws SQLException {

        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        HashMap row = new HashMap();
        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                row.put(md.getColumnName(i), rs.getObject(i));
                logger.info((String) row.get(i+1));
                logger.info(md.getColumnName(i).toString());
                logger.info((String) rs.getObject(i));
            }
        }
        return row;
    }
}
