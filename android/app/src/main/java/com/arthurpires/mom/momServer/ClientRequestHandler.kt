package com.arthurpires.mom.momServer

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket
import java.net.UnknownHostException

/**
 * Created by ceciliahunka on 16/11/17.
 */
class ClientRequestHandler @Throws(UnknownHostException::class, IOException::class)
constructor(host: String, port: Int) {

    private var clientSocket: Socket? = null
    private var outToServer: DataOutputStream? = null
    private var inFromServer: DataInputStream? = null
    var isExpectedReply: Boolean = false

    init {
        this.initializeSockets(host, port)
    }

    @Throws(UnknownHostException::class, IOException::class)
    fun initializeSockets(host: String, port: Int) {
        this.clientSocket = Socket(host, port)
        this.outToServer = DataOutputStream(this.clientSocket!!.getOutputStream())
        this.inFromServer = DataInputStream(this.clientSocket!!.getInputStream())
    }

    @Throws(IOException::class, InterruptedException::class)
    fun send(msg: ByteArray) {
        this.outToServer!!.writeInt(msg.size)
        this.outToServer!!.write(msg)
        this.outToServer!!.flush()

        if (!this.isExpectedReply) {
            this.clientSocket!!.close()
            this.outToServer!!.close()
            this.inFromServer!!.close()
        }
    }

    @Throws(IOException::class, InterruptedException::class, ClassNotFoundException::class)
    fun receive(): ByteArray {

        val msgSize = inFromServer!!.readInt()
        val result = ByteArray(msgSize)
        val byteReads = this.inFromServer!!.read(result)

        if (byteReads == -1) {
            // error, dont read any byte
        }

        this.clientSocket!!.close()
        this.outToServer!!.close()
        this.inFromServer!!.close()

        return result
    }

    fun closeConecction() {
        try {
            this.clientSocket!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}
