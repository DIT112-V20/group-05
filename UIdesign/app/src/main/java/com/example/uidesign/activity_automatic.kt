package com.example.uidesign

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class activity_automatic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_automatic)

        val data : Data = Data(applicationContext)


        val distanceLeft =findViewById(R.id.DistanceLeft)as Button
        val speed =findViewById(R.id.Speed)as Button
        val time =findViewById(R.id.Time)as Button
        val cm =findViewById(R.id.editTextCm)as EditText
        val speedS=findViewById(R.id.editTextSpeed)as EditText
        val manual = findViewById(R.id.test) as Button



        // this gets the information from Destination then Menu activity and gives it to seconds text view
        fun distance(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("toAutomatic")
                }
            }
            return 109 // default
        }


        if(distance() == 100) {
            // counter
            val ica = findViewById<EditText>(R.id.ica).setVisibility(View.VISIBLE);
            val IKEA = findViewById<EditText>(R.id.ikea).setVisibility(View.INVISIBLE);
            val Chalmers = findViewById<EditText>(R.id.chalmers).setVisibility(View.INVISIBLE);
            val Elgiganten = findViewById<EditText>(R.id.elgiganten).setVisibility(View.INVISIBLE);
            val Nordstan = findViewById<EditText>(R.id.nordstan).setVisibility(View.INVISIBLE);
            val Lindholmen = findViewById<EditText>(R.id.lindholmen).setVisibility(View.INVISIBLE);
            val Stockholm = findViewById<EditText>(R.id.stockholm).setVisibility(View.INVISIBLE);
            val NewYork = findViewById<EditText>(R.id.newyork).setVisibility(View.INVISIBLE);
            val Home = findViewById<EditText>(R.id.homedestination).setVisibility(View.INVISIBLE);

            val chronometer = findViewById<Chronometer>(R.id.chronometer)
            chronometer.start()
        }

        if(distance() == 200) {
            // counter
            val ica = findViewById<EditText>(R.id.ica).setVisibility(View.INVISIBLE);
            val IKEA = findViewById<EditText>(R.id.ikea).setVisibility(View.VISIBLE);
            val Chalmers = findViewById<EditText>(R.id.chalmers).setVisibility(View.INVISIBLE);
            val Elgiganten = findViewById<EditText>(R.id.elgiganten).setVisibility(View.INVISIBLE);
            val Nordstan = findViewById<EditText>(R.id.nordstan).setVisibility(View.INVISIBLE);
            val Lindholmen = findViewById<EditText>(R.id.lindholmen).setVisibility(View.INVISIBLE);
            val Stockholm = findViewById<EditText>(R.id.stockholm).setVisibility(View.INVISIBLE);
            val NewYork = findViewById<EditText>(R.id.newyork).setVisibility(View.INVISIBLE);
            val Home = findViewById<EditText>(R.id.homedestination).setVisibility(View.INVISIBLE);

            val chronometer = findViewById<Chronometer>(R.id.chronometer)
            chronometer.start()
        }

        if(distance() == 300) {
            // counter
            val ica = findViewById<EditText>(R.id.ica).setVisibility(View.INVISIBLE);
            val IKEA = findViewById<EditText>(R.id.ikea).setVisibility(View.INVISIBLE);
            val Chalmers = findViewById<EditText>(R.id.chalmers).setVisibility(View.VISIBLE);
            val Elgiganten = findViewById<EditText>(R.id.elgiganten).setVisibility(View.INVISIBLE);
            val Nordstan = findViewById<EditText>(R.id.nordstan).setVisibility(View.INVISIBLE);
            val Lindholmen = findViewById<EditText>(R.id.lindholmen).setVisibility(View.INVISIBLE);
            val Stockholm = findViewById<EditText>(R.id.stockholm).setVisibility(View.INVISIBLE);
            val NewYork = findViewById<EditText>(R.id.newyork).setVisibility(View.INVISIBLE);
            val Home = findViewById<EditText>(R.id.homedestination).setVisibility(View.INVISIBLE);

            val chronometer = findViewById<Chronometer>(R.id.chronometer)
            chronometer.start()
        }

        if(distance() == 400) {
            // counter
            val ica = findViewById<EditText>(R.id.ica).setVisibility(View.INVISIBLE);
            val IKEA = findViewById<EditText>(R.id.ikea).setVisibility(View.INVISIBLE);
            val Chalmers = findViewById<EditText>(R.id.chalmers).setVisibility(View.INVISIBLE);
            val Elgiganten = findViewById<EditText>(R.id.elgiganten).setVisibility(View.VISIBLE);
            val Nordstan = findViewById<EditText>(R.id.nordstan).setVisibility(View.INVISIBLE);
            val Lindholmen = findViewById<EditText>(R.id.lindholmen).setVisibility(View.INVISIBLE);
            val Stockholm = findViewById<EditText>(R.id.stockholm).setVisibility(View.INVISIBLE);
            val NewYork = findViewById<EditText>(R.id.newyork).setVisibility(View.INVISIBLE);
            val Home = findViewById<EditText>(R.id.homedestination).setVisibility(View.INVISIBLE);

            val chronometer = findViewById<Chronometer>(R.id.chronometer)
            chronometer.start()
        }

        if(distance() == 500) {
            // counter
            val ica = findViewById<EditText>(R.id.ica).setVisibility(View.INVISIBLE);
            val IKEA = findViewById<EditText>(R.id.ikea).setVisibility(View.INVISIBLE);
            val Chalmers = findViewById<EditText>(R.id.chalmers).setVisibility(View.INVISIBLE);
            val Elgiganten = findViewById<EditText>(R.id.elgiganten).setVisibility(View.INVISIBLE);
            val Nordstan = findViewById<EditText>(R.id.nordstan).setVisibility(View.VISIBLE);
            val Lindholmen = findViewById<EditText>(R.id.lindholmen).setVisibility(View.INVISIBLE);
            val Stockholm = findViewById<EditText>(R.id.stockholm).setVisibility(View.INVISIBLE);
            val NewYork = findViewById<EditText>(R.id.newyork).setVisibility(View.INVISIBLE);
            val Home = findViewById<EditText>(R.id.homedestination).setVisibility(View.INVISIBLE);

            val chronometer = findViewById<Chronometer>(R.id.chronometer)
            chronometer.start()
        }

        if(distance() == 600) {
            // counter
            val ica = findViewById<EditText>(R.id.ica).setVisibility(View.INVISIBLE);
            val IKEA = findViewById<EditText>(R.id.ikea).setVisibility(View.INVISIBLE);
            val Chalmers = findViewById<EditText>(R.id.chalmers).setVisibility(View.INVISIBLE);
            val Elgiganten = findViewById<EditText>(R.id.elgiganten).setVisibility(View.INVISIBLE);
            val Nordstan = findViewById<EditText>(R.id.nordstan).setVisibility(View.INVISIBLE);
            val Lindholmen = findViewById<EditText>(R.id.lindholmen).setVisibility(View.VISIBLE);
            val Stockholm = findViewById<EditText>(R.id.stockholm).setVisibility(View.INVISIBLE);
            val NewYork = findViewById<EditText>(R.id.newyork).setVisibility(View.INVISIBLE);
            val Home = findViewById<EditText>(R.id.homedestination).setVisibility(View.INVISIBLE);

            val chronometer = findViewById<Chronometer>(R.id.chronometer)
            chronometer.start()
        }

        if(distance() == 700) {
            // counter
            val ica = findViewById<EditText>(R.id.ica).setVisibility(View.INVISIBLE);
            val IKEA = findViewById<EditText>(R.id.ikea).setVisibility(View.INVISIBLE);
            val Chalmers = findViewById<EditText>(R.id.chalmers).setVisibility(View.INVISIBLE);
            val Elgiganten = findViewById<EditText>(R.id.elgiganten).setVisibility(View.INVISIBLE);
            val Nordstan = findViewById<EditText>(R.id.nordstan).setVisibility(View.INVISIBLE);
            val Lindholmen = findViewById<EditText>(R.id.lindholmen).setVisibility(View.INVISIBLE);
            val Stockholm = findViewById<EditText>(R.id.stockholm).setVisibility(View.VISIBLE);
            val NewYork = findViewById<EditText>(R.id.newyork).setVisibility(View.INVISIBLE);
            val Home = findViewById<EditText>(R.id.homedestination).setVisibility(View.INVISIBLE);

            val chronometer = findViewById<Chronometer>(R.id.chronometer)
            chronometer.start()
        }

        if(distance() == 800) {
            // counter
            val ica = findViewById<EditText>(R.id.ica).setVisibility(View.INVISIBLE);
            val IKEA = findViewById<EditText>(R.id.ikea).setVisibility(View.INVISIBLE);
            val Chalmers = findViewById<EditText>(R.id.chalmers).setVisibility(View.INVISIBLE);
            val Elgiganten = findViewById<EditText>(R.id.elgiganten).setVisibility(View.INVISIBLE);
            val Nordstan = findViewById<EditText>(R.id.nordstan).setVisibility(View.INVISIBLE);
            val Lindholmen = findViewById<EditText>(R.id.lindholmen).setVisibility(View.INVISIBLE);
            val Stockholm = findViewById<EditText>(R.id.stockholm).setVisibility(View.INVISIBLE);
            val NewYork = findViewById<EditText>(R.id.newyork).setVisibility(View.VISIBLE);
            val Home = findViewById<EditText>(R.id.homedestination).setVisibility(View.INVISIBLE);

            val chronometer = findViewById<Chronometer>(R.id.chronometer)
            chronometer.start()
        }

        if(distance() == 900) {
            // counter
            val ica = findViewById<EditText>(R.id.ica).setVisibility(View.INVISIBLE);
            val IKEA = findViewById<EditText>(R.id.ikea).setVisibility(View.INVISIBLE);
            val Chalmers = findViewById<EditText>(R.id.chalmers).setVisibility(View.INVISIBLE);
            val Elgiganten = findViewById<EditText>(R.id.elgiganten).setVisibility(View.INVISIBLE);
            val Nordstan = findViewById<EditText>(R.id.nordstan).setVisibility(View.INVISIBLE);
            val Lindholmen = findViewById<EditText>(R.id.lindholmen).setVisibility(View.INVISIBLE);
            val Stockholm = findViewById<EditText>(R.id.stockholm).setVisibility(View.INVISIBLE);
            val NewYork = findViewById<EditText>(R.id.newyork).setVisibility(View.INVISIBLE);
            val Home = findViewById<EditText>(R.id.homedestination).setVisibility(View.VISIBLE);

            val chronometer = findViewById<Chronometer>(R.id.chronometer)
            chronometer.start()
        }


        // go to statistics

        val Statistics = findViewById(R.id.autoreahced) as Button



        Statistics.setOnClickListener {
            val intentback = Intent(this, Stats::class.java)
            startActivity(intentback)
        }


        // go to manual, when we return to automatic the time will disappear because we overriden it



        manual.setOnClickListener {
            val intentreal = Intent(this,ManualActivity2::class.java)
            intentreal.putExtra("toManual", distance())
            startActivity(intentreal)
        }




        // go to manual
        // we have to send intention to menu so that the distance we got is saved
        // THIS DOES NOT WORK WITH ANDROID BACK YET

        val back = findViewById(R.id.toManual) as ImageButton

        back.setOnClickListener {
            val back = Intent(this, Destination2::class.java)
            // the below was to keep the information in the menu but that turned to be not important
            // back.putExtra("toMenufordistance", distance())
            startActivity(back)
        }


        val handler = Handler()

        handler.postDelayed(object : Runnable {
            override fun run() {

                cm.setText(data.getDistance()+" Cm")

                handler.postDelayed(this, 500)
            }
        }, 700)


        handler.postDelayed(object : Runnable {
            override fun run() {

                speedS.setText(data.getSpeed()+" Cm/Sec")

                handler.postDelayed(this, 500)
            }
        }, 1000)


    }




}