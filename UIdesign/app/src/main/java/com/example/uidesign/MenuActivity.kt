package com.example.uidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast


class MenuActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var finalresult = 0
        val manual = findViewById<Button>(R.id.manualb)

        // created this to get the color of the LED light from the main, and inheriting it to other classes

        fun getMyVariable(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("FirstVariable")
                }
            }
            return 109 // default
        }

        fun getMyVariableback(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("BackVariable1")
                }
            }
            return 109 // default
        }



        if(getMyVariableback()==1){

            var backchange = findViewById(R.id.onoff2) as Button;
            backchange.setBackgroundResource(R.drawable.transparent);
        }



        if(getMyVariable()==1|| getMyVariableback()==1){

            var menuchange = findViewById(R.id.onoff2) as Button;
            menuchange.setBackgroundResource(R.drawable.transparent)
             finalresult = 1
        }






        val go_back = findViewById(R.id.back1) as ImageButton

        go_back.setOnClickListener {

            val intentback2 = Intent(this, MainActivity::class.java)
            intentback2.putExtra("BackVariable2", finalresult)
            startActivity(intentback2)


        }




        manual.setOnClickListener {


            val intent2 = Intent(this, ManualActivity2::class.java)
            intent2.putExtra("SecondVariable", finalresult)
            startActivity(intent2)
        }



    }
}
