package com.example.smart_green_house

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smart_green_house.Response.MessageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class   LampSwitchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lamp_switch)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var on: LinearLayout = findViewById(R.id.lampOn)
        var off: LinearLayout = findViewById(R.id.lampOff)
        var lampStatus: TextView = findViewById(R.id.lampStatus)
        var back: ImageView = findViewById(R.id.backLampSwitch)

        on.setOnClickListener {
            RetrofitClient.instance.getTurnOnLamp().enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (response.isSuccessful)
                    {
                        lampStatus.text = "On"
                    }
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })

        }

        off.setOnClickListener {
            RetrofitClient.instance.getTurnOffLamp().enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if(response.isSuccessful)
                    {
                        lampStatus.text = "Off"
                    }
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

        back.setOnClickListener {
            val options = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_right_in,  // Animasi untuk Activity yang baru
                R.anim.slide_out_left   // Animasi untuk Activity yang ditinggalkan
            )
            startActivity(Intent(applicationContext, LampActivity::class.java), options.toBundle())
        }
    }
}