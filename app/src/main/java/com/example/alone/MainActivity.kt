package com.example.alone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var connectBackround: Int = 2;
        var disconnectBackround: Int = 2;
        var startapp: Int = 0;
        var offapp: Int = 1;


        var btn_click_me = findViewById(R.id.getstarted) as Button


        var button_change = findViewById(R.id.onoff) as Button;
        var clickeron2 = findViewById(R.id.Connect) as Button;

        var button_change2 = findViewById(R.id.onoff) as Button;
        var clickeroff = findViewById(R.id.Disconnect) as Button;

        var start = findViewById(R.id.getstarted) as Button;


        start.setOnClickListener {
            val begin = Intent(this, Destination::class.java)
            startActivity(begin)

        }

    }

}
