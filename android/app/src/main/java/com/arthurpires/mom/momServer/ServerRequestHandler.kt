package com.arthurpires.mom.momServer

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket

/**
 * Created by ceciliahunka on 17/11/17.
 */
class ServerRequestHandler @Throws(IOException::class)
constructor(connectionSocket: Socket) {

    private var connectionSocket: Socket? = null

    private var outToClient: DataOutputStream? = null
    private var inFromClient: DataInputStream? = null

    init {
        this.initializeSockets(connectionSocket)
    }

    @Throws(IOException::class)
    fun initializeSockets(connectionSocket: Socket) {
        this.connectionSocket = connectionSocket
        this.outToClient = DataOutputStream(this.connectionSocket!!.getOutputStream())
        this.inFromClient = DataInputStream(this.connectionSocket!!.getInputStream())
    }

    @Throws(IOException::class)
    fun receive(): ByteArray {
        val msgSize = inFromClient!!.readInt()
        val result = ByteArray(msgSize)
        val byteReads = this.inFromClient!!.read(result)

        if (byteReads == -1) {
            // error, dont read any byte
        }

        return result
    }

    @Throws(IOException::class)
    fun send(message: ByteArray) {
        this.outToClient!!.writeInt(message.size)
        this.outToClient!!.write(message)
        this.outToClient!!.flush()
    }

    fun closeConecction() {
        try {
            this.connectionSocket!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}
