package com.example.smart_green_house

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LampActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lamp)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var back: ImageView = findViewById(R.id.backLamp)

        back.setOnClickListener {
            val options = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_right_in,  // Animasi untuk Activity yang baru
                R.anim.slide_out_left   // Animasi untuk Activity yang ditinggalkan
            )
            startActivity(Intent(application, MainActivity::class.java), options.toBundle())
        }

        var lamp1: LinearLayout = findViewById(R.id.lamp1)

        lamp1.setOnClickListener {
            val options = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_right_in,  // Animasi untuk Activity yang baru
                R.anim.slide_out_left   // Animasi untuk Activity yang ditinggalkan
            )
            startActivity(Intent(applicationContext, LampSwitchActivity::class.java), options.toBundle())
        }

    }
}