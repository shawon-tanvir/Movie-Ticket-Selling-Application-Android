package com.second.theatretics;

public class UsersType {
    int u_id;
    String u_name;
    String u_address;
    String u_phone;
    String u_email;
    String u_password;
    int u_catagory;

    public UsersType() {

    }
    public UsersType(int u_id, String u_name, String u_address, String u_phone, String u_email, String u_password, int u_catagory) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_address = u_address;
        this.u_phone = u_phone;
        this.u_email = u_email;
        this.u_password = u_password;
        this.u_catagory = u_catagory;
    }
    public UsersType(String u_name, String u_address, String u_phone, String u_email, String u_password, int u_catagory) {
        this.u_name = u_name;
        this.u_address = u_address;
        this.u_phone = u_phone;
        this.u_email = u_email;
        this.u_password = u_password;
        this.u_catagory = u_catagory;
    }
    public UsersType(String u_name,String u_password) {
        this.u_name = u_name;
        this.u_address = u_address;
        this.u_phone = u_phone;
        this.u_email = u_email;
        this.u_password = u_password;
        this.u_catagory = u_catagory;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public int getU_catagory() {
        return u_catagory;
    }

    public void setU_catagory(int u_catagory) {
        this.u_catagory = u_catagory;
    }
}
