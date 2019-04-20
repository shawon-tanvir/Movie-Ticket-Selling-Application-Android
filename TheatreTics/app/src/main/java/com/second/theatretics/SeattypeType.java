package com.second.theatretics;

public class SeattypeType {
    int stt_id;
    String stt_name;
    int st_id;

    public int getStt_id() {
        return stt_id;
    }

    public void setStt_id(int stt_id) {
        this.stt_id = stt_id;
    }

    public String getStt_name() {
        return stt_name;
    }

    public void setStt_name(String stt_name) {
        this.stt_name = stt_name;
    }

    public int getSt_id() {
        return st_id;
    }

    public void setSt_id(int st_id) {
        this.st_id = st_id;
    }
    public SeattypeType()
    {

    }
    public SeattypeType(int stt_id, String stt_name ,int st_id)
    {

        this.stt_id=stt_id;
        this.stt_name=stt_name;
        this.st_id=st_id;

    }

    public SeattypeType(String stt_name ,int st_id)
    {
        this.stt_name=stt_name;
        this.st_id=st_id;

    }
}
