package com.arthurpires.mom.momServer

import java.io.IOException

/**
 * Created by ceciliahunka on 18/11/17.
 */
object Producer {

    @Throws(IOException::class, InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val queue01Proxy = QueueManagerProxy("queue01")

        for (loop in 0..4) {
            queue01Proxy.send("sending message " + loop)
        }

    }
}
