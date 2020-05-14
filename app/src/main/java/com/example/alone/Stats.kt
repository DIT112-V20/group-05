package com.example.alone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Stats : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)



        val home = findViewById(R.id.home) as ImageButton

        home.setOnClickListener {
            val home = Intent(this, MainActivity::class.java)
            home.putExtra("fromDestination", 1)
            startActivity(home)

        }


        val estimatedDistance = findViewById(R.id.estimatedDistance)as Button

        val actualCost = findViewById(R.id.actualCost)as Button

        val  actualDistance = findViewById(R.id.actualDistance)as Button


    }
}
