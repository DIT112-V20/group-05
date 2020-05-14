package com.example.uieric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.uieric.MenuActivity
import com.example.uieric.R
import com.example.uieric.activity_automatic

class statstat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statstat)


        val go_back5 = findViewById(R.id.back5) as ImageButton

        fun getMyVariable5(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("SecondVariable")
                }
            }
            return 109 // default
        }



        go_back5.setOnClickListener {
            val intentback = Intent(this, activity_automatic::class.java)
            intentback.putExtra("statvar", getMyVariable5())
            startActivity(intentback)
        }



        if(getMyVariable5()==1){

            var backchange = findViewById(R.id.onoff5) as Button;
            backchange.setBackgroundResource(R.drawable.transparent);
        }



    }
}
