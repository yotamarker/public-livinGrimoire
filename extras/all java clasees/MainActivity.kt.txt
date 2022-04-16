package com.yotamarker.lgkotlin1

import android.Manifest
import android.content.*
import android.content.pm.PackageManager
import android.location.*
import android.os.BatteryManager
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener, AccelerometerListener{
    //STT :
    //private var context:Context?=null
    private var hasAudioPermission = false
    private val REQUEST_CODE = 1
    private val RECOGNITION_RESULT = 2
    // nightrider display emot
    val nightRider2 = NightRider();
    val nightRider3 = NightRider()
    val nightRider4 = NightRider()
    //end nightrider beefup region
    // global vars
    private var tts: TextToSpeech? = null
    val kanjiClock = KanjiClock()
    var chii:ChobitV2? = null
    var toggleTic = false
    var gyroX = 0.0f; var gyroY = 0.0f;var gyroCounter = 0;var gyroGate = JikanMon()
    var locationManager: LocationManager? = null //GPS beefup
    var locationListener: LocationListener? =null //GPS beefup
    //var spoke = false
    val nightRider = NightRider()
    val b8TriTimeGate = TimeGate()
    var prevBatState = false;
    var mbTTS = TTSVoice(this)
    var cerabellumV2:actionable?=null //TODO
    var brain:Brain?=null
    private fun batterySkin(str:String){
                        var resultStr = chii!!.doIt("",str,"")
                editText.setText("")
                mbTTS.voiceIt(resultStr)
                if (mbTTS.TTS){speakOut(resultStr)}
    }
    private val mBatInfoReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            val status: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                    || status == BatteryManager.BATTERY_STATUS_FULL
            val chargePlug: Int = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
            val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
            val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
            val charging = usbCharge || acCharge;
            if(prevBatState xor charging){
                prevBatState=charging;
                if(usbCharge){batterySkin("slow");return;}
                if(acCharge){batterySkin("fast");return;}
                batterySkin("unplugged")
            }
            if (!b8TriTimeGate.isClosed) {
                b8TriTimeGate.close(5)
                batterySkin("$level charge")
            }
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
            //STT
            if(requestCode==1){
                hasAudioPermission = true}
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
        askPermission()
        nightRider2.setMode(2);nightRider3.setMode(3);nightRider4.setMode(4)
        val handler = Handler()

        val run = object : Runnable {
            override fun run() {
                Log.i("hadouken", "A second passed by")
                var s1 = "";
                s1 = kanjiClock.timeInKanji()
                textView.setText(s1)
                when (chii!!.getSoulEmotion()) {
                    "2" -> s1 = nightRider2.getDisplay()
                    "3" -> s1 = nightRider3.getDisplay()
                    "4" -> s1 = nightRider4.getDisplay()
                    else -> {s1 = nightRider.getDisplay()
                    }
                }
                textView2.setText(s1)
//                var resultStr = chii!!.doIt("","","")
//                editText.setText("")
//                mbTTS.voiceIt(resultStr)
//                if (mbTTS.TTS){speakOut(resultStr)}
                handler.postDelayed(this, 1000)
            }
        }

        handler.post(run)
        b8TriTimeGate.setPause(5)
        b8TriTimeGate.close(1)
        //chii = Chobit(SharedPrefDB(this))
        chii = ChobitV2(Personality1(SharedPrefDB(this)))
        chii!!.loadPersonality(Personality1(SharedPrefDB(this)))
        tts = TextToSpeech(this, this)
        cerabellumV2=CerabellumV2(this)//TODO
        val translatorChobit:ChobitV2 = ChobitV2(PersonalityTranslator(SharedPrefDB(this)))
        brain = Brain(cerabellumV2!!,chii!!,chii!!,translatorChobit)
        supportActionBar?.hide()
        //mainActivityLayout.setBackgroundResource(R.drawable.roboowl2)//TODO
        //mainActivityLayout.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.roboowl2))
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
        runSpeechRecognition()
        //engageChobit()
    }
    fun clrText(view: View){
        editText.setText("")
    }
    fun engageChobit(){
        val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(editText.windowToken,0)
        //var resultStr = chii!!.doIt(editText.text.toString(),"","")
        //TODO
        brain!!.doIt(editText.text.toString(),"","")
        //cerabellumV2!!.act(resultStr)
        //face(chii!!.getEmot())
    }
    fun cerabellumSpeak(resultStr:String){
        //TODO
        editText.setText("")
        mbTTS.voiceIt(resultStr)
        if (mbTTS.TTS){speakOut(resultStr)}
    }
    fun clearTxtBox(){editText.setText("")}
    fun screenFlip1(){
        mainActivityLayout.setBackgroundResource(R.drawable.roboowl2)
    }
    fun screenFlip2(){
        mainActivityLayout.setBackgroundResource(R.drawable.chobit800)
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
    //STT :
    private fun askPermission() {
        //create a list of permissions
        var listPerm = ArrayList<String>()
        //var listPerm:List<String> = ArrayList() // contains the recog results per talk
        //get permission status for audio record
        val audioPerm = ContextCompat.checkSelfPermission(this.applicationContext, Manifest.permission.RECORD_AUDIO)
        //check if the record audio permission is granted
        if (audioPerm != PackageManager.PERMISSION_GRANTED)
        {
            listPerm.add(Manifest.permission.RECORD_AUDIO)
        }
        else
        {
            hasAudioPermission = true
        }
        //check if our list is not empty, and ask permission for it's items.
        if (!listPerm.isEmpty())
        {
            //ask permission by requests.
            ActivityCompat.requestPermissions(this, listPerm.toArray(arrayOfNulls<String>(listPerm.size)), REQUEST_CODE)
        }
    }
    private fun runSpeechRecognition() {
        //handle the speech recognition intent
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        //tell the intent that we want to speak freely
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        //tell the intent that we want to use the default language
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault())// hebrew
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
        //show the user a text to explain what we want.
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to phone")
        if (hasAudioPermission)
        {
            try
            {
                startActivityForResult(intent, RECOGNITION_RESULT)
            }
            catch (e: ActivityNotFoundException) {
                Toast.makeText(this.applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
            }
            catch (e:Exception) {
                Log.e("WTF", "runSpeechRecognition: " + e.message)
            }
        }
    }

    protected override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            //in case the request code is 2 - recognition intent
            RECOGNITION_RESULT -> if (resultCode == RESULT_OK && data != null)
            {
                //get array list of all our result (can be 1, can be 5)
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val myAdapter = LangAdapter(this.applicationContext, result)
                editText.setText(result.get(0).trim().toLowerCase().substring(0,result.get(0).trim().length) + "")
                engageChobit()
                //StaticString.str =result.get(0).trim().toLowerCase().substring(1,result.get(0).trim().length) + ""
                //Toast.makeText(this.applicationContext, result.get(0).trim().toLowerCase().substring(1, result.get(0).trim().length) + "", Toast.LENGTH_SHORT).show()
            }
            else -> Toast.makeText(this.applicationContext, "the programmer is not so bright", Toast.LENGTH_SHORT).show()
        }
    }
}
