package com.example.uieric

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class PopUpObstacle  : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popwindow)

        val returnButton = findViewById<Button>(R.id.okButton)



        returnButton.setOnClickListener {
            val intentback = Intent(this, activity_automatic::class.java)
            startActivity(intentback)
        }


    }
}
