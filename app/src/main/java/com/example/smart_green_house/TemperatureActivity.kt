package com.example.smart_green_house

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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

class TemperatureActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private val delay = 1000L // 1000 ms = 1 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temperature)

        var suhu: TextView = findViewById(R.id.tvTemperature)
        handler = Handler(Looper.getMainLooper())

        handler.postDelayed(object : Runnable {
            override fun run() {
                // Panggil API suhu
                RetrofitClient.instance.getSuhu().enqueue(object : Callback<SuhuResponse> {
                    override fun onResponse(call: Call<SuhuResponse>, response: Response<SuhuResponse>) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            suhu.text = data?.temperature
                        } else {
                            Log.d("Data", "onResponse: Gagal")
                        }
                    }

                    override fun onFailure(call: Call<SuhuResponse>, t: Throwable) {
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