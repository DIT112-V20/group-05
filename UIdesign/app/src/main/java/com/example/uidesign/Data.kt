package com.example.uidesign

import android.content.Context
import android.preference.PreferenceManager

class Data constructor(val context : Context) {

    val request : RequestHandler = RequestHandler(context)
	
	var speed : String = ""
    var distance : String  = ""


    fun getResponse() : String? {
        val m = PreferenceManager.getDefaultSharedPreferences(context)
        var mResponse = m.getString("Response", "")
        return mResponse
    }
	
	fun getInfo(){

        //request.getRequest("sendInfo")
        var str = getResponse()
        var parts = str?.split("/")
        if (parts != null) {
            speed  = parts.get(0)
            distance  = parts.get(1)
            

        }
    }

    fun findSpeed() : String? {
        //getInfo()
        getInfo()
        //request.getRequest("")
        return speed
    }
  
  fun findDistance() : String? {
        request.getRequest("sendInfo")
        return distance
    }
}

