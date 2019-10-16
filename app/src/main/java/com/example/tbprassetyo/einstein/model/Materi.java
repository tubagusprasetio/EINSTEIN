package com.example.tbprassetyo.einstein.model;

public class Materi {

    private int id;
    private int activityid;
    private String materi_name;

    public Materi(int id,String materi_name) {
        this.activityid = id;
        this.materi_name = materi_name;
    }

    public int getId() {
        return activityid;
    }

    public void setId(int activityid) {
        this.activityid = activityid;
    }

    public String getMateri_name() {
        return materi_name;
    }

    public void setMateri_name(String materi_name) {
        this.materi_name= materi_name;
    }
}
