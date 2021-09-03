package com.yotamarker.lgkotlin1;

abstract public class AbsDictionaryDB {
    protected Boolean existsInDB = false;

    public abstract void save(String key, String value);

    public abstract String load(String key);

    public abstract Boolean getExistsInDB();
}
