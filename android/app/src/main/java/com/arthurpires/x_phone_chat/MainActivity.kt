package com.arthurpires.x_phone_chat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.hnkalhp.momServer.QueueInvoker
import com.hnkalhp.momServer.QueueManager
import com.hnkalhp.momServer.QueueManagerProxy
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val DEFAULT_TOPIC: String = "default"

    var publishTextField: EditText? = null
    var topicTextField: EditText? = null

    var chatView: LinearLayout? = null

    var queueProxy: QueueManagerProxy? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.queueProxy = QueueManagerProxy(DEFAULT_TOPIC)

        this.publishTextField = findViewById(R.id.publishTextField)
        this.topicTextField = findViewById(R.id.topicTextField)
        this.chatView = findViewById(R.id.ChatView)

        thread {
            Thread.sleep(1000)
            while (true) {
                // listen topic
                val newText = queueProxy!!.receive(0)
                Thread.sleep(1000)
                if(!newText.equals("")){
                    runOnUiThread({
                        val newMsg = TextView(chatView!!.context)
                        newMsg.setText(newText)
                        chatView!!.addView(newMsg)
                    })
                }
            }
        }

        thread {
            val invoker = QueueInvoker()
            val manager = QueueManager()
            invoker.invoke(manager, 3000)
        }
    }

    fun onSendButtonClick(view: View) {
        val publishText = publishTextField!!.getText().toString()
        publishTextField!!.setText("")
        thread {
            this.queueProxy!!.send(publishText)
        }
    }

    fun onChangeRoomClick(view: View) {
        val topic = topicTextField!!.text.toString()
        this.queueProxy = QueueManagerProxy(topic)
        runOnUiThread({
            chatView!!.removeAllViews()
        })
    }
}
