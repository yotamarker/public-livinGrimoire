package com.yotamarker.lgkotlin1;

public class MCode {
    private String name = "";
    private Integer Classification = 0;
    private Boolean isActive = false;

    public MCode(String name, Integer classification, Boolean isActive) {
        super();
        this.name = name;
        Classification = classification;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
