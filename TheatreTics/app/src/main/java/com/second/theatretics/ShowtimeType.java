package com.second.theatretics;

public class ShowtimeType {
    int st_id;
    String st_timestart;
    int t_id;
    int m_id;

    public int getSt_id() {
        return st_id;
    }

    public void setSt(int st_id) {
        this.st_id = st_id;
    }

    public String getSt_timestart() {
        return st_timestart;
    }

    public void setSt_timestart(String st_timestart) {
        this.st_timestart = st_timestart;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }
    public ShowtimeType()
    {

    }
    public ShowtimeType(int st_id, String st_timestart ,int t_id,int m_id)
    {

        this.st_id=st_id;
        this.st_timestart=st_timestart;
        this.t_id=t_id;
        this.m_id=m_id;

    }

    public ShowtimeType(String st_timestart ,int t_id,int m_id)
    {
        this.st_timestart=st_timestart;
        this.t_id=t_id;
        this.m_id=m_id;

    }

}
