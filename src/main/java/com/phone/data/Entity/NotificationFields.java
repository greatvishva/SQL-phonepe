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

@Table(name ="notification_fields")
public class NotificationFields {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "row_id")
    private long rowid;

    @Column(name = "Id")    private Long Id;
    @Column(name = "pos_Device_Id")    private String pos_Device_Id;
    @Column(name = "card_holder_name")    private String card_holder_name;
    @Column(name = "masked_card_number")    private String masked_card_number;
    @Column(name = "transaction_mode")    private String transaction_mode;
    @Column(name = "acquirer_bank")    private String acquirer_bank;
    @Column(name = "card_type")    private String card_type;
    @Column(name = "card_network")    private String card_network;
    @Column(name = "card_issuer_country_code")    private String card_issuer_country_code;
    @Column(name = "amount")    private String amount;
    @Column(name = "invoice_number")    private String invoice_number;
    @Column(name = "batch_number")    private String batch_number;
    @Column(name = "TerminalId")    private String terminalId;


    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public long getRowid() {
        return rowid;
    }

    public void setRowid(long rowid) {
        this.rowid = rowid;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPos_Device_Id() {
        return pos_Device_Id;
    }

    public void setPos_Device_Id(String pos_Device_Id) {
        this.pos_Device_Id = pos_Device_Id;
    }

    public String getCard_holder_name() {
        return card_holder_name;
    }

    public void setCard_holder_name(String card_holder_name) {
        this.card_holder_name = card_holder_name;
    }

    public String getMasked_card_number() {
        return masked_card_number;
    }

    public void setMasked_card_number(String masked_card_number) {
        this.masked_card_number = masked_card_number;
    }

    public String getTransaction_mode() {
        return transaction_mode;
    }

    public void setTransaction_mode(String transaction_mode) {
        this.transaction_mode = transaction_mode;
    }

    public String getAcquirer_bank() {
        return acquirer_bank;
    }

    public void setAcquirer_bank(String acquirer_bank) {
        this.acquirer_bank = acquirer_bank;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_network() {
        return card_network;
    }

    public void setCard_network(String card_network) {
        this.card_network = card_network;
    }

    public String getCard_issuer_country_code() {
        return card_issuer_country_code;
    }

    public void setCard_issuer_country_code(String card_issuer_country_code) {
        this.card_issuer_country_code = card_issuer_country_code;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    @Override
    public String toString() {
        return "NotificationFields{" +
                "rowid=" + rowid +
                ", Id='" + Id + '\'' +
                ", pos_Device_Id='" + pos_Device_Id + '\'' +
                ", card_holder_name='" + card_holder_name + '\'' +
                ", masked_card_number='" + masked_card_number + '\'' +
                ", transaction_mode='" + transaction_mode + '\'' +
                ", acquirer_bank='" + acquirer_bank + '\'' +
                ", card_type='" + card_type + '\'' +
                ", card_network='" + card_network + '\'' +
                ", card_issuer_country_code='" + card_issuer_country_code + '\'' +
                ", amount='" + amount + '\'' +
                ", invoice_number='" + invoice_number + '\'' +
                ", batch_number='" + batch_number + '\'' +
                ", terminalId='" + terminalId + '\'' +
                '}';
    }


}
