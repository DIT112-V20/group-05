package com.example.uidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class Destination : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)


        val destination_1 = findViewById(R.id.destination1) as Button
        val destination_2 = findViewById(R.id.destination2) as Button
        val destination_3 = findViewById(R.id.destination3) as Button


        destination_1.setOnClickListener {
            sendRequest("AutoOn?distance="+"572")
            val intent2 = Intent(this, MenuActivity::class.java)
            startActivity(intent2)
        }
        destination_2.setOnClickListener {
            sendRequest("AutoOn?distance="+"100")
            val intent2 = Intent(this, MenuActivity::class.java)
            startActivity(intent2)
        }
        destination_3.setOnClickListener {
            sendRequest("AutoOn?distance="+"100")
            val intent2 = Intent(this, MenuActivity::class.java)
            startActivity(intent2)
        }




        val home = findViewById(R.id.home) as ImageButton

        home.setOnClickListener {
            val home = Intent(this, MainActivity::class.java)
            home.putExtra("BackVariable2", 1)
            startActivity(home)

        }


    }

    fun sendRequest(endpoint : String){
        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
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
