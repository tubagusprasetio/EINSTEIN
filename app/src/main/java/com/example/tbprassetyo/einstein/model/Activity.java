package com.example.tbprassetyo.einstein.model;

public class Activity {
    private int id;
    private int requestNumber;
    private int requestNameMateri;

    private int answearCorrect;
    private int answearFail;

    public Activity(int requestNumber, int requestNameMateri, int answearCorrect, int answearFail){

        this.requestNumber = requestNumber;
        this.requestNameMateri = requestNameMateri;
        this.answearCorrect = answearCorrect;
        this.answearFail = answearFail;

    }

    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }

    public int getRequestNumber(){return requestNumber;}
    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public int getNameMateri(){return requestNameMateri;}
    public void setRequestNameMateri(int requestNameMateri){
        this.requestNameMateri = requestNameMateri;
    }

    public int getAnswearCorrect(){return answearCorrect;}
    public void setAnswearCorrect(int answearCorrect) {
        this.answearCorrect = answearCorrect;
    }

    public int getAnswearFail(){return answearFail;}
    public void setAnswearFail(int answearFail) {
        this.answearFail = answearFail;
    }

}
