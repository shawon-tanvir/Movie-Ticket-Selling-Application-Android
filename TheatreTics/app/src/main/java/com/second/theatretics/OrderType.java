package com.second.theatretics;

public class OrderType {
    int o_id;
    int total_seat;
    int total_amount;
    int o_confirmation;
    int u_id;
    int m_id;

    public OrderType() {
    }
    public OrderType(int total_seat, int total_amount, int o_confirmation, int u_id, int m_id) {
        this.total_seat = total_seat;
        this.total_amount = total_amount;
        this.o_confirmation = o_confirmation;
        this.u_id = u_id;
        this.m_id = m_id;
    }
    public OrderType(int o_id, int total_seat, int total_amount, int o_confirmation, int u_id, int m_id) {
        this.o_id = o_id;
        this.total_seat = total_seat;
        this.total_amount = total_amount;
        this.o_confirmation = o_confirmation;
        this.u_id = u_id;
        this.m_id = m_id;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getTotal_seat() {
        return total_seat;
    }

    public void setTotal_seat(int total_seat) {
        this.total_seat = total_seat;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getO_confirmation() {
        return o_confirmation;
    }

    public void setO_confirmation(int o_confirmation) {
        this.o_confirmation = o_confirmation;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }
}
