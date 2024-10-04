package com.example.smart_green_house

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smart_green_house.Response.KelembapanResponse
import com.example.smart_green_house.Response.SuhuResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private val delay = 1000L // 1000 ms = 1 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var temperaturActiv: LinearLayout = findViewById(R.id.temperatureActiv)
        var humidityActiv: LinearLayout = findViewById(R.id.humadityActiv)
        var soilMostureActiv: LinearLayout = findViewById(R.id.soilMostureActiv)
        var lampActiv: LinearLayout = findViewById(R.id.lampActiv)

        temperaturActiv.setOnClickListener {
            startActivity(Intent(applicationContext, TemperatureActivity::class.java))

        }

        humidityActiv.setOnClickListener {
            startActivity(Intent(applicationContext, HumidityActivity::class.java))
        }

        soilMostureActiv.setOnClickListener {
            startActivity(Intent(applicationContext, SoilMoistureActivity::class.java))
        }

        lampActiv.setOnClickListener {
//            startActivity(Intent(applicationContext, Lam))
        }

    }
}