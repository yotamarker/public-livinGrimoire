package com.yotamarker.lgkotlin1;

public class AbsDictionaryDBShadow extends AbsDictionaryDB {
    // used as a fill in class if you want to test the chobit and havent built a DB
    // class yet
    @Override
    public void save(String key, String value) {
    }

    @Override
    public String load(String key) {
        return "null";
    }

    @Override
    public Boolean getExistsInDB() {
        return false;
    }

}
