package com.arthurpires.mom.momServer

import java.util.HashMap



/**
 * Created by ceciliahunka on 16/11/17.
 */
class QueueManager {

    var host: String? = null
    var port: Int = 0
    internal var queues: MutableMap<String, Queue> = HashMap()

    fun getQueues(): Map<String, Queue> {
        return queues
    }

    fun getQueue(queueName: String): Queue {
        val queueExists = this.queues.containsKey(queueName)
        if (!queueExists) {
            this.queues.put(queueName, Queue(queueName))
        }
        return this.queues[queueName!!]!!
    }
}
