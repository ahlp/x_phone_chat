package com.arthurpires.mom.momServer

import java.io.IOException
import java.net.ServerSocket

/**
 * Created by ceciliahunka on 18/11/17.
 */
class ServerRequestReader @Throws(IOException::class)
constructor(port: Int) {

    private val server: ServerSocket

    init {
        this.server = ServerSocket(port)
    }

    @Throws(IOException::class)
    fun accept(): ServerRequestHandler {
        return ServerRequestHandler(this.server.accept())
    }

    fun close() {
        try {
            this.server.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
