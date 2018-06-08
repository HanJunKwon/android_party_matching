package com.example.gg.android_party_matching.party;

public class PartyVO {

    private long code;                   // PK
    private long master_member_code;    // 방장 회원코드
    private String master_name;         //  방장 이름
    private String title;                // 제목
    private String contents;            // 내용
    private int join_people;           // 파티 참여 인원
    private int max_people;            // max인원
    private String party_date;         // 파티 하는 날짜
    private String make_date;          // 파티 만든 날짜
    private String latitude;            // 위도
    private String longitude;           // 경도
    private int classification;        // 분류 예) 1: 음식점 2: pc방

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getMaster_member_code() {
        return master_member_code;
    }

    public void setMaster_member_code(long master_member_code) {
        this.master_member_code = master_member_code;
    }

    public String getMaster_name() {
        return master_name;
    }

    public void setMaster_name(String master_name) {
        this.master_name = master_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getJoin_people() {
        return join_people;
    }

    public void setJoin_people(int join_people) {
        this.join_people = join_people;
    }

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public String getParty_date() {
        return party_date;
    }

    public void setParty_date(String party_date) {
        this.party_date = party_date;
    }

    public String getMake_date() {
        return make_date;
    }

    public void setMake_date(String make_date) {
        this.make_date = make_date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }
}
