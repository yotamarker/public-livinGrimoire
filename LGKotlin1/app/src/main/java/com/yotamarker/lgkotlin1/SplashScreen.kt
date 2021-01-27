package com.yotamarker.lgkotlin1
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import gr.net.maroulis.library.EasySplashScreen


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = EasySplashScreen(this@SplashScreen)
            .withFullScreen()
            .withTargetActivity(MainActivity::class.java)
            .withSplashTimeOut(5000)
            .withBackgroundColor(Color.parseColor("#1a1b29"))
            .withHeaderText("yotamarker.com")
            .withFooterText("created by moti barski")
            .withBeforeLogoText("loading")
            .withAfterLogoText("give me a sec")
        config.withBackgroundResource(R.drawable.splash)
        config.headerTextView.setTextColor(Color.WHITE)
        config.footerTextView.setTextColor(Color.WHITE)
        config.beforeLogoTextView.setTextColor(Color.WHITE)
        config.afterLogoTextView.setTextColor(Color.WHITE)
        val easySplashScreen = config.create()
        setContentView(easySplashScreen)
    }
}