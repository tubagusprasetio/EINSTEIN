package com.example.tbprassetyo.einstein.model;

public class Akun {
    private String nisn;
    private String kelas;
    private String username;
    private String password;

    public Akun(String nisn, String kelas, String username, String password) {
        this.nisn = nisn;
        this.kelas = kelas;
        this.username = username;
        this.password = password;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
