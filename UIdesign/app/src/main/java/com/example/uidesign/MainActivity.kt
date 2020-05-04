package com.example.uidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var connectBackround: Int = 2;
    var disconnectBackround: Int = 2;
    var startapp: Int = 0;
    var offapp: Int = 1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_click_me = findViewById(R.id.getstarted) as Button


        var button_change = findViewById(R.id.onoff) as Button;
        var clickeron = findViewById(R.id.Connect) as Button;

        var button_change2 = findViewById(R.id.onoff) as Button;
        var clickeroff = findViewById(R.id.Disconnect) as Button;

        var start = findViewById(R.id.getstarted) as Button;


        fun getMyVariableAgain(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("BackVariable2")
                }
            }
            return 109 // default
        }



        if(getMyVariableAgain()==1){

            var again = findViewById(R.id.onoff) as Button;
            again.setBackgroundResource(R.drawable.transparent);
        }


        clickeroff.setOnClickListener {
            if (disconnectBackround == 2) {

                button_change2.setBackgroundResource(R.drawable.nottransparent);
                offapp = 2
            }
            if (startapp == 2 && offapp == 2) {
                 btn_click_me = findViewById(R.id.getstarted) as Button

                btn_click_me.setOnClickListener {


                    val intent = Intent(this, MainActivity::class.java)
                    Toast.makeText(this@MainActivity, "You must connect", Toast.LENGTH_SHORT).show()

                }

            }
        }


        start.setOnClickListener{
            if(getMyVariableAgain()!=1) {
                Toast.makeText(this@MainActivity, "You must connect", Toast.LENGTH_SHORT).show()
            }else{
                    if (connectBackround == 2 && offapp == 1) {

                        button_change.setBackgroundResource(R.drawable.transparent);
                        btn_click_me = findViewById(R.id.getstarted) as Button


                        val intent = Intent(this, MenuActivity::class.java)

                        intent.putExtra("FirstVariable", offapp)
                        startActivity(intent)

                    } else{
                        Toast.makeText(this@MainActivity, "You must connect", Toast.LENGTH_SHORT).show()

                    }




            }
        }



            clickeron.setOnClickListener {
                if (connectBackround == 2) {

                    button_change.setBackgroundResource(R.drawable.transparent);

                    startapp = 2
                    offapp = 1
                }
                val btn_click_me = findViewById(R.id.getstarted) as Button

                btn_click_me.setOnClickListener {

                    val intent = Intent(this, MenuActivity::class.java)

                    intent.putExtra("FirstVariable", offapp)
                    startActivity(intent)
                    }
            }


        }
}



