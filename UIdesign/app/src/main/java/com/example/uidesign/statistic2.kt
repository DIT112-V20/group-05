package com.example.uidesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.uieric.R
import kotlinx.android.synthetic.main.popwindow.*

class statistic2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic2)

        val tripStatistic = textView

        val home = findViewById(R.id.homeImageButton)as ImageButton

        val estimatedDistance = findViewById(R.id.estimatedDistance)as Button

        val actualCost = findViewById(R.id.actualCost)as Button

        val  actualDistance = findViewById(R.id.actualDistance)as Button

    }
}



