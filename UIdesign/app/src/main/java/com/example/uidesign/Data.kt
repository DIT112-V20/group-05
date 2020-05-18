package com.example.uidesign

import android.content.Context
import android.preference.PreferenceManager

class Data constructor(val context : Context) {

    val request : RequestHandler = RequestHandler(context)

    fun getResponse() : String? {
        val m = PreferenceManager.getDefaultSharedPreferences(context)
        var mResponse = m.getString("Response", "")
        return mResponse
    }

    fun getDistance() : String? {
        request.getRequest("sensor")
        return getResponse()
    }
	
	fun getSpeed() : String? {
        request.getRequest("sensor")
        return getResponse()
    }
	
	fun getTime() : String? {
        request.getRequest("sensor")
        return getResponse()
    }
}