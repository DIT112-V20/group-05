package com.example.uidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton


class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val request = RequestHandler(applicationContext)

        setContentView(R.layout.settings)


        val submit = findViewById(R.id.submit) as Button
        val back = findViewById(R.id.back) as ImageButton
        val ip = findViewById(R.id.ip) as EditText
        val port = findViewById(R.id.port) as EditText

        submit.setOnClickListener {
            request.changeIp(ip.text.toString())
            request.changePort(port.text.toString())
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        // get previous light

        fun getMyVariable(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("toSettings")
                }
            }
            return 109 // default
        }

        // Click on back button and preserve the connection light of past screen (unlike the submit that needs a connect to make it green again)
        back.setOnClickListener {
            val home = Intent(this, MainActivity::class.java)
            home.putExtra("BackVariable2", getMyVariable())
            startActivity(home)

        }

    }
}