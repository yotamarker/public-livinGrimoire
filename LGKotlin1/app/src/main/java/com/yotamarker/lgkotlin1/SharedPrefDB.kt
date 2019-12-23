package com.yotamarker.lgkotlin1

import android.content.Context
import android.content.SharedPreferences

class SharedPrefDB(private val context: Context) : AbsDictionaryDB() {
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        this.sharedPreferences = this.context.getSharedPreferences("t800", Context.MODE_PRIVATE)
        this.editor = this.sharedPreferences.edit()
    }

    override fun save(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
        editor.commit()
    }

    override fun load(key: String): String {
        val result = sharedPreferences.getString(key, "null")
        this.existsInDB = result != "null"
        return result!!
    }

    override fun getExistsInDB(): Boolean? {
        return existsInDB
    }
}