package com.second.theatretics;

public class HallListType {
    int h_id;
    String h_name;
    String h_address;
    String h_phone;
    int u_id;

    public int getH_id() {
        return h_id;
    }

    public void setH_id(int h_id) {
        this.h_id = h_id;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getH_address() {
        return h_address;
    }

    public void setH_address(String h_address) {
        this.h_address = h_address;
    }

    public String getH_phone() {
        return h_phone;
    }

    public void setH_phone(String h_phone) {
        this.h_phone = h_phone;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
    public HallListType()
    {

    }
    public HallListType(int Id, String name , String address,String phone,int u_id)
    {

        this.h_id=Id;
        this.h_name=name;
        this.h_address=address;
        this.h_phone=phone;
        this.u_id=u_id;

    }

    public HallListType(String name , String address,String phone,int u_id)
    {
        this.h_name=name;
        this.h_address=address;
        this.h_phone=phone;
        this.u_id=u_id;

    }
}
