package com.phone.data.Entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "static_qr_txn")
public class StaticQrTxn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rowid")
    private Long rowid;

    @Column(name = "txn_id")    private String txnId;
    @Column(name = "checksum")    private String checksum;
    @Column(name = "created_at")    private String createdAt;
    @Column(name = "credit_vpa")    private String creditVpa;
    @Column(name = "customer_vpa")    private String customerVpa;
    @Column(name = "gateway_response_code")    private String gatewayResponseCode;
    @Column(name = "gateway_response_message")    private String gatewayResponseMessage;
    @Column(name = "gateway_transaction_id")    private String gatewayTransactionId;
    @Column(name = "merchant_channel_id")    private String merchantChannelId;
    @Column(name = "merchant_id")    private String merchantId;
    @Column(name = "merchant_transaction_id")    private String merchantTransactionId;
    @Column(name = "rrn")    private String rrn;
    @Column(name = "transaction_amount")    private String transactionAmount;
    @Column(name = "transaction_timestamp")    private String transactionTimestamp;
    @Column(name = "payeractype")    private String payeractype;
    @Column(name = "init_mode")    private String init_mode;
    @Column(name = "purpose_code")    private String purpose_code;
    @Column(name = "bin")    private String bin;

    @UpdateTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp insertedat;

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreditVpa() {
        return creditVpa;
    }

    public void setCreditVpa(String creditVpa) {
        this.creditVpa = creditVpa;
    }

    public String getCustomerVpa() {
        return customerVpa;
    }

    public void setCustomerVpa(String customerVpa) {
        this.customerVpa = customerVpa;
    }

    public String getGatewayResponseCode() {
        return gatewayResponseCode;
    }

    public void setGatewayResponseCode(String gatewayResponseCode) {
        this.gatewayResponseCode = gatewayResponseCode;
    }

    public String getGatewayResponseMessage() {
        return gatewayResponseMessage;
    }

    public void setGatewayResponseMessage(String gatewayResponseMessage) {
        this.gatewayResponseMessage = gatewayResponseMessage;
    }

    public String getGatewayTransactionId() {
        return gatewayTransactionId;
    }

    public void setGatewayTransactionId(String gatewayTransactionId) {
        this.gatewayTransactionId = gatewayTransactionId;
    }

    public String getMerchantChannelId() {
        return merchantChannelId;
    }

    public void setMerchantChannelId(String merchantChannelId) {
        this.merchantChannelId = merchantChannelId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(String transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }

    public String getPayeractype() {
        return payeractype;
    }

    public void setPayeractype(String payeractype) {
        this.payeractype = payeractype;
    }

    public String getInit_mode() {
        return init_mode;
    }

    public void setInit_mode(String init_mode) {
        this.init_mode = init_mode;
    }

    public String getPurpose_code() {
        return purpose_code;
    }

    public void setPurpose_code(String purpose_code) {
        this.purpose_code = purpose_code;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public Timestamp getInsertedat() {
        return insertedat;
    }

    public void setInsertedat(Timestamp insertedat) {
        this.insertedat = insertedat;
    }

    @Override
    public String toString() {
        return "StaticQrTxn{" +
                "rowid=" + rowid +
                ", txnId='" + txnId + '\'' +
                ", checksum='" + checksum + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", creditVpa='" + creditVpa + '\'' +
                ", customerVpa='" + customerVpa + '\'' +
                ", gatewayResponseCode='" + gatewayResponseCode + '\'' +
                ", gatewayResponseMessage='" + gatewayResponseMessage + '\'' +
                ", gatewayTransactionId='" + gatewayTransactionId + '\'' +
                ", merchantChannelId='" + merchantChannelId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", merchantTransactionId='" + merchantTransactionId + '\'' +
                ", rrn='" + rrn + '\'' +
                ", transactionAmount='" + transactionAmount + '\'' +
                ", transactionTimestamp='" + transactionTimestamp + '\'' +
                ", payeractype='" + payeractype + '\'' +
                ", init_mode='" + init_mode + '\'' +
                ", purpose_code='" + purpose_code + '\'' +
                ", bin='" + bin + '\'' +
                ", insertedat=" + insertedat +
                '}';
    }
}
