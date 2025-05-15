package com.example.tryoutpas_15_27.Premier;

public class Team {
    private String strTeam;
    private String strStadium;
    private String strBadge;

    public Team(String strTeam, String strStadium, String strBadge){
        this.strStadium=strStadium;
        this.strTeam=strTeam;
        this.strBadge=strBadge;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public String getStrStadium() {
        return strStadium;
    }

    public String getStrBadge() {
        return strBadge;
    }

}
