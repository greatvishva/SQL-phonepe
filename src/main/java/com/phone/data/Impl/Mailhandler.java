package com.phone.data.Impl;

import java.time.LocalDate;
import java.util.Properties;
import com.jcraft.jsch.*;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hibernate.pretty.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import jcifs.smb.NtlmPasswordAuthentication;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class Mailhandler {




    public static final String USERNAME = "misreports@bijlipay.co.in";
    public static final String PASSWORD = "Avengers!123";
//    public static final String TO = "switchtxnreport@bijlipay.co.in";
    public static final String TO = "rameshkumarm@bijlipay.co.in";
    public void commonSendMail(String msgBody, String subject) {
        LocalDate curdate = LocalDate.now();
        LocalDate yesterday =curdate.minusDays(1);
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        try {
            Message message = new MimeMessage(session);
                      message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(TO));
//            message.setRecipients(Message.RecipientType.CC,
//                    InternetAddress.parse(TO));
            message.setSubject(subject + yesterday);
            message.setText(String.format(msgBody));
            message.setContent(message,"html/text");
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
