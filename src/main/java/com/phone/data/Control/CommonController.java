//package com.phone.data.Entity;
//
//import com.phone.data.Impl.Billingimpl;
//import com.phone.data.Impl.Mailhandler;
//import com.phone.data.Repository.BillingRepo;
//import com.phone.data.Service.BillingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Component
//public class CommonController {
//    @Autowired
//    private BillingService billingService;
//
//    @Autowired
//    private Mailhandler mailhandler;
//    @Autowired
//    private Billingimpl billingimpl;
//
//    @Scheduled(cron = "0 45 2 * * *")
//    @GetMapping("/count")
//    public void insertedCount() throws Exception{
//
//         try {
//            List<String> countArray = billingimpl.insertedcounts();
//
//                     String msgBody = "Dear All," + "\n\n" + "Greetings from Bijlipay!!" + "\n\n" + "Here mentioned  " + " yesterday data inserted count institution wise, please inform if count 0 for any institution. " + "\n\n" + "Regards," + "\n" + "Team " + "Bijlipay\n\n Note :" + "This is an auto generated email. Please do not respond to this email id...\n\n " +
//                             "Phonepe  - " + countArray.get(0)+ "\n\n"+
//                             "Axis     - " + countArray.get(1)+ "\n\n"+
//                             "Hitachi  - " + countArray.get(2)+ "\n\n"+
//                             "KVB      - " + countArray.get(3)+ "\n\n"+
//                             "Mobikwik - " + countArray.get(4)+ "\n\n";
//
//
//                String subject = "Phonepe_billing data count for the date of _";
//
//                mailhandler.commonSendMail(msgBody, subject);
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
