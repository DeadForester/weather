package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.json.JSONObject
import java.net.URL
import android.content.Intent


class MainActivity : AppCompatActivity() {

    private var user_field: EditText? = null
    private var main_btn: Button? = null
    private var result_info: TextView? = null
    private var authors_btn: Button? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user_field = findViewById(R.id.editTextText)
        main_btn = findViewById(R.id.main_btn)
        result_info = findViewById(R.id.result_info)
        authors_btn = findViewById(R.id.authors_btn)


        authors_btn?.setOnClickListener {
            val intent = Intent(this@MainActivity, AuthorsActivity::class.java)
            startActivity(intent)
        }



        main_btn?.setOnClickListener {
            if(user_field?.text?.toString()?.trim()?.equals("")!!)
                Toast.makeText(this,"Введите город", Toast.LENGTH_LONG).show()
            else {
                val city: String = user_field?.text.toString()
                val key: String = "842b4a1ce58c35f823e2d921c6bab177"
                val url: String = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key&units=metric&lang=ru"
                GlobalScope.async(Dispatchers.Default) {
                    val apiResponse = URL(url).readText()
                    val weather = JSONObject(apiResponse).getJSONArray("weather")
                    val desc = weather.getJSONObject(0).getString("description")
                    val main = JSONObject(apiResponse).getJSONObject("main")
                    val temp = main.getString("temp")
                    val wind = JSONObject(apiResponse).getJSONObject("wind")
                    val speed = wind.getString("speed")
                    val intent = Intent(this@MainActivity, TemperatureActivity::class.java)
                    intent.putExtra("temperature", temp)
                    intent.putExtra("description", desc)
                    intent.putExtra("wind",speed)
                    startActivity(intent)

                }




            }
        }
    }
}