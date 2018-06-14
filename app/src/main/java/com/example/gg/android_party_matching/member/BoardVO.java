package com.example.gg.android_party_matching.member;

public class BoardVO {
    String registerDate;
    String title;
    int max_participant;
    int now_participant;
    String registrant;

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMax_participant() {
        return max_participant;
    }

    public void setMax_participant(int max_participant) {
        this.max_participant = max_participant;
    }

    public int getNow_participant() {
        return now_participant;
    }

    public void setNow_participant(int now_participant) {
        this.now_participant = now_participant;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public BoardVO(String registerDate, String title, int max_participant, int now_participant, String registrant){
        this.registerDate = registerDate;
        this.title = title;
        this.max_participant = max_participant;
        this.now_participant = now_participant;
        this.registrant = registrant;
    }
}
