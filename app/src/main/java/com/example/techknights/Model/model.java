package com.example.techknights.Model;

public class model {

    String Mission, Area, Time, ImpLevel, purl;

    public model(String Mission, String Area, String Time, String ImpLevel, String purl) {
        this.Mission = Mission;
        this.Area = Area;
        this.Time = Time;
        this.ImpLevel = ImpLevel;
        this.purl = purl;
    }

    public model() {

    }

    public String getMission() {
        return Mission;
    }

    public void setMission(String mission) {
        Mission = mission;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getImpLevel() {
        return ImpLevel;
    }

    public void setImpLevel(String impLevel) {
        ImpLevel = impLevel;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}


