package com.second.theatretics;

public class TheatresType {
    int t_id;
    String t_name;
    int h_id;

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public int getH_id() {
        return h_id;
    }

    public void setH_id(int h_id) {
        this.h_id = h_id;
    }
    public TheatresType()
    {

    }
    public TheatresType(int t_id, String t_name ,int h_id)
    {

        this.t_id=t_id;
        this.t_name=t_name;
        this.h_id=h_id;

    }

    public TheatresType(String t_name ,int h_id)
    {
        this.t_name=t_name;
        this.h_id=h_id;

    }
}
