package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TemperatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)

        val temperatureTextView: TextView = findViewById(R.id.temperatureTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val speedTextView: TextView = findViewById(R.id.speedTextView)


        val temperature = intent.getStringExtra("temperature")
        val description = intent.getStringExtra("description")
        val wind= intent.getStringExtra("wind")

        temperatureTextView.text = "Температура: $temperature"
        descriptionTextView.text = "Описание: $description"
        speedTextView.text = "Скорость ветра: $wind м/c"

    }
}