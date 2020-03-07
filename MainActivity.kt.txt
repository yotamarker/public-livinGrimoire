package com.yotamarker.lgkotlin1

import android.Manifest
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
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.nio.file.Files.size
import java.util.*
import kotlin.math.absoluteValue
import android.support.v4.os.HandlerCompat.postDelayed
import android.R.string.no
import android.R.attr.name
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import android.view.KeyEvent
import android.R.string.no
import android.R.attr.name
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.location.*
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import java.io.IOException


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener, AccelerometerListener{
    // global vars
    private var tts: TextToSpeech? = null
    val kanjiClock = KanjiClock()
    var chii:Chobit? = null
    var toggleTic = false
    var gyroX = 0.0f; var gyroY = 0.0f;var gyroCounter = 0;var gyroGate = JikanMon()
    var locationManager: LocationManager? = null //GPS beefup
    var locationListener: LocationListener? =null //GPS beefup
    //var spoke = false
    val nightRider = NightRider()
    var mbTTS = TTSVoice(this)
    private val mBatInfoReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            val b8TRiStr = chii!!.doIt("","$level charge","")
            mbTTS.voiceIt(b8TRiStr)
            //voiceIt(b8TRiStr)
            //if (b8TRiStr != ""){editText.setText(b8TRiStr)}
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
    fun placeToString() {
        //GPS beefup
        // convert gps coordinates into place name
        var returnString = ""
        val gcdLocale = Geocoder(this, Locale.ENGLISH) //new Geocoder(this, Locale.ENGLISH)
        var localeAdd:List<Address>? = null
        try
        {

            localeAdd = gcdLocale.getFromLocation(Tokoro.lat, Tokoro.lon, 1)
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        //if localeAdd is null (false) get out of the function (method)
        assert(localeAdd != null)
        if (localeAdd!!.size > 0)
        {
            val myAdd = localeAdd.get(0).getAddressLine(0)
            returnString = myAdd
        }
        Tokoro.placeName = returnString
    }
    override
    fun onRequestPermissionsResult(requestCode:Int, @NonNull permissions:Array<String>, @NonNull grantResults:IntArray) {
        //GPS beefup
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) === PackageManager.PERMISSION_GRANTED)
            {
                locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1.1f, locationListener)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//disables screen timeout
        // getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // reenable screen timeout
        val handler = Handler()

        val run = object : Runnable {
            override fun run() {
                Log.i("hadouken", "A second passed by")
                var s1 = "";
                s1 = kanjiClock.timeInKanji()
                textView.setText(s1)
                s1 = nightRider.getDisplay()
                textView2.setText(s1)
                handler.postDelayed(this, 1000)
            }
        }

        handler.post(run)

        chii = Chobit(SharedPrefDB(this))
        tts = TextToSpeech(this, this)
        supportActionBar?.hide()
        this.registerReceiver(this.mBatInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        var count = 0
        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                engageChobit()
                return@OnKeyListener true
            }
            false
        })
        //GPS beefup :
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object:LocationListener {
            override
            fun onLocationChanged(location: Location) {
                try {
                    Log.i("Location", "latcpp: " + location.getLatitude() + "lon: " + location.getLongitude()) // modify hya
                    Tokoro.lat = location.getLatitude();Tokoro.lon = location.getLongitude()
                    placeToString()
                } catch (e: Exception) {
                }


            }
            override
            fun onStatusChanged(s:String, i:Int, bundle:Bundle) {
            }
            override
            fun onProviderEnabled(s:String) {
            }
            override
            fun onProviderDisabled(s:String) {
            }
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
        else
        {
            locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1f, locationListener)
        }
        //GPS beefup on create end point
    }
    fun engage(view: View){
        engageChobit()
    }
    fun clrText(view: View){
        editText.setText("")
    }
    fun engageChobit(){
        val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(editText.windowToken,0)
        var resultStr = chii!!.doIt(editText.text.toString(),"","")
        editText.setText("")
        mbTTS.voiceIt(resultStr)
        if (mbTTS.TTS){speakOut(resultStr)}
        //face(chii!!.getEmot())
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
    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
        if (AccelerometerManager.isListening)
        {
            AccelerometerManager.stopListening()
            Toast.makeText(this, "onDestroy Accelerometer Stopped", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onShake(force:Float) {
        Toast.makeText(this, "Motion detected", Toast.LENGTH_SHORT).show()
    }
    override fun onAccelerationChanged(x:Float, y:Float, z:Float) {
        // *add gyroy below
        if((gyroX - x) > 2 && !gyroGate.isBlocked){
            gyroCounter++
            gyroGate.block()
            Toast.makeText(this, "moved $gyroCounter", Toast.LENGTH_SHORT).show()
            // out put uses this 4 lines :
            var resultStr = chii!!.doIt("","shake","")
            editText.setText("")
            mbTTS.voiceIt(resultStr)
            if (mbTTS.TTS){speakOut(resultStr)}
             }
        else{}
        gyroX=x;gyroY=y;
    }
    override protected fun onResume() {
        super.onResume()
        if (AccelerometerManager.isSupported(this))
        {
            AccelerometerManager.startListening(this)
        }
    }
    override fun onStop() {
        super.onStop()
        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening)
        {
            //Start Accelerometer Listening
            AccelerometerManager.stopListening()
            Toast.makeText(this, "onStop Accelerometer Stopped", Toast.LENGTH_SHORT).show()
        }
    }
}
