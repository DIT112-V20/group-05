package com.example.uidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*


class Destination2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination2)

        //Variables
        var distance = 0

        // Buttons declaration
        val ok = findViewById(R.id.Ok) as Button
        val home = findViewById(R.id.home) as ImageButton
        val mListView = findViewById(R.id.list) as ListView
        val searchBar = findViewById(R.id.searchBox) as SearchView
        val request = RequestHandler(applicationContext)


        // List and adapter
        val arrayAdapter: ArrayAdapter<*>
        val locations = arrayOf(
            "ICA", "IKEA", "Chalmers", "Elgiganten", "Nordstan",
            "Lindholmen", "Stockholm", "New York", "Home"
        )

        // View the items in "locations in the ListView
        arrayAdapter = ArrayAdapter(this,
            R.layout.rowinlist, locations)

        // adapt locations to the Listview
        mListView.adapter = arrayAdapter

        // searchView connect to Listview
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {

                arrayAdapter.filter.filter(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })





        //Switch
        mListView.setOnItemClickListener {
                adapterView, view, i, l ->
            if (i == 0) {
                distance = 100

                val toast = Toast.makeText(
                    this,
                    locations[i]+"\t\t=\t\t"+distance+"  meters",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP, 0, 1280)
                toast.show()
            }
            if (i == 1) {
                distance = 200
                val toast = Toast.makeText(
                    this,
                    locations[i]+"\t\t=\t\t"+distance+"  meters",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP, 0, 1280)
                toast.show()
            }
            if (i == 2) {
                distance = 300
                val toast = Toast.makeText(
                    this,
                    locations[i]+"\t\t=\t\t"+distance+"  meters",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP, 0, 1280)
                toast.show()
            }
            if (i == 3) {
                distance = 400
                val toast = Toast.makeText(
                    this,
                    locations[i]+"\t\t=\t\t"+distance+"  meters",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP, 0, 1280)
                toast.show()
            }
            if (i == 4) {
                distance = 500
                val toast = Toast.makeText(
                    this,
                    locations[i]+"\t\t=\t\t"+distance+"  meters",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP, 0, 1280)
                toast.show()
            }
            if (i == 5) {
                distance = 600
                val toast = Toast.makeText(
                    this,
                    locations[i]+"\t\t=\t\t"+distance+"  meters",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP, 0, 1280)
                toast.show()
            }
            if (i == 6) {
                distance = 700
                val toast = Toast.makeText(
                    this,
                    locations[i]+"\t\t=\t\t"+distance+"  meters",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP, 0, 1280)
                toast.show()
            }
            if (i == 7) {
                distance = 800
                val toast = Toast.makeText(
                    this,
                    locations[i]+"\t\t=\t\t"+distance+"  meters",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP, 0, 1280)
                toast.show()
            }
            if (i == 8) {
                    distance = 900
                    val toast = Toast.makeText(
                        this,
                        locations[i]+"\t\t=\t\t"+distance+"  meters",
                        Toast.LENGTH_SHORT
                    )
                    toast.setGravity(Gravity.TOP, 0, 1280)
                    toast.show()
                }
        }

        // Click on OK button
        ok.setOnClickListener {
            if (distance !==0){
                val intent2 = Intent(this, activity_automatic::class.java)
                request.sendRequest("AutoOn?distance=" + distance)
                intent2.putExtra("toAutomatic", distance)
                startActivity(intent2)
            } else {
                Toast.makeText(this@Destination2, "Please select a destination", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Click on Home button
        home.setOnClickListener {
            val home = Intent(this, MainActivity::class.java)
            home.putExtra("BackVariable2", 1)
            startActivity(home)

        }


    }


}