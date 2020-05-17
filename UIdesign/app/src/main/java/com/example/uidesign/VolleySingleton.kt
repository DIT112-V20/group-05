package com.example.uidesign

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley



class VolleySingleton constructor(context: Context) {
    companion object {

        @Volatile
        private var instance: VolleySingleton? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: VolleySingleton(context)
        }
    }

    val requestQueue: RequestQueue by lazy {

        Volley.newRequestQueue(context.applicationContext)
    }
}