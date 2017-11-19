package com.arthurpires.mom.momServer

/**
 * Created by ceciliahunka on 16/11/17.
 */
object QueueServer {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {

        val invoker = QueueInvoker()
        val manager = QueueManager()
        invoker.invoke(manager, 3000)

    }

}
