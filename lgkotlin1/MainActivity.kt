package com.yotamarker.lgkotlin1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.BatteryManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.TextUtils.substring
import android.util.Log
import android.view.View
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.nio.file.Files.size
import java.util.*


class MainActivity : AppCompatActivity() , TextToSpeech.OnInitListener{
    private var tts: TextToSpeech? = null
    var chii = Chobit()
    var toggleTic = false
    //var spoke = false
    var mbTTS = TTSVoice(this)
    private val mBatInfoReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            val b8TRiStr = chii.doIt("","$level charge","")
            mbTTS.voiceIt(b8TRiStr)
            //voiceIt(b8TRiStr)
            if (b8TRiStr != ""){editText.setText(b8TRiStr)}
            //editText.setText(chii.doIt("","$level charge",""))
            //txtBox.setText("$level% $b8TRcounter")
            val status: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                    || status == BatteryManager.BATTERY_STATUS_FULL

            val chargePlug: Int = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
            val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
            val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
            //txtBox.setText("$usbCharge")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tts = TextToSpeech(this, this)
        supportActionBar?.hide()
        this.registerReceiver(this.mBatInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        var count = 0
        val t = object:Thread() {
            public override fun run() {
                while ((!isInterrupted()))
                {
                    try
                    {
                        Thread.sleep(4000)
                        runOnUiThread(object:Runnable {
                            public override fun run() {
                                if (toggleTic){
                                    count++
                                    mbTTS.voiceIt(count.toString())}
                            }
                        })
                    }
                    catch (e:InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        t.start()
        imageView2.setOnLongClickListener {

            toggleTic = !toggleTic
            if (toggleTic){imageView2.setImageResource(R.drawable.brainpic)}
            else{imageView2.setImageResource(R.drawable.yggdrasil)}
            true
        }
    }
    fun clrText(view: View){
        editText.setText("")
    }
    fun engage(view: View){
        var resultStr = chii.doIt(editText.text.toString(),"","")
        editText.setText("")
        mbTTS.voiceIt(resultStr)
        if (mbTTS.TTS){speakOut(resultStr)}
        face(chii.getEmot())
    }
    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                //buttonSpeak!!.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

    }
    private fun speakOut(leftOver:String) {
        tts!!.speak(leftOver, TextToSpeech.QUEUE_FLUSH, null,"")
    }
    private fun face(face:String) {
        when (face) {
            "speaking" -> imageView.setImageResource(R.drawable.sugoikibun)

            else -> {imageView.setImageResource(R.drawable.waa)}
        }
    }
    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}
