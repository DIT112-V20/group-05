package com.example.uieric

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.uidesign.statstat
import kotlinx.android.synthetic.main.activity_automatic.*

class activity_automatic : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_automatic)

        var finaldigit = 0
        val alertbutton = findViewById(R.id.switchtomanual) as Button
        val tempButton = findViewById(R.id.TempButtonWarning) as Button
        val tempSuccess = findViewById(R.id.TempButtonSuccess) as Button
        val stat = findViewById(R.id.statistics) as Button

        val imageButton = findViewById<ImageButton>(R.id.back3)
        val number = findViewById<EditText>(R.id.editTextNumber)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val destinationView = findViewById<TextView>(R.id.DestinationTextView)
        submitButton.setOnClickListener {
            destinationView.setText(number.text)
        }

// this gets the signal from the menu

        fun getMyVariable2(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("SecondVariable")
                }
            }
            return 109 // default
        }


        fun ok() {
            val intentforward = Intent(this, ManualActivity2::class.java)
            intentforward.putExtra("SecondVariable", getMyVariable2())
            startActivity(intentforward)
        }


        var button_change = findViewById(R.id.statistics) as Button;


        var statisticsActivated: Int = 2;



        fun activateSuccess() {
            button_change.setBackgroundResource(R.drawable.colorchange);

        }

        tempButton.setOnClickListener {
            val builder = AlertDialog.Builder(
                this@activity_automatic,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert
            )
            builder.setTitle("Obstacle Avoided")
            builder.setMessage("The car just avoided hitting an obstacle. \n Maneuver around it using Manual Mode")
            builder.setPositiveButton("To Manual Mode") { dialog, which ->
                ok()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }


        stat.setOnClickListener {
            if(statisticsActivated==2){
                val toast = Toast.makeText(this, "Statistics will be activitated when destination is reached", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0,800)
                toast.show()
            }
            if(statisticsActivated == 1) {
            val intentforward = Intent(this, statstat::class.java)
            intentforward.putExtra("SecondVariable", getMyVariable2())
            startActivity(intentforward)
}
        }


        tempSuccess.setOnClickListener {
            val builder = AlertDialog.Builder(
                this@activity_automatic,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert
            )
            builder.setTitle("Mission Accomplished")
            builder.setMessage("The Final Destination Has Been Reached")
            builder.setPositiveButton("OK") { dialog, which ->
                activateSuccess()
                statisticsActivated = 1
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()

        }
// this gets the signal from the menu

            fun getMyVariable3(): Int {
                if (intent != null) {
                    if (intent.extras != null) {
                        return intent.extras!!.getInt("fromManualLink")
                    }
                }
                return 109 // default
            }


        fun getMyVariablestat(): Int {
            if (intent != null) {
                if (intent.extras != null) {
                    return intent.extras!!.getInt("statvar")
                }
            }
            return 109 // default
        }


            alertbutton.setOnClickListener {
                val builder = AlertDialog.Builder(
                    this@activity_automatic,
                    R.style.Theme_AppCompat_DayNight_Dialog_Alert
                )
                builder.setTitle("Warning")
                builder.setMessage("You are about to exit the automatic mode.\n Do you want to continue?")
                builder.setPositiveButton("OK") { dialog, which ->
                    ok()
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }






            if (getMyVariable2() == 1) {

                var menuchange = findViewById(R.id.onoff3) as Button;
                menuchange.setBackgroundResource(R.drawable.transparent);
            }



            if (getMyVariable3() == 1) {

                var menuchange = findViewById(R.id.onoff3) as Button;
                menuchange.setBackgroundResource(R.drawable.transparent);
            }

        if (getMyVariablestat() == 1) {

            var menuchange = findViewById(R.id.onoff3) as Button;
            menuchange.setBackgroundResource(R.drawable.transparent);
        }


        if (getMyVariablestat() == 1 || getMyVariable2() == 1) {
            finaldigit = 1
        }


        val go_back3 = findViewById(R.id.back3) as ImageButton

        go_back3.setOnClickListener {
            val intentback = Intent(this, MenuActivity::class.java)
            intentback.putExtra("BackVariable0", finaldigit)
            startActivity(intentback)
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
