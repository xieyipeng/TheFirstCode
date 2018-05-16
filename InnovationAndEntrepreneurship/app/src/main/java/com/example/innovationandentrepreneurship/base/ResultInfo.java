package com.example.innovationandentrepreneurship.base;

/**
 * Created by 解奕鹏 on 2018/3/18.
 */

public class ResultInfo {
    private String name;
    private String synopsis;
    private String  distance;

    public ResultInfo(String name, String synopsis, String distance) {
        this.name = name;
        this.synopsis = synopsis;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
