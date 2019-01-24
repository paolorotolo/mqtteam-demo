package com.mqtteam.bari2019.helper

import android.content.Context
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*


class MqttHelper(val context: Context, private val serverUri: String, private val clientId: String) {
    private val mqttConnectOptions: MqttConnectOptions
    private var mqttListener: IMqttMessageListener? = null

    private val mqttAndroidClient: MqttAndroidClient = MqttAndroidClient(context, serverUri, clientId)

    init {
        this.mqttConnectOptions = MqttConnectOptions()
        mqttConnectOptions.setAutomaticReconnect(true)
        mqttConnectOptions.isCleanSession = false
    }

    @Throws(MqttException::class)
    fun connect(listener: IMqttActionListener) {
        mqttAndroidClient.connect(mqttConnectOptions, null, listener)
    }

    fun setUsername(username: String) {
        mqttConnectOptions.userName = username
    }

    fun setPassword(password: String) {
        mqttConnectOptions.password = password.toCharArray()
    }

    fun setListener(mqttActionListener: IMqttMessageListener) {
        mqttListener = mqttActionListener
    }


    fun subscribeTopic(topic: String) {
        mqttAndroidClient.subscribe(topic, 0, mqttListener)
    }

    @Throws(MqttException::class)
    fun publishMessage(topic: String, payload: String) {
        val message = MqttMessage()
        message.payload = payload.toByteArray()
        mqttAndroidClient.publish(topic, message)
    }

    @Throws(MqttException::class)
    fun publishMessage(topic: String, payload: ByteArray) {
        val message = MqttMessage()
        message.payload = payload
        mqttAndroidClient.publish(topic, message)
    }

}