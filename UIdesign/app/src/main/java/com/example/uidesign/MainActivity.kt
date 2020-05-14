package com.example.uieric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uieric.MenuActivity
import com.example.uieric.R
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
                    val toast = Toast.makeText(this@MainActivity, "You must connect first", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0,800)
                    toast.show()
                }
        }
            sendRequest("disconnect")

        }


        start.setOnClickListener{
            if(getMyVariableAgain()!=1) {
                val toast = Toast.makeText(this@MainActivity, "You must connect first", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0,800)
                toast.show()            }else{
                    if (connectBackround == 2 && offapp == 1) {

                        button_change.setBackgroundResource(R.drawable.transparent);
                        btn_click_me = findViewById(R.id.getstarted) as Button


                        val intent = Intent(this, MenuActivity::class.java)

                        intent.putExtra("FirstVariable", offapp)
                        startActivity(intent)

                    } else{
                        val toast = Toast.makeText(this@MainActivity, "You must connect first", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP, 0,800)
                        toast.show()
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
                sendRequest("")
            }


        }


    fun sendRequest(endpoint : String){
        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val url = "http://192.168.43.199:12345/$endpoint"
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




