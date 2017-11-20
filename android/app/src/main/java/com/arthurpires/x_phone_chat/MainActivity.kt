package com.arthurpires.x_phone_chat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.hnkalhp.momServer.QueueManagerProxy
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val DEFAULT_TOPIC: String = "default"

    var publishTextField: EditText? = null
    var topicTextField: EditText? = null

    var chatView: LinearLayout? = null

    var queueProxy: QueueManagerProxy? = null
    var publisher: QueueManagerProxy? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.queueProxy = QueueManagerProxy(DEFAULT_TOPIC)
        this.publisher = QueueManagerProxy(DEFAULT_TOPIC)

        this.publishTextField = findViewById(R.id.publishTextField)
        this.topicTextField = findViewById(R.id.topicTextField)
        this.chatView = findViewById(R.id.ChatView)

        thread {

            val sub: Subscriber = Subscriber({ msg -> addMsgToChat(msg) })

            queueProxy!!.receive(sub)

        }

//        thread {
//            val invoker = QueueInvoker()
//            val manager = QueueManager()
//            invoker.invoke(manager, 3000)
//        }
    }

    fun addMsgToChat(msg: String) {
        if(!msg.equals("")){
            runOnUiThread({
                val newMsg = TextView(chatView!!.context)
                newMsg.setText(msg)
                chatView!!.addView(newMsg)
            })
        }
    }

    fun onSendButtonClick(view: View) {
        val publishText = publishTextField!!.getText().toString()
        publishTextField!!.setText("")
        thread {
            this.publisher!!.send(publishText)
        }
    }

    fun onChangeRoomClick(view: View) {
        val topic = topicTextField!!.text.toString()
        this.queueProxy = QueueManagerProxy(topic)
        this.publisher = QueueManagerProxy(topic)
        runOnUiThread({
            chatView!!.removeAllViews()
        })
    }
}
