package com.phone.data.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

@Table(name ="notification_data")
public class NotificationData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long rowid;

    @Column(name = "TxnCorrelationId")    private String txnCorrelationId;
    @Column (name = "TerminalId")    private String terminalId="N/A";
    @Column (name = "merchant_id")    private String merchant_id="N/A";
    @Column (name = "batch_number")    private String batch_number="N/A";
    @Column (name = "invoice_number")    private String invoice_number="N/A";
    @Column (name = "response_code")    private String response_code="N/A";
    @Column (name = "rrn")    private String rrn="N/A";
    @Column (name = "transaction_auth_code")    private String transaction_auth_code="N/A";
    @Column (name = "transaction_date_time")    private String transaction_date_time="N/A";
    @Column (name = "DateTime")    private String DateTime="N/A";
    @Column (name = "TransactionId")    private String TransactionId="N/A";
    @Column (name = "OrgTransactionId")    private String OrgTransactionId="N/A";
    @Column (name = "TransactionType")    private String TransactionType="N/A";
    @Column (name = "NotificationType")    private String notificationType="N/A";
    @Column (name = "Stan")    private String stan="N/A";
    @Column (name = "settlement_status")    private String settlement_status="N/A";
    @Column (name = "notification_fields_id")    private Long notification_fields_id;


    public long getRowid() {
        return rowid;
    }

    public void setRowid(long rowid) {
        this.rowid = rowid;
    }

    public String getTxnCorrelationId() {
        return txnCorrelationId;
    }

    public void setTxnCorrelationId(String txnCorrelationId) {
        this.txnCorrelationId = txnCorrelationId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getTransaction_auth_code() {
        return transaction_auth_code;
    }

    public void setTransaction_auth_code(String transaction_auth_code) {
        this.transaction_auth_code = transaction_auth_code;
    }

    public String getTransaction_date_time() {
        return transaction_date_time;
    }

    public void setTransaction_date_time(String transaction_date_time) {
        this.transaction_date_time = transaction_date_time;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public String getOrgTransactionId() {
        return OrgTransactionId;
    }

    public void setOrgTransactionId(String orgTransactionId) {
        OrgTransactionId = orgTransactionId;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public String getSettlement_status() {
        return settlement_status;
    }

    public void setSettlement_status(String settlement_status) {
        this.settlement_status = settlement_status;
    }

    public Long getNotification_fields_id() {
        return notification_fields_id;
    }

    public void setNotification_fields_id(Long notification_fields_id) {
        this.notification_fields_id = notification_fields_id;
    }

    @Override
    public String toString() {
        return "NotificationData{" +
                "rowid=" + rowid +
                ", txnCorrelationId='" + txnCorrelationId + '\'' +
                ", terminalId='" + terminalId + '\'' +
                ", merchant_id='" + merchant_id + '\'' +
                ", batch_number='" + batch_number + '\'' +
                ", invoice_number='" + invoice_number + '\'' +
                ", response_code='" + response_code + '\'' +
                ", rrn='" + rrn + '\'' +
                ", transaction_auth_code='" + transaction_auth_code + '\'' +
                ", transaction_date_time='" + transaction_date_time + '\'' +
                ", DateTime='" + DateTime + '\'' +
                ", TransactionId='" + TransactionId + '\'' +
                ", OrgTransactionId='" + OrgTransactionId + '\'' +
                ", TransactionType='" + TransactionType + '\'' +
                ", notificationType='" + notificationType + '\'' +
                ", stan='" + stan + '\'' +
                ", settlement_status='" + settlement_status + '\'' +
                ", notification_fields_id='" + notification_fields_id + '\'' +
                '}';
    }
}
