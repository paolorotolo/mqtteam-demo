package com.mqtteam.bari2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mqtteam.bari2019.helper.MqttHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.client.mqttv3.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    lateinit var mqttHelper: MqttHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeMqtt()

    }

    /**
     * Called each time a new message is available on MQTT
     */
    fun onNewMessageAvailable(topic: String, message: String){

    }

    /**
     * ********************
     * *** MQTT METHODS ***
     * ********************
     */

    private fun initializeMqtt(){
        mqttHelper = MqttHelper(this, "tcp://broker.shiftr.io", "AndroidClient")
        mqttHelper.setUsername("vault-tec-Bari-Hackaton")
        mqttHelper.setPassword("securepwd")
        mqttHelper.connect(object: IMqttActionListener{
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                mqttHelper.setListener(
                    IMqttMessageListener { topic, message ->
                        runOnUiThread {
                            onNewMessageAvailable(topic, message.toString())
                        }
                    }
                )
                mqttHelper.subscribeTopic("test")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                toast(exception?.message.toString())
            }
        })

    }
}
