package com.mqtteam.bari2019.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mqtteam.bari2019.R
import com.mqtteam.bari2019.repository.FirebaseRepository
import kotlinx.android.synthetic.main.activity_clear_muletto.*

class ClearMuletto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clear_muletto)

        val id = intent.extras.getString("id")

        libera.setOnClickListener {
            FirebaseRepository.updateDisponibilityMuletto(id, false)
            finish()
        }

    }
}
