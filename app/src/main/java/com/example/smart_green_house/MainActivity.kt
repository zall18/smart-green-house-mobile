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

        var suhu: TextView = findViewById(R.id.tvTemperature)
        var kelembapan: TextView = findViewById(R.id.tvHumidity)


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

        handler = Handler(Looper.getMainLooper())

        handler.postDelayed(object : Runnable {
            override fun run() {
                // Panggil API suhu
                RetrofitClient.instance.getSuhu().enqueue(object : Callback<SuhuResponse> {
                    override fun onResponse(call: Call<SuhuResponse>, response: Response<SuhuResponse>) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            suhu.text = data?.suhu
                        } else {
                            Log.d("Data", "onResponse: Gagal")
                        }
                    }

                    override fun onFailure(call: Call<SuhuResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })

                // Panggil API kelembapan
                RetrofitClient.instance.getkelembapan().enqueue(object : Callback<KelembapanResponse> {
                    override fun onResponse(call: Call<KelembapanResponse>, response: Response<KelembapanResponse>) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            kelembapan.text = data?.kelembapan
                        } else {
                            Log.d("Data", "onResponse: Gagal")
                        }
                    }

                    override fun onFailure(call: Call<KelembapanResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })

                // Panggil ulang runnable ini setelah 1 detik
                handler.postDelayed(this, delay)
            }
        }, delay)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Hentikan handler saat activity dihancurkan
        handler.removeCallbacksAndMessages(null)
    }
}