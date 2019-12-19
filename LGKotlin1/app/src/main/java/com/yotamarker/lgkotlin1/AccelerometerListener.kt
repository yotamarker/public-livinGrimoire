package com.yotamarker.lgkotlin1

interface AccelerometerListener {

    fun onAccelerationChanged(x: Float, y: Float, z: Float)

    fun onShake(force: Float)
}
