package com.example.uidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class Stats : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)



        val home = findViewById(R.id.home) as ImageButton

        home.setOnClickListener {
            val home = Intent(this, MainActivity::class.java)
            home.putExtra("BackVariable2", 1)
            startActivity(home)

        }


        val estimatedDistance = findViewById(R.id.estimatedDistance)as Button

        val actualCost = findViewById(R.id.actualCost)as Button

        val  actualDistance = findViewById(R.id.actualDistance)as Button

        val estimatedDistanceString = findViewById(R.id.estimated)as EditText

        val driven = findViewById(R.id.actualdriven)as EditText

        val percentage2 = findViewById(R.id.percentage)as EditText


        val data : Data = Data(applicationContext)

        val handler = Handler()

        handler.postDelayed(object : Runnable {
            override fun run() {

                estimatedDistanceString.setText(data.findDistance()+" m")
                percentage2.setText(data.findDistance())
                handler.postDelayed(this, 500)
            }
        }, 500)




    }
}
