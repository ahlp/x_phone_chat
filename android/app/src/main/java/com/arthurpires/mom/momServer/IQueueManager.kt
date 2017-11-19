package com.arthurpires.mom.momServer

import java.io.IOException

/**
 * Created by ceciliahunka on 16/11/17.
 */
interface IQueueManager {

    @Throws(IOException::class, InterruptedException::class)
    fun send(msg: String)

    @Throws(IOException::class, InterruptedException::class, ClassNotFoundException::class)
    fun receive(index: Int): String

}
