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
    }
}