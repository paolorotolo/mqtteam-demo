package com.mqtteam.bari2019.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mqtteam.bari2019.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hubButton.setOnClickListener {
            startActivity(Intent(this, HubActivity::class.java))
        }

        localizeButton.setOnClickListener {
            startActivity(Intent(this, RecycleViewActivity::class.java))
        }



    }
}
