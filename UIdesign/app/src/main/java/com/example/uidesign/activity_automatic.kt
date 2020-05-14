package com.example.uieric

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.MainActivity
import com.example.uidesign.statstat

import kotlinx.android.synthetic.main.activity_automatic.*

class activity_automatic : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_automatic)


        // go to statistics

        val Statistics = findViewById(R.id.autoreahced) as Button



        Statistics.setOnClickListener {
            val intentback = Intent(this, statstat::class.java)
            startActivity(intentback)
        }



        // go to manual

        val drive_manual = findViewById(R.id.toManual) as ImageButton

        drive_manual.setOnClickListener {
            val manualdrive = Intent(this, ManualActivity2::class.java)
            startActivity(manualdrive)
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
