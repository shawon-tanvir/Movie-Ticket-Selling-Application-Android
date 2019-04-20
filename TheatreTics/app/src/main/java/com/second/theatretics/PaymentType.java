package com.second.theatretics;

public class PaymentType {
    int p_id;
    String p_phone;
    String p_transactionNumber;
    String p_datetime;
    int p_type;
    int p_amount;
    int o_id;

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_phone() {
        return p_phone;
    }

    public void setP_phone(String p_phone) {
        this.p_phone = p_phone;
    }

    public String getP_transactionNumber() {
        return p_transactionNumber;
    }

    public void setP_transactionNumber(String p_transactionNumber) {
        this.p_transactionNumber = p_transactionNumber;
    }

    public String getP_datetime() {
        return p_datetime;
    }

    public void setP_datetime(String p_datetime) {
        this.p_datetime = p_datetime;
    }

    public int getP_type() {
        return p_type;
    }

    public void setP_type(int p_type) {
        this.p_type = p_type;
    }

    public int getP_amount() {
        return p_amount;
    }

    public void setP_amount(int p_amount) {
        this.p_amount = p_amount;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public PaymentType(int p_id, String p_phone, String p_transactionNumber, String p_datetime, int p_type, int p_amount, int o_id) {
        this.p_id = p_id;
        this.p_phone = p_phone;
        this.p_transactionNumber = p_transactionNumber;
        this.p_datetime = p_datetime;
        this.p_type = p_type;
        this.p_amount = p_amount;
        this.o_id = o_id;
    }
    public PaymentType( String p_phone, String p_transactionNumber, String p_datetime, int p_type, int p_amount, int o_id) {
        this.p_phone = p_phone;
        this.p_transactionNumber = p_transactionNumber;
        this.p_datetime = p_datetime;
        this.p_type = p_type;
        this.p_amount = p_amount;
        this.o_id = o_id;
    }
    public PaymentType() {

    }
}
