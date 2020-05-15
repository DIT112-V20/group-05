package com.example.alone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class activity_automatic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_automatic)


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

    }




    }

