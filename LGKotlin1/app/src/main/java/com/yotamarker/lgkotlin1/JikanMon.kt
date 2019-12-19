package com.yotamarker.lgkotlin1

class JikanMon {
    private val playGround = PlayGround()
    private var x = -1
    private var timePause = 3
    val isBlocked:Boolean
        get() {
            val nowSeconds = playGround.getSecondsAsInt()
            if ((x < 0 || nowSeconds > x) || (x > 60 && nowSeconds > (x - 60)))
            {
                x = -1
                return false
            }
            return true
        }
    fun block() {
        this.x = playGround.getSecondsAsInt() + timePause
    }
    fun setTimePause(timePause:Int) {
        if ((timePause > 0) && (timePause < 30))
        {
            this.timePause = timePause
        }
    }
}