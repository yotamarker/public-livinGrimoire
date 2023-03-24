package com.yotamarker.lgkotlinfull.LGCore

abstract class AbsDictionaryDB {
    abstract fun save(key: String, value: String)
    abstract fun load(key: String): String //TODO set to return null as default if key not found !!!
}