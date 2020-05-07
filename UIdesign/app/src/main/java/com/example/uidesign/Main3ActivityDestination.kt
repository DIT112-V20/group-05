package com.example.uidesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.uieric.R

class Main3ActivityDestination : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3_destination)



        val destination = findViewById<TextView>(R.id.TextViewDestination)
        val number = findViewById<EditText>(R.id.editTextNumber)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val destinationView = findViewById<TextView>(R.id.DestinationTextView)
        submitButton.setOnClickListener {
            destinationView.setText(number.text)
        }
    }
}