package com.phone.data.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

//@Table(name ="Billing_Data")
@Table(name ="phonepe_transaction")
public class PhonepeTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long rowid;
    @Column(name = "TxnCorrelationId")
    private String id;

    @Column (name = "TerminalId")
    private String TerminalId="N/A";
    @Column (name = "MerchantId")
    private String MerchantId="N/A";
    @Column (name = "MTI")
    private String MTI="N/A";

    @Column (name = "TxnType")
    private String TxnType="N/A";
    @Column (name = "Stan")
    private String Stan="N/A";

    @Column (name = "AuthResponseCode")
    private String AuthResponseCode="N/A";

    @Column (name = "TxnResponseCode")
    private String TxnResponseCode="N/A";

    @Column (name = "TxnAmount")
    private String TxnAmount="N/A";
    @Column (name = "TxnAdditionalAmount")
    private String TxnAdditionalAmount="N/A";
    @Column (name = "BatchNumber")
    private String BatchNumber="N/A";
    @Column (name = "InvoiceNumber")
    private String InvoiceNumber="N/A";
    @Column (name = "RRNumber")
    private String RRNumber="N/A";
    @Column (name = "InstitutionId")
    private String InstitutionId="N/A";
    @Column (name = "RequestRouteTime")
    private String RequestRouteTime="N/A";
    @Column (name = "ResponseReceivedTime")    private String ResponseReceivedTime="N/A";


    @Column (name = "BIN")    private String BIN="N/A";
    @Column (name = "CardInfo")    private String CardInfo="N/A";
    @Column (name = "PANEntryMode")    private String PANEntryMode="N/A";
    @Column (name = "DeviceInvoiceNumber")    private String DeviceInvoiceNumber="N/A";


    @UpdateTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedate;

    public long getRowid() {
        return rowid;
    }

    public void setRowid(long rowid) {
        this.rowid = rowid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerminalId() {
        return TerminalId;
    }

    public void setTerminalId(String terminalId) {
        TerminalId = terminalId;
    }

    public String getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(String merchantId) {
        MerchantId = merchantId;
    }

    public String getMTI() {
        return MTI;
    }

    public void setMTI(String MTI) {
        this.MTI = MTI;
    }

    public String getTxnType() {
        return TxnType;
    }

    public void setTxnType(String txnType) {
        TxnType = txnType;
    }

    public String getStan() {
        return Stan;
    }

    public void setStan(String stan) {
        Stan = stan;
    }

    public String getAuthResponseCode() {
        return AuthResponseCode;
    }

    public void setAuthResponseCode(String authResponseCode) {
        AuthResponseCode = authResponseCode;
    }

    public String getTxnResponseCode() {
        return TxnResponseCode;
    }

    public void setTxnResponseCode(String txnResponseCode) {
        TxnResponseCode = txnResponseCode;
    }

    public String getTxnAmount() {
        return TxnAmount;
    }

    public void setTxnAmount(String txnAmount) {
        TxnAmount = txnAmount;
    }

    public String getTxnAdditionalAmount() {
        return TxnAdditionalAmount;
    }

    public void setTxnAdditionalAmount(String txnAdditionalAmount) {
        TxnAdditionalAmount = txnAdditionalAmount;
    }

    public String getBatchNumber() {
        return BatchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        BatchNumber = batchNumber;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        InvoiceNumber = invoiceNumber;
    }

    public String getRRNumber() {
        return RRNumber;
    }

    public void setRRNumber(String RRNumber) {
        this.RRNumber = RRNumber;
    }

    public String getInstitutionId() {
        return InstitutionId;
    }

    public void setInstitutionId(String institutionId) {
        InstitutionId = institutionId;
    }

    public String getRequestRouteTime() {
        return RequestRouteTime;
    }

    public void setRequestRouteTime(String requestRouteTime) {
        RequestRouteTime = requestRouteTime;
    }

    public String getResponseReceivedTime() {
        return ResponseReceivedTime;
    }

    public void setResponseReceivedTime(String responseReceivedTime) {
        ResponseReceivedTime = responseReceivedTime;
    }

    public String getBIN() {
        return BIN;
    }

    public void setBIN(String BIN) {
        this.BIN = BIN;
    }

    public String getCardInfo() {
        return CardInfo;
    }

    public void setCardInfo(String cardInfo) {
        CardInfo = cardInfo;
    }

    public String getPANEntryMode() {
        return PANEntryMode;
    }

    public void setPANEntryMode(String PANEntryMode) {
        this.PANEntryMode = PANEntryMode;
    }

    public String getDeviceInvoiceNumber() {
        return DeviceInvoiceNumber;
    }

    public void setDeviceInvoiceNumber(String deviceInvoiceNumber) {
        DeviceInvoiceNumber = deviceInvoiceNumber;
    }

    @Override
    public String toString() {
        return "PhonepeTransaction{" +
                "rowid=" + rowid +
                ", id='" + id + '\'' +
                ", TerminalId='" + TerminalId + '\'' +
                ", MerchantId='" + MerchantId + '\'' +
                ", MTI='" + MTI + '\'' +
                ", TxnType='" + TxnType + '\'' +
                ", Stan='" + Stan + '\'' +
                ", AuthResponseCode='" + AuthResponseCode + '\'' +
                ", TxnResponseCode='" + TxnResponseCode + '\'' +
                ", TxnAmount='" + TxnAmount + '\'' +
                ", TxnAdditionalAmount='" + TxnAdditionalAmount + '\'' +
                ", BatchNumber='" + BatchNumber + '\'' +
                ", InvoiceNumber='" + InvoiceNumber + '\'' +
                ", RRNumber='" + RRNumber + '\'' +
                ", InstitutionId='" + InstitutionId + '\'' +
                ", RequestRouteTime='" + RequestRouteTime + '\'' +
                ", ResponseReceivedTime='" + ResponseReceivedTime + '\'' +
                ", BIN='" + BIN + '\'' +
                ", CardInfo='" + CardInfo + '\'' +
                ", PANEntryMode='" + PANEntryMode + '\'' +
                ", DeviceInvoiceNumber='" + DeviceInvoiceNumber + '\'' +
                ", updatedate=" + updatedate +
                '}';
    }
}
