package com.arthurpires.mom.momServer

import java.util.ArrayList


/**
 * Created by ceciliahunka on 16/11/17.
 */
class Queue {

    private var queueName = ""
    private val queue = ArrayList<Message>()

    constructor(queueName: String) {
        this.queueName = queueName
    }

    fun enqueue(msg: Message) {
        this.queue.add(msg)
    }

    fun dequeue(index: Int): Message {
        if (this.queue.size === 0 || this.queueSize() < index + 1) {
            val empty = Message()
            empty.header = MessageHeader(this.queueName)
            empty.body = MessageBody("")

            return empty
        } else {
            val message = this.queue[0]
            return message
        }
        val message = this.queue[0]

        return message
    }

    fun queueSize(): Int {
        return this.queue.size
    }
}
