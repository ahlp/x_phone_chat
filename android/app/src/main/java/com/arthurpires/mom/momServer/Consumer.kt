package com.arthurpires.mom.momServer

import java.io.IOException

/**
 * Created by ceciliahunka on 18/11/17.
 */
object Consumer {

    @Throws(IOException::class, InterruptedException::class, ClassNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {

        val queue01Proxy = QueueManagerProxy("queue01")

        for (messageNotReadIndex in 0..4) {
            val response = queue01Proxy.receive(messageNotReadIndex)
            println("Response is: " + response)
        }
    }

}
