package com.example.uidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton


class ManualActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual2)


        val request = RequestHandler(applicationContext)

        // this gets the information from automatic1
        fun distance(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("toManual")
                }
            }
            return 109 // default
        }


// this will send the distance we got from automatic1 to automatic2

        val drive_automatic = findViewById(R.id.toautomaticnew) as ImageButton

        drive_automatic.setOnClickListener {
            val autodrive2 = Intent(this, automaticfrommanual::class.java)
            autodrive2.putExtra("toAutomatic2", distance())
            startActivity(autodrive2)
        }




        val Statistics = findViewById(R.id.toStatistics) as Button


        Statistics.setOnClickListener {
            val intentback = Intent(this, Destination2::class.java)
            startActivity(intentback)
        }




        var top_change: Int = 3;
        var bottom_change: Int = 3;
        var right_change: Int = 3;
        var left_change: Int = 3;


        var gear1background: Int = 2;
        var gear2background: Int = 2;
        var gear3background: Int = 2;

        var forward = findViewById(R.id.topButton) as Button
        var backward = findViewById(R.id.bottomButton) as Button
        var left = findViewById(R.id.leftButton) as Button
        var right = findViewById(R.id.rightButton) as Button
        var stop = findViewById(R.id.stopButton) as Button

        var plus = findViewById(R.id.plus) as Button
        var minus = findViewById(R.id.minus) as Button


        var button_change = findViewById(R.id.gear1) as Button;
        var button_change2 = findViewById(R.id.gear2) as Button;
        var button_change3 = findViewById(R.id.gear3) as Button;


        // to make sure the first gear light is chosen when we press a direction without manually choosing any gear
        forward.setOnClickListener{
            if ((gear1background == 2 && gear2background == 2 && gear3background == 2) && (top_change == 3)){

                button_change.setBackgroundResource(R.drawable.chosengear);
                gear1background = 1;
            }
            request.sendRequest("forward")
        }

        backward.setOnClickListener{
            if ((gear1background == 2 && gear2background == 2 && gear3background == 2) && (bottom_change == 3)){

                button_change.setBackgroundResource(R.drawable.chosengear);
                gear1background = 1;
            }
            request.sendRequest("backward")
        }

        right.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    request.sendRequest("turnRight")
                }
                MotionEvent.ACTION_UP -> {
                    request.sendRequest("resetAngle")
                }
            }
            return@OnTouchListener true
        })

        left.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    request.sendRequest("turnLeft")
                }
                MotionEvent.ACTION_UP -> {
                    request.sendRequest("resetAngle")
                }
            }
            return@OnTouchListener true
        })



        stop.setOnClickListener {
            request.sendRequest("stop")
        }


        button_change.setOnClickListener {
            if (gear1background == 2) {

                button_change.setBackgroundResource(R.drawable.chosengear);
                gear1background = 1;
                if (gear2background == 1 || gear3background == 1) {
                    button_change3.setBackgroundResource(R.drawable.gears);
                    gear3background = 2;
                    button_change2.setBackgroundResource(R.drawable.gears);
                    gear2background = 2;

                }
            }
            request.sendRequest("setGear?gear=1")
        }


        button_change2.setOnClickListener {
            if (gear2background == 2) {
                button_change2.setBackgroundResource(R.drawable.chosengear);
                gear2background = 1;
                if (gear1background == 1 || gear3background == 1) {
                    button_change.setBackgroundResource(R.drawable.gears);
                    gear1background = 2;
                    button_change3.setBackgroundResource(R.drawable.gears);
                    gear3background = 2;

                }
            }
            request.sendRequest("setGear?gear=2")
        }


        button_change3.setOnClickListener {
            if (gear3background == 2) {
                button_change3.setBackgroundResource(R.drawable.chosengear);
                gear3background = 1;
                if (gear2background == 1 || gear1background == 1) {
                    button_change2.setBackgroundResource(R.drawable.gears);
                    gear2background = 2;
                    button_change.setBackgroundResource(R.drawable.gears);
                    gear1background = 2;

                }
            }
            request.sendRequest("setGear?gear=3")
        }

        plus.setOnClickListener {
            request.sendRequest("increaseSpeed")
        }

        minus.setOnClickListener {
            request.sendRequest("decreaseSpeed")
        }




    }







}


