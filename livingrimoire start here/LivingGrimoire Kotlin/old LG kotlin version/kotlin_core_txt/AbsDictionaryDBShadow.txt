package com.yotamarker.lgkotlinfull.LGCore

class AbsDictionaryDBShadow : AbsDictionaryDB() {
    // used as a fill in class if you want to test the chobit and havent built a DB
    // class yet
    override fun save(key: String, value: String) {}
    override fun load(key: String): String {
        return "null"
    }
}