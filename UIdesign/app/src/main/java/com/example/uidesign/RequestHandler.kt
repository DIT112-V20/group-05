package com.example.uidesign

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.preference.PreferenceManager

class RequestHandler(val mContext: Context) {


	companion object{
		var ip = "192.168.43.199"
		var port = "12345"
	}
	
	fun changeIp(newIp : String){
		ip = newIp
	}
	
	fun changePort(newPort : String){
		port = newPort
	}


    fun sendRequest(endpoint : String){
        val queue: RequestQueue = VolleySingleton.getInstance(mContext).requestQueue
        val url = "http://$ip:$port/$endpoint"
        val stringRequest =
            StringRequest(Request.Method.GET, url, object : Response.Listener<String?> {
                override fun onResponse(response: String?) {
                    Toast.makeText(
                        mContext,
                        "Command was successful !",
                        Toast.LENGTH_LONG
                    )
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(mContext, "Error occurred", Toast.LENGTH_LONG)
                }
            })
        queue.add(stringRequest)


    }

    fun getRequest(endpoint : String){
        val queue: RequestQueue = VolleySingleton.getInstance(mContext).requestQueue
        val url = "http://$ip:$port/$endpoint"
        val stringRequest =
            StringRequest(Request.Method.GET, url, object : Response.Listener<String?> {
                override fun onResponse(response: String?) {
				
				sharedResponse(response.toString())
				
                    /*Toast.makeText(
                        mContext,
                        response,
                        Toast.LENGTH_LONG
                    ).show()*/
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(mContext, "ERROR", Toast.LENGTH_LONG)

                }
            })
        queue.add(stringRequest)
    }
	
	private fun sharedResponse(response: String) {
        val m = PreferenceManager.getDefaultSharedPreferences(mContext)
        val editor = m.edit()
        editor.putString("Response", response)
        editor.commit()
    }
}