package com.example.alone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var finalresult = 0
        val manual = findViewById<Button>(R.id.manual)



        val home = findViewById(R.id.home) as ImageButton

        home.setOnClickListener {
            val home = Intent(this, MainActivity::class.java)
            home.putExtra("fromMenu", 1)
            startActivity(home)

        }



// this gives the signal to manual

        manual.setOnClickListener {
            val intent2 = Intent(this, ManualActivity2::class.java)
            startActivity(intent2)
        }


// this gives the signal to automatic

        automatic.setOnClickListener {
            val intent2 = Intent(this, activity_automatic::class.java)
            startActivity(intent2)
        }



    }
}
