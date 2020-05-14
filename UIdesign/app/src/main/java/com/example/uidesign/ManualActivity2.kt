package com.example.uieric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_manual2.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uidesign.statstat


class ManualActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual2)


        // to send the data from activity to a different activity
        fun getMyVariable(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("SecondVariable")
                }
            }
            return 109 // default
        }

        //  go to automatic and send data through the getMyVariable function

        val drive_automatic = findViewById(R.id.toautomatic) as ImageButton

        drive_automatic.setOnClickListener {
            val autodrive = Intent(this, MenuActivity::class.java)
            autodrive.putExtra("BackVariable1", getMyVariable())
            startActivity(autodrive)
        }



        // go to statistics

        val Statistics = findViewById(R.id.manualreached) as Button


        Statistics.setOnClickListener {
            val intentback = Intent(this, statstat::class.java)
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
            sendRequest("forward")
        }

        backward.setOnClickListener{
            if ((gear1background == 2 && gear2background == 2 && gear3background == 2) && (bottom_change == 3)){

                button_change.setBackgroundResource(R.drawable.chosengear);
                gear1background = 1;
            }
            sendRequest("backward")
        }
		
		right.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    sendRequest("turnRight")
                }
                MotionEvent.ACTION_UP -> {
                    sendRequest("resetAngle")
                }
            }
            return@OnTouchListener true
        })
		
		left.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    sendRequest("turnLeft")
                }
                MotionEvent.ACTION_UP -> {
                    sendRequest("resetAngle")
                }
            }
            return@OnTouchListener true
        })
		
       

        stop.setOnClickListener {
            sendRequest("stop")
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
            sendRequest("setGear?gear=1")
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
                sendRequest("setGear?gear=2")
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
                    sendRequest("setGear?gear=3")
                }

        plus.setOnClickListener {
            sendRequest("increaseSpeed")
        }

        minus.setOnClickListener {
            sendRequest("decreaseSpeed")
        }

        }

    fun sendRequest(endpoint : String){
        val queue: RequestQueue = Volley.newRequestQueue(this)
        val url = "http://213.80.116.220:12345/$endpoint"
        val stringRequest =
                StringRequest(Request.Method.GET, url, object : Response.Listener<String?> {
                    override fun onResponse(response: String?) {
                        Toast.makeText(
                                applicationContext,
                                "Command was successful !",
                                Toast.LENGTH_LONG
                        ).show()
                    }
                }, object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {
                        Toast.makeText(applicationContext, "Error occurred", Toast.LENGTH_LONG)
                                .show()
                    }
                })
        queue.add(stringRequest)
    }

    }
