package com.mqtteam.bari2019.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mqtteam.bari2019.R
import com.mqtteam.bari2019.helper.MqttHelper
import com.mqtteam.bari2019.repository.FirebaseRepository

class MainActivity : AppCompatActivity() {
    lateinit var mqttHelper: MqttHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseRepository.addCarrelloMock()

    }
}
