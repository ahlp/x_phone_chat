package com.example.arthurpires.x_phone_chat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var topic: String = "default"

    var publishTextField: EditText? = null
    var topicTextField: EditText? = null

    var chatView: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        publishTextField = findViewById(R.id.publishTextField)
        topicTextField = findViewById(R.id.topicTextField)
        chatView = findViewById(R.id.ChatView)

        thread {
            while (true) {
                // listen topic
                val newText = "alo"
                Thread.sleep(1000)
                runOnUiThread({
                    val newMsg = TextView(chatView!!.context)
                    newMsg.setText(newText)
                    chatView!!.addView(newMsg)
                })
            }
        }
    }

    fun addMsgToChat(msg: String) {

    }

    fun onSendButtonClick(view: View) {
        val publishText = publishTextField!!.getText().toString()
        publishTextField!!.setText("")
        thread {
            // publish publishText on topic
        }
    }

    fun onChangeRoomClick(view: View) {
        this.topic = topicTextField!!.text.toString()
        runOnUiThread({
            chatView!!.removeAllViews()
        })
    }
}
