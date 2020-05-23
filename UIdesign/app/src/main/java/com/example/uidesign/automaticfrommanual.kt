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


class automaticfrommanual : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_automaticfrommanual)

        val data : Data = Data(applicationContext)
        val cm =findViewById(R.id.editTextCm2)as EditText
        val speedS=findViewById(R.id.editTextSpeed2)as EditText
        val manual = findViewById(R.id.test2) as Button
        val backtoDestination = findViewById(R.id.toDestination2) as ImageButton




        // this gets the information from Destination then Menu activity and gives it to seconds text view
        fun distance(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("toAutomatic2")
                }
            }
            return 109 // default
        }

        // go to destination to pick a new destination

        backtoDestination.setOnClickListener {
            val back = Intent(this, Destination2::class.java)
            startActivity(back)
        }

        // go to statistics

        val Statistics = findViewById(R.id.autoreahced2) as Button



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


        val handler = Handler()

        handler.postDelayed(object : Runnable {
            override fun run() {

                cm.setText(data.findDistance()+" Cm")

                handler.postDelayed(this, 500)
            }
        }, 700)


        handler.postDelayed(object : Runnable {
            override fun run() {

                speedS.setText(data.findSpeed()+" Cm/Sec")

                handler.postDelayed(this, 500)
            }
        }, 1000)
    }
}
