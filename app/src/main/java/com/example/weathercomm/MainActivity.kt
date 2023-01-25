package com.example.weathercomm

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    val CITY: String = "toronto,ca"
    val API: String = "b0fa7e6d6ef32271d13bc15b0a485688"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherTask().execute()
    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
            findViewById<TextView>(R.id.errortext).visibility = View.VISIBLE

        }

        override fun doInBackground(vararg p0: String?): String {
            var response:String?
            try{
                response = URL(spec:"https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(Charsets.UTF_8)
            }
            catch (e: Exception)
            {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?){
            super.onPostExwecute(result)
            try{
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject(name: "main")
                val sys = jsonObj.getJSONObject(name: "sys")
                val wind = jsonObj.getJSONObject(name: "wind")
            }
        }
    }

}
