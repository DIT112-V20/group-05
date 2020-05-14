package com.example.uieric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uieric.MainActivity
import kotlinx.android.synthetic.main.activity_menu.*

class DestinationMenu : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.destination_menu)

        val destination_1 = findViewById<Button>(R.id.destination1)
        val destination_2 = findViewById<Button>(R.id.destination2)
        val destination_3 = findViewById<Button>(R.id.destination3)


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
            sendRequest("AutoOn?distance="+"56")
            val intent2 = Intent(this, MenuActivity::class.java)
            startActivity(intent2)
        }


//            USED FOR HOME BUTTTON!
        val go_back = findViewById(R.id.back1) as ImageButton

        go_back.setOnClickListener {

            val intentback2 = Intent(this, MainActivity::class.java)
            startActivity(intentback2)

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
