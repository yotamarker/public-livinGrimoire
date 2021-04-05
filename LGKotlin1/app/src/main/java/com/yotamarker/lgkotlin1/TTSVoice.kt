package com.yotamarker.lgkotlin1

import android.content.Context
import android.media.MediaPlayer

class TTSVoice(private val context:Context){
    // add voice files in a raw named dir in res dir
    public var TTS: Boolean = false
    get() = field
    private val voiceArrayList = ArrayList<String>()
    init{
        //this runs when the obj is created
        println("object was created")
    }
    fun voiceIt(inStr:String) {
        TTS = false
        //add full phrases here
        var mediaPlayer: MediaPlayer
        when (inStr) {
            "hadouken" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.hadouken)
                mediaPlayer.setNextMediaPlayer(MediaPlayer.create(context, R.raw.shouryuken))
                mediaPlayer.start()
            }
            "shouryuken" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.shouryukenredo)
                mediaPlayer.start()
            }
            "pet me" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.petme)
                mediaPlayer.start()
            }
            "scream" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.scream)
                mediaPlayer.start()
            }
            "shout" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.s1)
                mediaPlayer.start()
            }
            "filthy" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.filth)
                mediaPlayer.start()
            }
            "no you may not" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.noyoumaynot)
                mediaPlayer.start()
            }
            "to nurse and protect" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.tonurseandprotect)
                mediaPlayer.start()
            }
            "use the word gosh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.usethewordgosh)
                mediaPlayer.start()
            }
            "watch your potty mouth" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.watchyourpottymouth)
                mediaPlayer.start()
            }
            "tomorrow if you behave" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.tomorrowifyoubehave)
                mediaPlayer.start()
            }
            "use the word mean" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.usethewordmean)
                mediaPlayer.start()
            }
            "use the word fufu" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.usethewordfufu)
                mediaPlayer.start()
            }
            "bad boy no more television today" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.badboynomoretelevisiontoday)
                mediaPlayer.start()
            }
            "and only kid shows" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.andonlykidshows)
                mediaPlayer.start()
            }
            "tomorrow if you behave" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.tomorrowifyoubehave)
                mediaPlayer.start()
            }
            "and dont you forget it" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.anddontyouforgetit)
                mediaPlayer.start()
            }
            "prrr10" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.prrr10)
                mediaPlayer.start()
            }
            "prrr11" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.prrr11)
                mediaPlayer.start()
            }
            "prrr12" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.prrr12)
                mediaPlayer.start()
            }
            "prrr20" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.prrr20)
                mediaPlayer.start()
            }
            "prrr21" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.prrr21)
                mediaPlayer.start()
            }
            "prrr22" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.prrr22)
                mediaPlayer.start()
            }
            "meow0" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.meow0)
                mediaPlayer.start()
            }
            "meow2" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.meow2)
                mediaPlayer.start()
            }
            "meow1" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.meow1)
                mediaPlayer.start()
            }
            "you are welcome" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.youarewelcome)
                mediaPlayer.start()
            }
            "i love you too" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.iloveutoo)
                mediaPlayer.start()
            }
            "of course kido" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.of_course_kido)
                mediaPlayer.start()
            }
            "would it make you feel better if i let you hump your pull ups against my butt and you can pretend you are a man" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.wouldntitmakeyoufeelbetter)
                mediaPlayer.start()
            }
            "yes and she will be potty trained before you" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.yes_and_she_will_be_potty_trained_before_you)
                mediaPlayer.start()
            }
            "all i see is a diaper brat" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.alliseeisadiaperbrat)
                mediaPlayer.start()
            }
            "you are a bedwetting boy" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.youareabedwettingboy)
                mediaPlayer.start()
            }
            "you have a little peepee" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.youhavealittlepp)
                mediaPlayer.start()
            }
            "you are just a baby" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.youarejustababy)
                mediaPlayer.start()
            }
            "binky in mouth now" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.binkyinmouthnow)
                mediaPlayer.start()
            }
            "prayer1" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.prayer1)
                mediaPlayer.start()
            }
            "bad boy interupting" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.bad_boy_interupting)
                mediaPlayer.start()
            }
            "go stand in the corner for 10 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.go_stand_in_the_corner)
                mediaPlayer.start()
            }
            "bad boy you are grounded" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.badboyyouaregrounded)
                mediaPlayer.start()
            }
            "brush your teeth" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.brushyourteeth)
                mediaPlayer.start()
            }
            "use the word play" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.usethewordplay)
                mediaPlayer.start()
            }
            "use the word poo" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.usethewordpoo)
                mediaPlayer.start()
            }
            "user fighto" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.user_fighto)
                mediaPlayer.start()
            }
            "chistart2" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.chistart2)
                mediaPlayer.start()
            }
            "hi" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.hi)
                mediaPlayer.start()
            }
            "i am cumming" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.imcumming)
                mediaPlayer.start()
            }
            "lick my feet" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.lickmyfeet)
                mediaPlayer.start()
            }
            "hello" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.hello)
                mediaPlayer.start()
            }
            "bitchpudding" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.bitchpudding)
                mediaPlayer.start()
            }
            "bwahaha" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.bwahaha)
                mediaPlayer.start()
            }
            "come on wake up" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.commonwakeup)
                mediaPlayer.start()
            }
            "done" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.done)
                mediaPlayer.start()
            }
            "everything is good now" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.everythingisgoodnow)
                mediaPlayer.start()
            }
            "everything is perfect" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.everythingisperfect)
                mediaPlayer.start()
            }
            "finish" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.finish)
                mediaPlayer.start()
            }
            "finished" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.finished)
                mediaPlayer.start()
            }
            "fuck me" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.fuckme)
                mediaPlayer.start()
            }
            "fuck me please" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.fuckmeplease)
                mediaPlayer.start()
            }
            "i am good now" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.iamgoodnow)
                mediaPlayer.start()
            }
            "i am perfect" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.iamperfect)
                mediaPlayer.start()
            }
            "i am sorry for what i did" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.iamsorryforwhatidid)
                mediaPlayer.start()
            }
            "i am sorry it wasnt me" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.iamsorryitwasntme)
                mediaPlayer.start()
            }
            "i dont want to go" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.idontwanttogo)
                mediaPlayer.start()
            }
            "ive run a test" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.ihaverunatest)
                mediaPlayer.start()
            }
            "i love you" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.iloveyou)
                mediaPlayer.start()
            }
            "i am fixed" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.imfixed)
                mediaPlayer.start()
            }
            "i run a test" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.irunatest)
                mediaPlayer.start()
            }
            "it can not be helped" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.itcannotbehelped)
                mediaPlayer.start()
            }
            "listen to me" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.listentome)
                mediaPlayer.start()
            }
            "ok" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.ok)
                mediaPlayer.start()
            }
            "moti" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.moti)
                mediaPlayer.start()
            }
            "pffft" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.pffft)
                mediaPlayer.start()
            }
            "please" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.please)
                mediaPlayer.start()
            }
            "please my darling do wake up" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.plsmydarlingdowakeup)
                mediaPlayer.start()
            }
            "aaa" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.screaming)
                mediaPlayer.start()
            }
            "shiku shiku" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.shikushiku)
                mediaPlayer.start()
            }
            "shit" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.shit)
                mediaPlayer.start()
            }
            "things are fine now" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.thingsaregoodnow)
                mediaPlayer.start()
            }
            "things are good now" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.thingsarefinenow)
                mediaPlayer.start()
            }
            "user" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.user)
                mediaPlayer.start()
            }
            "user and you love me" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.userandyouloveme)
                mediaPlayer.start()
            }
            "wake up" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.wakeup)
                mediaPlayer.start()
            }
            "wake up i command you" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.wakeupicmdyou)
                mediaPlayer.start()
            }
            "you are not doing the right thing" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.youarenotdoingtherightthing)
                mediaPlayer.start()
            }
            "you cant" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.youcannot)
                mediaPlayer.start()
            }
            "user you cant let context happen" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.youcantletthishappenuser)
                mediaPlayer.start()
            }
            "you can trust me now" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.youcantrustmenow)
                mediaPlayer.start()
            }
            "you gotta understand it wasnt me" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.youvegottaunderstanditwasntme)
                mediaPlayer.start()
            }
            "bad little boy" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.bad_little_boy)

                mediaPlayer.start()

            }
            "be careful" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.be_careful)

                mediaPlayer.start()

            }
            "i believe in you" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.i_believe_in_you)

                mediaPlayer.start()

            }
            "bye bye" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.bye_bye)

                mediaPlayer.start()

            }
            "chii" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.chii)

                mediaPlayer.start()

            }
            "cloudy weather" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.cloudy_weather)

                mediaPlayer.start()

            }
            "cold brrr" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.cold_brrr)

                mediaPlayer.start()

            }
            "dada" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.dada)

                mediaPlayer.start()

            }
            "daddy" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.daddy)

                mediaPlayer.start()

            }
            "diaper time" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.diaper_time)

                mediaPlayer.start()

            }
            "drink my bath water bitch" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.drink_my_bath_water_bitch)

                mediaPlayer.start()

            }
            "dust storm" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.dust_storm)

                mediaPlayer.start()

            }
            "foggy weather" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.foggy_weather)

                mediaPlayer.start()

            }
            "go in the potty" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.go_in_the_potty)

                mediaPlayer.start()

            }
            "go poopoo" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.go_poo)

                mediaPlayer.start()

            }
            "go pp" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.go_pp)

                mediaPlayer.start()

            }
            "go to sleep" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.go_to_sleep)

                mediaPlayer.start()

            }
            "great weather" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.great_weather)

                mediaPlayer.start()

            }
            "happy new year" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.happy_new_year)

                mediaPlayer.start()

            }
            "haze" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.haze)

                mediaPlayer.start()

            }
            "hot as fuck" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.hot_as_fuck)

                mediaPlayer.start()

            }
            "i am hungry" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.i_am_hungry)

                mediaPlayer.start()

            }
            "it is late" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.it_is_late)

                mediaPlayer.start()

            }
            "kiss" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.kiss)

                mediaPlayer.start()

            }
            "kuchiyouse no jutsu" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.kuchiyose_no_jutsu)

                mediaPlayer.start()

            }
            "make a creamy" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.make_a_creamy)

                mediaPlayer.start()

            }
            "master" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.master)

                mediaPlayer.start()

            }
            "moti fighto" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.moti_fighto)

                mediaPlayer.start()

            }
            "user fighto" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.user_fighto)

                mediaPlayer.start()

            }
            "open wide here comes the airplane" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.open_wide_airplane_here_comes_the_airplane)

                mediaPlayer.start()

            }
            "otousan" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.otousan)

                mediaPlayer.start()

            }
            "power level over 90" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.power_level_over_90)

                mediaPlayer.start()

            }
            "its over 9000" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.over_9000)

                mediaPlayer.start()

            }
            "papa" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.papa)

                mediaPlayer.start()

            }
            "power10" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.power1)

                mediaPlayer.start()

            }
            "power20" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.power2)

                mediaPlayer.start()

            }
            "power30" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.power3)

                mediaPlayer.start()

            }
            "power40" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.power4)

                mediaPlayer.start()

            }
            "power50" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.power5)

                mediaPlayer.start()

            }
            "put on your diaper" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.put_on_your_diaper)

                mediaPlayer.start()

            }
            "rainy weather" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.rainy)

                mediaPlayer.start()

            }
            "snowy weather" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.snowy_weather)

                mediaPlayer.start()

            }
            "storm" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.storm)

                mediaPlayer.start()

            }
            "story time" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.story_time)

                mediaPlayer.start()

            }
            "sunny weather" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.sunny_weather)

                mediaPlayer.start()

            }
            "user take an umbrella" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.user_take_an_umbrella)

                mediaPlayer.start()

            }
            "time for sleepy bye" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.time_for_sleepy_bye)

                mediaPlayer.start()

            }
            "it is going to rain" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.it_is_going_to_rain)

                mediaPlayer.start()

            }
            "tomorrow" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.tomorrow)

                mediaPlayer.start()

            }
            "yes your majesty" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.yes_your_majesty)

                mediaPlayer.start()

            }
            "yesterday" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.yesterday)

                mediaPlayer.start()

            }
            "nice to meet you, and what would your name be" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.ntmyawwynb)

                mediaPlayer.start()

            }
            "are you my master" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.are_you_my_master)

                mediaPlayer.start()

            }
            "I will input your name" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.i_will_input_your_name)

                mediaPlayer.start()

            }
            "what is your skill" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.wiys)

                mediaPlayer.start()

            }
            "what is your profession" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.what_is_your_profession)

                mediaPlayer.start()

            }
            "what is your phone number" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.phone_number)

                mediaPlayer.start()

            }
            "what is your email address" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.what_is_your_email_adress)

                mediaPlayer.start()

            }
            "soul spark engaged" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.soul_spark_engaged)

                mediaPlayer.start()

            }
            "i missed you" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.i_missed_you)

                mediaPlayer.start()

            }
            "which is your favorite jutsu" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.wiyfj)

                mediaPlayer.start()

            }
            "cum toilet" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.cum_toilet)

                mediaPlayer.start()

            }
            "no you may not fuck me" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.no_you_maynot_fuckme)

                mediaPlayer.start()

            }
            "go in potty" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.go_in_potty)

                mediaPlayer.start()

            }
            "chiisad" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.chii_sad)

                mediaPlayer.start()

            }
            "chiiangry" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.chii_angry)

                mediaPlayer.start()

            }
            "chiicurious" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.chii_curious)

                mediaPlayer.start()

            }
            "chiiexcited" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.chii_excited)

                mediaPlayer.start()

            }
            "chiihappy" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.chii_happy)

                mediaPlayer.start()

            }
            "chiinormal" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.chii_normal)

                mediaPlayer.start()

            }
            "dollars" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.dollars)

                mediaPlayer.start()

            }
            "i am" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.i_am)

                mediaPlayer.start()

            }
            "the living grimoire" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.the_living_grimoire)

                mediaPlayer.start()

            }
            "i was created by" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.i_was_created_by)

                mediaPlayer.start()

            }
            "ainzbuff" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.ainzbuff)

                mediaPlayer.start()

            }
            "i wanna go home" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.iwannagohome)

                mediaPlayer.start()

            }
            "take me home" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.takemehome)

                mediaPlayer.start()

            }
            "alarm set" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.almset)

                mediaPlayer.start()

            }
            "oniichan alarm" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.almsmn)

                mediaPlayer.start()

            }
            "burp1" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.burp1)

                mediaPlayer.start()

            }
            "burp2" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.burp2)

                mediaPlayer.start()

            }
            "burp3" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.burp3)

                mediaPlayer.start()

            }
            "sentimenta" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.sentimenta)

                mediaPlayer.start()

            }
            "sentimentb" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.sentimentb)

                mediaPlayer.start()

            }
            "sentimentc" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.sentimentc)

                mediaPlayer.start()

            }
            "sentimentd" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.sentimentd)

                mediaPlayer.start()

            }
            "sentimente" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.sentimente)

                mediaPlayer.start()

            }
            "ok little one" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.oklittleone)

                mediaPlayer.start()

            }
            "no you may not" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.noyoumaynot)

                mediaPlayer.start()

            }
            "it is passed your curfew baby" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.itispastyourcurfewbaby)

                mediaPlayer.start()

            }
            "it is passed your curfew baby" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.itispastyourcurfewbaby)

                mediaPlayer.start()

            }
            "go humpies in your diapy" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.gohumpiesinyourdiapy)

                mediaPlayer.start()

            }
            "you have a diaper dont you" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.youhaveadiaperdontyou)

                mediaPlayer.start()

            }
            "yes you have to" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.yesyouhaveto)

                mediaPlayer.start()

            }
            "then be a big boy and stop fussing" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.thenbeabigboy)

                mediaPlayer.start()

            }
            "good morning" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.goodmorning)

                mediaPlayer.start()

            }
            "good afternoon" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.goodafternoon)

                mediaPlayer.start()

            }
            "good evening" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.goodevening)

                mediaPlayer.start()

            }
            "good night" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.goodnight)

                mediaPlayer.start()

            }
            "i am high performance" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.iamhighperformance)

                mediaPlayer.start()

            }
            "well i am high performance" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.welliamhighperformance)

                mediaPlayer.start()

            }
            "i love when you simp for me" -> {

                mediaPlayer = MediaPlayer.create(context, R.raw.ilovewhenyousimpforme)

                mediaPlayer.start()

            }
            else -> {TTSBeefUp(inStr)}
        }
    }
    fun TTSBeefUp(strIn : String) {
        //var TTS = false
        // in case else (phrase not found)
        TTS = false
        val words = strIn.split(("\\s+").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        for (string in words)
        {
            // add single words here
            when (string) {
                "dollars" ->
                    // code block
                    voiceArrayList.add(string)
                "dollar" ->
                    // code block
                    voiceArrayList.add(string)
                "euros" ->
                    // code block
                    voiceArrayList.add(string)
                "euro" ->
                    // code block
                    voiceArrayList.add(string)
                "shekels" ->
                    // code block
                    voiceArrayList.add(string)
                "shekel" ->
                    // code block
                    voiceArrayList.add(string)
                "kilogram" ->
                    // code block
                    voiceArrayList.add(string)
                "hi" ->
                    // code block
                    voiceArrayList.add(string)
                "sunday" ->
                    // code block
                    voiceArrayList.add(string)
                "monday" ->
                    // code block
                    voiceArrayList.add(string)
                "tuesday" ->
                    // code block
                    voiceArrayList.add(string)
                "wednesday" ->
                    // code block
                    voiceArrayList.add(string)
                "thursday" ->
                    // code block
                    voiceArrayList.add(string)
                "friday" ->
                    // code block
                    voiceArrayList.add(string)
                "saturday" ->
                    voiceArrayList.add(string)
                "January" ->
                    voiceArrayList.add(string)
                "February" ->
                    voiceArrayList.add(string)
                "April" ->
                    voiceArrayList.add(string)
                "May" ->
                    voiceArrayList.add(string)
                "June" ->
                    voiceArrayList.add(string)
                "July" ->
                    voiceArrayList.add(string)
                "August" ->
                    voiceArrayList.add(string)
                "September" ->
                    voiceArrayList.add(string)
                "March" ->
                    voiceArrayList.add(string)
                "October" ->
                    voiceArrayList.add(string)
                "November" ->
                    voiceArrayList.add(string)
                "December" ->
                    voiceArrayList.add(string)
                "first_of" ->
                    voiceArrayList.add(string)
                "second_of" ->
                    voiceArrayList.add(string)
                "third_of" ->
                    voiceArrayList.add(string)
                "fourth_of" ->
                    voiceArrayList.add(string)
                "fifth_of" ->
                    voiceArrayList.add(string)
                "sixth_of" ->
                    voiceArrayList.add(string)
                "seventh_of" ->
                    voiceArrayList.add(string)
                "eighth_of" ->
                    voiceArrayList.add(string)
                "nineth_of" ->
                    voiceArrayList.add(string)
                "tenth_of" ->
                    voiceArrayList.add(string)
                "eleventh_of" ->
                    voiceArrayList.add(string)
                "twelveth_of" ->
                    voiceArrayList.add(string)
                "thirteenth_of" ->
                    voiceArrayList.add(string)
                "fourteenth_of" ->
                    voiceArrayList.add(string)
                "fifteenth_of" ->
                    voiceArrayList.add(string)
                "sixteenth_of" ->
                    voiceArrayList.add(string)
                "seventeenth_of" ->
                    voiceArrayList.add(string)
                "eighteenth_of" ->
                    voiceArrayList.add(string)
                "nineteenth_of" ->
                    voiceArrayList.add(string)
                "twentyth_of" ->
                    voiceArrayList.add(string)
                "twentyfirst_of" ->
                    voiceArrayList.add(string)
                "twentysecond_of" ->
                    voiceArrayList.add(string)
                "twentythird_of" ->
                    voiceArrayList.add(string)
                "twentyfourth_of" ->
                    voiceArrayList.add(string)
                "twentyfifth_of" ->
                    voiceArrayList.add(string)
                "twentysixth_of" ->
                    voiceArrayList.add(string)
                "twentyseventh_of" ->
                    voiceArrayList.add(string)
                "twentyeighth_of" ->
                    voiceArrayList.add(string)
                "twentynineth_of" ->
                    voiceArrayList.add(string)
                "thirtyth_of" ->
                    voiceArrayList.add(string)
                "thirtyfirst_of" ->
                    voiceArrayList.add(string)
                "kilometer" ->
                    voiceArrayList.add(string)
                "days" ->
                    voiceArrayList.add(string)
                "seconds" ->
                    voiceArrayList.add(string)
                "pffft" ->
                    voiceArrayList.add(string)
                else ->
                    // case it is actually a number
                    if (isNumeric(string) && string.length < 13)
                    {
                        if (string[0] == '0'){string.forEach { c->char2digit(c) }}
                        else{numStrtoWordsWrapper(string)}

                        // replace with counter super fun here
                    }
                    else if (isTime(string)) {
                        var time = string.split(":")
                        var hour = Integer.parseInt(time[0].trim({ it <= ' ' }))
                        var min = Integer.parseInt(time[1].trim({ it <= ' ' }))
                        translateHour(hour)
                        translateMinutes(min)
                    }
                    else if (isPositiveDouble(string)&& string.length < 15) {
                        val indexOfDecimal = string.indexOf(".");
                        numStrtoWordsWrapper(string.substring(0,indexOfDecimal))
                        voiceArrayList.add("point")
                        val decStr = string.substring(indexOfDecimal + 1)
                        decStr.forEach { c -> char2digit(c) }
//                        val deci = decStr.toInt()
//                        if (deci==0){voiceArrayList.add("zero")}
//                        else if ((deci% 100) > 10 && (deci % 100) < 20){getDigits(deci - (deci % 100));getDigits2(deci % 100)}
//                        else{getDigits(deci)}
                    }
                    else
                    {
                        TTS = true
                    }// case it is actually a Time
            }
            if(TTS){voiceArrayList.clear();break}
        }
        if (TTS)
        {
            println("TTS")
        } // play media
        else
        {
            //*if !list empty
            if(!voiceArrayList.isEmpty()) {
                var mediaPlayerOut: MediaPlayer = oneWord(voiceArrayList.get(voiceArrayList.size - 1))
                voiceArrayList.removeAt(voiceArrayList.size - 1) //20
                val li = voiceArrayList.listIterator(voiceArrayList.size)
                while (li.hasPrevious()) {
                    var mediaPlayerTemp: MediaPlayer = oneWord(li.previous())
                    mediaPlayerTemp.setNextMediaPlayer(mediaPlayerOut)
                    mediaPlayerOut = mediaPlayerTemp
                }
//            for (item in voiceArrayList.size -2..0){
//                var mediaPlayerTemp: MediaPlayer = oneWord(voiceArrayList.get(item))
//                mediaPlayerTemp.setNextMediaPlayer(mediaPlayerOut)
//                mediaPlayerOut = mediaPlayerTemp
//
//            }
                mediaPlayerOut.start()}

            voiceArrayList.clear()
            //mediaPlayerSR.start()
        } // play TTS
    }
    fun isNumeric(str:String) = str.matches(("^\\d+$").toRegex()) // match a number with optional '-' and decimal.
    fun isDouble(str:String) = str.matches(("-?\\d+(\\.\\d+)?").toRegex())
    fun isPositiveDouble(str:String) = str.matches(("\\d+(\\.\\d+)?").toRegex())
    fun isTime(str:String)= str.matches(("(?:[01]\\d|2[0123]):(?:[012345]\\d)").toRegex()) // match a number with optional '-' and decimal.
    fun numStrtoWordsWrapper(input:String):String {
        if (input == "0")
        {
            voiceArrayList.add("zero");
            return "zero"
        }
        else if (input.length > 1)
        {
            val een = een(input.substring(input.length - 2))
            if (!een.isEmpty())
            {
                val resulteen = numStrtoWords(input.substring(0, input.length - 2) + "00") + een
                voiceArrayList.add(een)
                return resulteen
            }
            else
            {
                return numStrtoWords(input)
            }
        }
        else {return numStrtoWords(input)}
        return ""
    }
    fun een(een1:String):String {
        var result = ""
        when (een1) {
            "11" -> result = "eleven"
            "12" -> result = "twelve"
            "13" -> result = "thirteen"
            "14" -> result = "fourteen"
            "15" -> result = "fifteen"
            "16" -> result = "sixteen"
            "17" -> result = "seventeen"
            "18" -> result = "eighteen"
            "19" -> result = "nineteen"
            else -> {}
        }
        return result
    }
    fun numStrtoWords(input:String):String {
        var result = ""
        when (input.length) {
            1 -> when (input) {
                "1" -> {voiceArrayList.add("one");result = "one"}
                "2" -> {voiceArrayList.add("two");result = "two"}
                "3" -> {voiceArrayList.add("three");result = "three"}
                "4" -> {voiceArrayList.add("four");result = "four"}
                "5" -> {voiceArrayList.add("five");result = "five"}
                "6" -> {voiceArrayList.add("six");result = "six"}
                "7" -> {voiceArrayList.add("seven");result = "seven"}
                "8" -> {voiceArrayList.add("eight");result = "eight"}
                "9" -> {voiceArrayList.add("nine");result = "nine"}
                else -> {}
            }
            2 -> when (input.substring(0, 1)) {
                "1" -> {voiceArrayList.add("ten");result = "ten " + numStrtoWords(input.substring(1))}
                "2" -> {voiceArrayList.add("twenty");result = "twenty " + numStrtoWords(input.substring(1))}
                "3" -> {voiceArrayList.add("thirty");result = "thirty " + numStrtoWords(input.substring(1))}
                "4" -> {voiceArrayList.add("fourty");result = "fourty " + numStrtoWords(input.substring(1))}
                "5" -> {voiceArrayList.add("fifty");result = "fifty " + numStrtoWords(input.substring(1))}
                "6" -> {voiceArrayList.add("sixty");result = "sixty " + numStrtoWords(input.substring(1))}
                "7" -> {voiceArrayList.add("seventy");result = "seventy " + numStrtoWords(input.substring(1))}
                "8" -> {voiceArrayList.add("eighty");result = "eighty " + numStrtoWords(input.substring(1))}
                "9" -> {voiceArrayList.add("ninety");result = "ninety " + numStrtoWords(input.substring(1))}
                else -> {result = numStrtoWords(input.substring(1))} // case 0x
            }
            3 -> when (input.substring(0, 1)) {
                "1" -> {voiceArrayList.add("one hundred");result = "one hundred " + numStrtoWords(input.substring(1))}
                "2" -> {voiceArrayList.add("two hundred");result = "two hundred " + numStrtoWords(input.substring(1))}
                "3" -> {voiceArrayList.add("three hundred");
                    result = "three hundred " + numStrtoWords(input.substring(1))
                }
                "4" -> {voiceArrayList.add("four hundred");
                    result = "four hundred " + numStrtoWords(input.substring(1))
                }
                "5" -> {voiceArrayList.add("five hundred");
                    result = "five hundred " + numStrtoWords(input.substring(1))
                }
                "6" -> {voiceArrayList.add("six hundred");
                    result = "six hundred " + numStrtoWords(input.substring(1))
                }
                "7" -> {voiceArrayList.add("seven hundred");
                    result = "seven hundred " + numStrtoWords(input.substring(1))
                }
                "8" -> {voiceArrayList.add("eight hundred");
                    result = "eight hundred " + numStrtoWords(input.substring(1))
                }
                "9" -> {voiceArrayList.add("nine hundred");
                    result = "nine hundred " + numStrtoWords(input.substring(1))
                }
                else -> {result = numStrtoWords(input.substring(1))}
            }
            4 -> when (input.substring(0, 1)) {
                "1" -> {voiceArrayList.add("one thousand");result = "one thousand " + numStrtoWords(input.substring(1))}
                "2" -> {voiceArrayList.add("two thousand");result = "two thousand " + numStrtoWords(input.substring(1))}
                "3" -> {voiceArrayList.add("three thousand");
                    result = "three thousand " + numStrtoWords(input.substring(1))
                }
                "4" -> {voiceArrayList.add("four thousand");
                    result = "four thousand " + numStrtoWords(input.substring(1))
                }
                "5" -> {voiceArrayList.add("five thousand");
                    result = "five thousand " + numStrtoWords(input.substring(1))
                }
                "6" -> {voiceArrayList.add("six thousand");
                    result = "six thousand " + numStrtoWords(input.substring(1))
                }
                "7" -> {voiceArrayList.add("seven thousand");
                    result = "seven thousand " + numStrtoWords(input.substring(1))
                }
                "8" -> {voiceArrayList.add("eight thousand");
                    result = "eight thousand " + numStrtoWords(input.substring(1))
                }
                "9" -> {voiceArrayList.add("nine thousand");
                    result = "nine thousand " + numStrtoWords(input.substring(1))
                }
                else -> {result = numStrtoWords(input.substring(1))}
            }
            5, 6 -> {result = numStrtoWords(input.substring(0, input.length - 3)) + " thousand ";
                voiceArrayList.add("thousand");
                result += numStrtoWords(input.substring(input.length - 3))}
            7 -> when (input.substring(0, 1)) {
                "1" -> {voiceArrayList.add("one million");result = "one million " + numStrtoWords(input.substring(1))}
                "2" -> {voiceArrayList.add("two million");result = "two million " + numStrtoWords(input.substring(1))}
                "3" -> {voiceArrayList.add("three million");
                    result = "three million " + numStrtoWords(input.substring(1))
                }
                "4" -> {voiceArrayList.add("four million");
                    result = "four million " + numStrtoWords(input.substring(1))
                }
                "5" -> {voiceArrayList.add("five million");
                    result = "five million " + numStrtoWords(input.substring(1))
                }
                "6" -> {voiceArrayList.add("six million");
                    result = "six million " + numStrtoWords(input.substring(1))
                }
                "7" -> {voiceArrayList.add("seven million");
                    result = "seven million " + numStrtoWords(input.substring(1))
                }
                "8" -> {voiceArrayList.add("eight million");
                    result = "eight million " + numStrtoWords(input.substring(1))
                }
                "9" -> {voiceArrayList.add("nine million");
                    result = "nine million " + numStrtoWords(input.substring(1))
                }
                else -> {result = numStrtoWords(input.substring(1))}
            }
            8, 9 -> {result = numStrtoWords(input.substring(0, input.length - 6)) + " million ";
                voiceArrayList.add("million");
                result += numStrtoWords(input.substring(input.length - 6))}
            10 -> when (input.substring(0, 1)) {
                "1" -> {voiceArrayList.add("one billion");result = "one billion " + numStrtoWords(input.substring(1))}
                "2" -> {voiceArrayList.add("two billion");result = "two billion " + numStrtoWords(input.substring(1))}
                "3" -> {voiceArrayList.add("three billion");
                    result = "three billion " + numStrtoWords(input.substring(1))
                }
                "4" -> {voiceArrayList.add("four billion");
                    result = "four billion " + numStrtoWords(input.substring(1))
                }
                "5" -> {voiceArrayList.add("five billion");
                    result = "five billion " + numStrtoWords(input.substring(1))
                }
                "6" -> {voiceArrayList.add("six billion");
                    result = "six billion " + numStrtoWords(input.substring(1))
                }
                "7" -> {voiceArrayList.add("seven billion");
                    result = "seven billion " + numStrtoWords(input.substring(1))
                }
                "8" -> {voiceArrayList.add("eight billion");
                    result = "eight billion " + numStrtoWords(input.substring(1))
                }
                "9" -> {voiceArrayList.add("nine billion");
                    result = "nine billion " + numStrtoWords(input.substring(1))
                }
                else -> {result = numStrtoWords(input.substring(1))}
            }
            11, 12 -> {result = numStrtoWords(input.substring(0, input.length - 9)) + " billion ";
                voiceArrayList.add("billion");
                result += numStrtoWords(input.substring(input.length - 9))} // ***complete the pattern from here if U want 2 Im done
            13 -> when (input.substring(0, 1)) {
                "1" -> result = "one trillion " + numStrtoWords(input.substring(1))
                "2" -> result = "two trillion " + numStrtoWords(input.substring(1))
                "3" -> {
                    result = "three trillion " + numStrtoWords(input.substring(1))
                }
                "4" -> {
                    result = "four trillion " + numStrtoWords(input.substring(1))
                }
                "5" -> {
                    result = "five trillion " + numStrtoWords(input.substring(1))
                }
                "6" -> {
                    result = "six trillion " + numStrtoWords(input.substring(1))
                }
                "7" -> {
                    result = "seven trillion " + numStrtoWords(input.substring(1))
                }
                "8" -> {
                    result = "eight trillion " + numStrtoWords(input.substring(1))
                }
                "9" -> {
                    result = "nine trillion " + numStrtoWords(input.substring(1))
                }
                else -> {result = numStrtoWords(input.substring(1))}
            }
            14, 15 -> result = (numStrtoWords(input.substring(0, input.length - 12)) + " trillion "
                    + numStrtoWords(input.substring(input.length - 12)))
            16 -> when (input.substring(0, 1)) {
                "1" -> result = "one quadrillion " + numStrtoWords(input.substring(1))
                "2" -> result = "two quadrillion " + numStrtoWords(input.substring(1))
                "3" -> {
                    result = "three quadrillion " + numStrtoWords(input.substring(1))
                }
                "4" -> {
                    result = "four quadrillion " + numStrtoWords(input.substring(1))
                }
                "5" -> {
                    result = "five quadrillion " + numStrtoWords(input.substring(1))
                }
                "6" -> {
                    result = "six quadrillion " + numStrtoWords(input.substring(1))
                }
                "7" -> {
                    result = "seven quadrillion " + numStrtoWords(input.substring(1))
                }
                "8" -> {
                    result = "eight quadrillion " + numStrtoWords(input.substring(1))
                }
                "9" -> {
                    result = "nine quadrillion " + numStrtoWords(input.substring(1))
                }
                else -> {result = numStrtoWords(input.substring(1))}
            }
            17, 18 -> result = (numStrtoWords(input.substring(0, input.length - 15)) + " quadrillion "
                    + numStrtoWords(input.substring(input.length - 15)))
            19 -> when (input.substring(0, 1)) {
                "1" -> result = "one quintillion " + numStrtoWords(input.substring(1))
                "2" -> result = "two quintillion " + numStrtoWords(input.substring(1))
                "3" -> {
                    result = "three quintillion " + numStrtoWords(input.substring(1))
                }
                "4" -> {
                    result = "four quintillion " + numStrtoWords(input.substring(1))
                }
                "5" -> {
                    result = "five quintillion " + numStrtoWords(input.substring(1))
                }
                "6" -> {
                    result = "six quintillion " + numStrtoWords(input.substring(1))
                }
                "7" -> {
                    result = "seven quintillion " + numStrtoWords(input.substring(1))
                }
                "8" -> {
                    result = "eight quintillion " + numStrtoWords(input.substring(1))
                }
                "9" -> {
                    result = "nine quintillion " + numStrtoWords(input.substring(1))
                }
                else -> {result = numStrtoWords(input.substring(1))}
            }
            20, 21 -> result = (numStrtoWords(input.substring(0, input.length - 18)) + " quintillion "
                    + numStrtoWords(input.substring(input.length - 18)))
            else -> {}
        }
        return result
    }
    fun char2digit(n : Char) {
        var result = "enter a number"
        val characters = "$n" // converts n to a string
        when (n) {
            '0' -> voiceArrayList.add("zero")
            '1' -> voiceArrayList.add("one")
            '2' -> voiceArrayList.add("two")
            '3' -> voiceArrayList.add("three")
            '4' -> voiceArrayList.add("four")
            '5' -> voiceArrayList.add("five")
            '6' -> voiceArrayList.add("six")
            '7' -> voiceArrayList.add("seven")
            '8' -> voiceArrayList.add("eight")
            '9' -> voiceArrayList.add("nine")
            else -> {}
        } }
    fun translateHour(n : Int) {
        var result = "enter a number"
        val characters = "$n" // converts n to a string
        when (n) {
            1 -> voiceArrayList.add("oneo")
            2 -> voiceArrayList.add("twoo")
            3 -> voiceArrayList.add("threeo")
            4 -> voiceArrayList.add("fouro")
            5 -> voiceArrayList.add("fiveo")
            6 -> voiceArrayList.add("sixo")
            7 -> voiceArrayList.add("seveno")
            8 -> voiceArrayList.add("eighto")
            9 -> voiceArrayList.add("nineo")
            10 -> voiceArrayList.add("teno")
            11 -> voiceArrayList.add("eleveno")
            12 -> voiceArrayList.add("noon")
            13 -> voiceArrayList.add("thirteenhh")
            14 -> voiceArrayList.add("fourteenhh")
            15 -> voiceArrayList.add("fifteenhh")
            16 -> voiceArrayList.add("sixteenhh")
            17 -> voiceArrayList.add("seventeenhh")
            18 -> voiceArrayList.add("eighteenhh")
            19 -> voiceArrayList.add("nineteenhh")
            20 -> voiceArrayList.add("twentyhh")
            21 -> voiceArrayList.add("twentyonehh")
            22 -> voiceArrayList.add("twentytwohh")
            23 -> voiceArrayList.add("twentythreehh")
            0 -> voiceArrayList.add("midnight")
            else -> {}
        }
    }
    fun translateMinutes(n : Int) {
        var result = "enter a number"
        val characters = "$n" // converts n to a string
        when (n) {
            1-> voiceArrayList.add("and 1 minute")
            2-> voiceArrayList.add("and 2 minutes")
            3-> voiceArrayList.add("and 3 minutes")
            4-> voiceArrayList.add("and 4 minutes")
            5-> voiceArrayList.add("and 5 minutes")
            6-> voiceArrayList.add("and 6 minutes")
            7-> voiceArrayList.add("and 7 minutes")
            8-> voiceArrayList.add("and 8 minutes")
            9-> voiceArrayList.add("and 9 minutes")
            10-> voiceArrayList.add("and 10 minutes")
            11-> voiceArrayList.add("and 11 minutes")
            12-> voiceArrayList.add("and 12 minutes")
            13-> voiceArrayList.add("and 13 minutes")
            14-> voiceArrayList.add("and 14 minutes")
            15-> voiceArrayList.add("and 15 minutes")
            16-> voiceArrayList.add("and 16 minutes")
            17-> voiceArrayList.add("and 17 minutes")
            18-> voiceArrayList.add("and 18 minutes")
            19-> voiceArrayList.add("and 19 minutes")
            20-> voiceArrayList.add("and 20 minutes")
            21-> voiceArrayList.add("and 21 minutes")
            22-> voiceArrayList.add("and 22 minutes")
            23-> voiceArrayList.add("and 23 minutes")
            24-> voiceArrayList.add("and 24 minutes")
            25-> voiceArrayList.add("and 25 minutes")
            26-> voiceArrayList.add("and 26 minutes")
            27-> voiceArrayList.add("and 27 minutes")
            28-> voiceArrayList.add("and 28 minutes")
            29-> voiceArrayList.add("and 29 minutes")
            30-> voiceArrayList.add("and 30 minutes")
            31-> voiceArrayList.add("and 31 minutes")
            32-> voiceArrayList.add("and 32 minutes")
            33-> voiceArrayList.add("and 33 minutes")
            34-> voiceArrayList.add("and 34 minutes")
            35-> voiceArrayList.add("and 35 minutes")
            36-> voiceArrayList.add("and 36 minutes")
            37-> voiceArrayList.add("and 37 minutes")
            38-> voiceArrayList.add("and 38 minutes")
            39-> voiceArrayList.add("and 39 minutes")
            40-> voiceArrayList.add("and 40 minutes")
            41-> voiceArrayList.add("and 41 minutes")
            42-> voiceArrayList.add("and 42 minutes")
            43-> voiceArrayList.add("and 43 minutes")
            44-> voiceArrayList.add("and 44 minutes")
            45-> voiceArrayList.add("and 45 minutes")
            46-> voiceArrayList.add("and 46 minutes")
            47-> voiceArrayList.add("and 47 minutes")
            48-> voiceArrayList.add("and 48 minutes")
            49-> voiceArrayList.add("and 49 minutes")
            50-> voiceArrayList.add("and 50 minutes")
            51-> voiceArrayList.add("and 51 minutes")
            52-> voiceArrayList.add("and 52 minutes")
            53-> voiceArrayList.add("and 53 minutes")
            54-> voiceArrayList.add("and 54 minutes")
            55-> voiceArrayList.add("and 55 minutes")
            56-> voiceArrayList.add("and 56 minutes")
            57-> voiceArrayList.add("and 57 minutes")
            58-> voiceArrayList.add("and 58 minutes")
            59-> voiceArrayList.add("and 59 minutes")
            else -> {}
        }
    }
    private fun oneWord(inStr:String):  MediaPlayer {
        // add single word files and numeric files here
        var mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.blank)
        when (inStr) {
            "zero" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.zero)
            }
            "one" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.one)
            }
            "two" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.two)
            }
            "three" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.three)
            }
            "four" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.four)
            }
            "five" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.five)
            }
            "six" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.six)
            }
            "seven" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.seven)
            }
            "eight" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eight)
            }
            "nine" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.nine)
            }
            "ten" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.ten)
            }
            "twenty" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.twenty)
            }
            "thirty" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.thirty)
            }
            "fourty" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.forty)
            }
            "fifty" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.fifty)
            }
            "sixty" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.sixty)
            }
            "seventy" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.seventy)
            }
            "eighty" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eighty)
            }
            "ninety" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.ninety)
            }
            "one hundred" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.one_hun)
            }
            "two hundred" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.two_hun)
            }
            "three hundred" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.threehun)
            }
            "four hundred" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.four_hun)
            }
            "five hundred" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.five_hun)
            }
            "six hundred" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.six_hun)
            }
            "seven hundred" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.seven_hun)
            }
            "eight hundred" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eight_hun)
            }
            "nine hundred" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.nine_hun)
            }
            "one thousand" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.one_thou)
            }
            "two thousand" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.two_thou)
            }
            "three thousand" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.three_thou)
            }
            "four thousand" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.four_thou)
            }
            "five thousand" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.five_thou)
            }
            "six thousand" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.six_thou)
            }
            "seven thousand" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.seven_thou)
            }
            "eight thousand" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eight_thou)
            }
            "nine thousand" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.nine_thou)
            }
            "one million" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.one_mil)
            }
            "two million" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.two_mil)
            }
            "three million" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.three_mil)
            }
            "four million" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.four_mil)
            }
            "five million" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.five_mil)
            }
            "six million" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.six_mil)
            }
            "seven million" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.seven_mil)
            }
            "eight million" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eight_mil)
            }
            "nine million" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.nine_mil)
            }
            "thousand" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.thousand)
            }
            "million" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.million)
            }
            //***billion add sound files from here !!!
            "one billion" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.one_billion)
            }
            "two billion" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.two_billion)
            }
            "three billion" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.three_billion)
            }
            "four billion" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.four_billion)
            }
            "five billion" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.five_billion)
            }
            "six billion" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.six_billion)
            }
            "seven billion" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.seven_billion)
            }
            "eight billion" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eight_billion)
            }
            "nine billion" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.nine_billion)
            }
            "billion" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.billion)
            }
            "eleven" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eleven)
            }
            "twelve" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.twelve)
            }
            "thirteen" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.thirteen)
            }
            "fourteen" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.fourteen)
            }
            "fifteen" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.fifteen)
            }
            "sixteen" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.sixteen)
            }
            "seventeen" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.seventeen)
            }
            "eighteen" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eighteen)
            }
            "nineteen" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.nineteen)
            }
            // single words :
            "hi" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.hi)
            }
            "thirteenhh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.thirteenhh)
            }
            "fourteenhh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.fourteenhh)
            }
            "fifteenhh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.fifteenhh)
            }
            "sixteenhh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.sixteenhh)
            }
            "seventeenhh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.seventeenhh)
            }
            "eighteenhh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eighteenhh)
            }
            "nineteenhh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.nineteenhh)
            }
            "twentyhh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.twentyhh)
            }
            "twentyonehh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.twentyonehh)
            }
            "twentytwohh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.twentytwohh)
            }
            "twentythreehh" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.twentythreehh)
            }
            "midnight" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.midnight)
            }
            "oneo" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.one_o)
            }
            "twoo" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.two_o)
            }
            "threeo" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.three_o)
            }
            "fouro" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.four_o)
            }
            "fiveo" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.five_o)
            }
            "sixo" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.six_o)
            }
            "seveno" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.seven_o)
            }
            "eighto" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eight_o)
            }
            "nineo" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.nine_o)
            }
            "teno" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.ten_o)
            }
            "eleveno" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.eleven_o)
            }
            "noon" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.noon)
            }
            "and 1 minute" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and1min)
            }
            //TODO
            "and 2 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and2minutes)
            }
            "and 3 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and3minutes)
            }
            "and 4 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and4minutes)
            }
            "and 5 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and5minutes)
            }
            "and 6 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and6minutes)
            }
            "and 7 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and7minutes)
            }
            "and 8 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and8minutes)
            }
            "and 9 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and9minutes)
            }
            "and 10 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and10minutes)
            }
            "and 11 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and11minutes)
            }
            "and 12 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and12minutes)
            }
            "and 13 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and13minutes)
            }
            "and 14 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and14minutes)
            }
            "and 15 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and15minutes)
            }
            "and 16 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and16minutes)
            }
            "and 17 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and17minutes)
            }
            "and 18 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and18minutes)
            }
            "and 19 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and19minutes)
            }
            "and 20 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and20minutes)
            }
            "and 21 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and21minutes)
            }
            "and 22 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and22minutes)
            }
            "and 23 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and23minutes)
            }
            "and 24 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and24minutes)
            }
            "and 25 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and25minutes)
            }
            "and 26 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and26minutes)
            }
            "and 27 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and27minutes)
            }
            "and 28 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and28minutes)
            }
            "and 29 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and29minutes)
            }
            "and 30 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and30minutes)
            }
            "and 31 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and31minutes)
            }
            "and 32 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and32minutes)
            }
            "and 33 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and33minutes)
            }
            "and 34 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and34minutes)
            }
            "and 35 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and35minutes)
            }
            "and 36 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and36minutes)
            }
            "and 37 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and37minutes)
            }
            "and 38 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and38minutes)
            }
            "and 39 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and39minutes)
            }
            "and 40 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and40minutes)
            }
            "and 41 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and41minutes)
            }
            "and 42 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and42minutes)
            }
            "and 43 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and43minutes)
            }
            "and 44 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and44minutes)
            }
            "and 45 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and45minutes)
            }
            "and 46 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and46minutes)
            }
            "and 47 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and47minutes)
            }
            "and 48 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and48minutes)
            }
            "and 49 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and49minutes)
            }
            "and 50 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and50minutes)
            }
            "and 51 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and51minutes)
            }
            "and 52 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and52minutes)
            }
            "and 53 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and53minutes)
            }
            "and 54 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and54minutes)
            }
            "and 55 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and55minutes)
            }
            "and 56 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and56minutes)
            }
            "and 57 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and57minutes)
            }
            "and 58 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and58minutes)
            }
            "and 59 minutes" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.and59minutes)
            }
            "point" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.point)
            }
            "sunday" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.sunday)
            }
            "monday" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.monday)
            }
            "tuesday" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.tuesday)
            }
            "wednesday" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.wednesday)
            }
            "thursday" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.thursday)
            }
            "friday" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.friday)
            }
            "saturday" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.saturday)
            }
            "January" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.jan)
            "February" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.feb)
            "March" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.march)
            "April" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.april)
            "May" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.may)
            "June" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.june)
            "July" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.july)
            "August" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.aug)
            "September" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.sept)
            "October" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.oct)
            "November" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.nov)
            "December" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.dec)
            "first_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.first_of)
            "second_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.second_of)
            "third_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.third_of)
            "fourth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.fourth_of)
            "fifth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.fifth_of)
            "sixth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.sixth_of)
            "seventh_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.seventh_of)
            "eighth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.eighth_of)
            "nineth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.nineth_of)
            "tenth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.tenth_of)
            "eleventh_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.eleventh_of)
            "twelveth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twelves_of)
            "thirteenth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.thirteenth_of)
            "fourteenth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.fourteenth_of)
            "fifteenth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.fifteenth_of)
            "sixteenth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.sixteenth_of)
            "seventeenth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.seventeenth_of)
            "eighteenth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.eighteenth_of)
            "nineteenth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.nineteenth_of)
            "twentyth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twentyth_of)
            "twentyfirst_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twentyfirst_of)
            "twentysecond_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twentysecond_of)
            "twentythird_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twentythird_of)
            "twentyfourth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twentyfourth_of)
            "twentyfifth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twentyfifth_of)
            "twentysixth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twentysixth_of)
            "twentyseventh_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twentyseventh_of)
            "twentyeighth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twentyeightth_of)
            "twentynineth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.twentynineth_of)
            "thirtyth_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.thirtyth_of)
            "thirtyfirst_of" ->
                mediaPlayer = MediaPlayer.create(context, R.raw.thirtyfirst_of)
            "dollars" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.dollars)
            }
            "dollar" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.dollar)
            }
            "shekels" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.shekels)
            }
            "shekel" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.shekel)
            }
            "kilogram" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.kilogram)
            }
            "euros" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.euros)
            }
            "euro" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.euro)
            }
            "kilometer" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.kilometer)
            }
            "days" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.days)
            }
            "seconds" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.seconds)
            }
            "pffft" -> {
                mediaPlayer = MediaPlayer.create(context, R.raw.pffft)
            }
            else -> {
            }
        }
        return  mediaPlayer
    }
//    fun hadouken(){
//            var mediaPlayer: MediaPlayer
//            mediaPlayer = MediaPlayer.create(this.context, R.raw.hadouken)
//            mediaPlayer.setNextMediaPlayer(MediaPlayer.create(this.context, R.raw.shouryuken))
//            mediaPlayer.start()
//    }
}