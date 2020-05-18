package com.example.uidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import android.os.Handler

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
        val sec =findViewById(R.id.editTextTime)as EditText


        val chalmers =572
        var ica =56
        var IKEA= 100


        // go to statistics

        val Statistics = findViewById(R.id.autoreahced) as Button



        Statistics.setOnClickListener {
            val intentback = Intent(this, Stats::class.java)
            startActivity(intentback)
        }



        // go to manual

        val back = findViewById(R.id.toManual) as ImageButton

        back.setOnClickListener {
            val back = Intent(this, MenuActivity::class.java)
            startActivity(back)
        }
		
		
		val handler = Handler()

        handler.postDelayed(object : Runnable {
            override fun run() {
                
				cm.setText(data.getDistance()+" Cm")

                handler.postDelayed(this, delay.toLong())
            }
        }, 700)
		

        handler.postDelayed(object : Runnable {
            override fun run() {
                
				speedS.setText(data.getSpeed()+" Cm/Sec")

                handler.postDelayed(this, delay.toLong())
            }
        }, 1000)
		

    }




    }

