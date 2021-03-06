package com.example.uidesign

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
        val request = RequestHandler(applicationContext)

        val home = findViewById(R.id.home) as ImageButton

        home.setOnClickListener {
            val home = Intent(this, MainActivity::class.java)
            home.putExtra("BackVariable2", 1)
            startActivity(home)

        }

        // this gets the information from Destination activity
        fun button(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("toMenu")
                }
            }
            return 109 // default
        }

        // this gives the signal to manual
        manual.setOnClickListener {
            val intent2 = Intent(this, ManualActivity2::class.java)
            var distance = button().toString()
            request.sendRequest("endpoint?"+ distance)
            startActivity(intent2)
        }

        // this gives the signal to automatic
        automatic.setOnClickListener {
            val intent2 = Intent(this, activity_automatic::class.java)
            var distance = button().toString()
            request.sendRequest("endpoint?"+ distance)
            startActivity(intent2)
        }



    }
}
