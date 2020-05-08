package com.example.uieric

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_manual2.*


class ManualActivityLink : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_link)

// this gets the signal from the automatic
        fun getMyVariable3(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("SecondVariable")
                }
            }
            return 109 // default
        }

        // this will give the link to automatic

        val manuallink = findViewById<Button>(R.id.back5)

        manuallink.setOnClickListener {
            val intent2 = Intent(this, ManualActivityLink::class.java)
            intent2.putExtra("fromManualLink", 1)
            startActivity(intent2)
        }


        // this will get the signal from manual link
        fun getMyVariableback(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("BackVariable1")
                }
            }
            return 109 // default
        }

        fun getMyVariablefromAuto2(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("VariablefromaAuto")
                }
            }
            return 109 // default
        }


        val go_back2 = findViewById(R.id.back2) as ImageButton

        go_back2.setOnClickListener {
            val intentback = Intent(this, activity_automatic::class.java)
            intentback.putExtra("BackVariable3", getMyVariable3())
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


        var button_change = findViewById(R.id.gear1) as Button;
        var button_change2 = findViewById(R.id.gear2) as Button;
        var button_change3 = findViewById(R.id.gear3) as Button;


        // to make sure the first gear light is chosen when we press a direction without manually choosing any gear
        forward.setOnClickListener{
            if ((gear1background == 2 && gear2background == 2 && gear3background == 2) && (top_change == 3)){

                button_change.setBackgroundResource(R.drawable.chosengear);
                gear1background = 1;
            }
        }

        backward.setOnClickListener{
            if ((gear1background == 2 && gear2background == 2 && gear3background == 2) && (bottom_change == 3)){

                button_change.setBackgroundResource(R.drawable.chosengear);
                gear1background = 1;
            }
        }
        right.setOnClickListener{
            if ((gear1background == 2 && gear2background == 2 && gear3background == 2) && (right_change == 3)){

                button_change.setBackgroundResource(R.drawable.chosengear);
                gear1background = 1;
            }
        }
        left.setOnClickListener{
            if ((gear1background == 2 && gear2background == 2 && gear3background == 2) && (left_change == 3)){

                button_change.setBackgroundResource(R.drawable.chosengear);
                gear1background = 1;
            }
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


        }



        if(getMyVariable3()==1) {

            var menuchange = findViewById(R.id.onoff3) as Button;
            menuchange.setBackgroundResource(R.drawable.transparent);
        }


        if(getMyVariablefromAuto2()==1) {

            var menuchange = findViewById(R.id.onoff3) as Button;
            menuchange.setBackgroundResource(R.drawable.transparent);
        }



    }

}
