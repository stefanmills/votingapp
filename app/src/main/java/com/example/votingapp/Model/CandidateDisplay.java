package com.example.votingapp.Model;

public class CandidateDisplay {
    private String candidateName;
    private String pictureUrl;
    private String ID;
    private String position;
//    private String[] financial;
//    private String[] wocom;


    public CandidateDisplay() {
    }

    public CandidateDisplay(String candidateName, String pictureUrl, String ID, String position) {
        this.candidateName = candidateName;
        this.pictureUrl = pictureUrl;
        this.ID = ID;
        this.position = position;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
