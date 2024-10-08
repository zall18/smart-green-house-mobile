package com.example.smart_green_house

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
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

class HumidityActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private val delay = 1000L // 1000 ms = 1 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_humidity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var kelembapan: TextView = findViewById(R.id.tvHumidity)
        handler = Handler(Looper.getMainLooper())

        handler.postDelayed(object : Runnable {
            override fun run() {

                // Panggil API kelembapan
                RetrofitClient.instance.getkelembapan().enqueue(object :
                    Callback<KelembapanResponse> {
                    override fun onResponse(call: Call<KelembapanResponse>, response: Response<KelembapanResponse>) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            kelembapan.text = data?.humidity + "%"
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

        var back: ImageView =findViewById(R.id.backHumidity)

        back.setOnClickListener {
            val options = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_right_in,  // Animasi untuk Activity yang baru
                R.anim.slide_out_left   // Animasi untuk Activity yang ditinggalkan
            )
            startActivity(Intent(application, MainActivity::class.java), options.toBundle())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Hentikan handler saat activity dihancurkan
        handler.removeCallbacksAndMessages(null)
    }

}
