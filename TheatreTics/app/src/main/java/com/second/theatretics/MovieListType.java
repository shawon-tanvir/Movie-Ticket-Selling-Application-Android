package com.second.theatretics;

public class MovieListType {
    int m_id;
    String m_name;
    byte[] m_poster;
    int m_running;

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public byte[] getM_poster() {
        return m_poster;
    }

    public void setM_poster(byte[] m_poster) {
        this.m_poster = m_poster;
    }

    public int getM_running() {
        return m_running;
    }

    public void setM_running(int m_running) {
        this.m_running = m_running;
    }



    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public MovieListType()
    {

    }
    public MovieListType(int Id, String name , byte[] image,int running)
    {

        this.m_id=Id;
        this.m_name=name;
        this.m_poster=image;
        this.m_running=running;

    }

    public MovieListType(String name, byte[] image,int running)
    {
        this.m_name=name;
        this.m_poster=image;
        this.m_running=running;

    }




}
