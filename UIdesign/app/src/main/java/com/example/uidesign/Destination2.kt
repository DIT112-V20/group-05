package com.example.uidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // List and adapter
        val arrayAdapter: ArrayAdapter<*>
        val locations = arrayOf(
            "ICA 100m", "IKEA 200m", "Chalmers 300m", "Elgiganten 400m", "Nordstan 500m",
            "Lindholmen 600m", "Stockholm 700m", "New York 800m", "Home 900m"
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
                Toast.makeText(this@Destination2, locations[0] , Toast.LENGTH_SHORT).show()
            }
            if (i == 1) {
                distance = 100
                Toast.makeText(this@Destination2, locations[1] , Toast.LENGTH_SHORT).show()
            }
            if (i == 2) {
                distance = 200
                Toast.makeText(this@Destination2, locations[2], Toast.LENGTH_SHORT).show()
            }
            if (i == 3) {
                distance = 300
                Toast.makeText(this@Destination2, locations[3], Toast.LENGTH_SHORT).show()
            }
            if (i == 4) {
                distance = 400
                Toast.makeText(this@Destination2, locations[4], Toast.LENGTH_SHORT).show()
            }
            if (i == 5) {
                distance = 500
                Toast.makeText(this@Destination2, locations[5], Toast.LENGTH_SHORT).show()
            }
            if (i == 6) {
                distance = 600
                Toast.makeText(this@Destination2, locations[6], Toast.LENGTH_SHORT).show()
            }
            if (i == 7) {
                distance = 700
                Toast.makeText(this@Destination2, locations[7] , Toast.LENGTH_SHORT).show()
            }
            if (i == 8) {
                distance = 800
                Toast.makeText(this@Destination2, locations[8] , Toast.LENGTH_SHORT).show()
            }
            if (i == 9) {
                distance = 900
                Toast.makeText(this@Destination2, locations[9], Toast.LENGTH_SHORT).show()
            }

        }

        // Click on OK button
        ok.setOnClickListener {
            if (distance !== 0){
                val intent2 = Intent(this, MenuActivity::class.java)
                intent.putExtra("toMenu", distance)
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
