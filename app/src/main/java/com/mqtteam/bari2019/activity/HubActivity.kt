package com.mqtteam.bari2019.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mqtteam.bari2019.R
import com.mqtteam.bari2019.helper.MqttHelper
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.jetbrains.anko.toast

class HubActivity : AppCompatActivity() {
    lateinit var mqttHelper: MqttHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub)
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

    private fun initializeMqtt() {
        mqttHelper = MqttHelper(this, "tcp://broker.shiftr.io", "AndroidClient")
        mqttHelper.setUsername("vault-tec-Bari-Hackaton")
        mqttHelper.setPassword("securepwd")
        mqttHelper.connect(object : IMqttActionListener {
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
