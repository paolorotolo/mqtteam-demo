package com.mqtteam.bari2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mqtteam.bari2019.data.Pacco
import com.mqtteam.bari2019.repository.FirebaseRepository
import kotlinx.android.synthetic.main.activity_check_in.*

class CheckInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        confirmButton.setOnClickListener {
            val pacco = Pacco().apply {
                name = nameET.text.toString()
                desc = descET.text.toString()
                type = typeET.text.toString()
            }

            // FirebaseRepository.addPacco(pacco)
        }
    }
}
