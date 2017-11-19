package com.arthurpires.mom.momServer

import java.io.IOException
import java.util.ArrayList

/**
 * Created by ceciliahunka on 16/11/17.
 */
class QueueManagerProxy(queueName: String) : IQueueManager {

    private val queueName: String? = queueName

    // stores messages on queue
    @Throws(IOException::class, InterruptedException::class)
    override fun send(m: String) {

        val handler = ClientRequestHandler("localhost", 3000)
        handler.isExpectedReply = false
        val marshaller = Marshaller()
        val packet = RequestPacket()
        val message = Message()

        // queue name on message header
        message.header = MessageHeader(this.queueName)
        message.body = MessageBody(m)

        val packetBody = RequestPacketBody()
        val parameters = ArrayList<Any>(0)

        packetBody.parameters = parameters
        packetBody.message = message
        packet.packetHeader = RequestPacketHeader("send")
        packet.packetBody = packetBody

        val packetObj = packet as Object
        handler.send(marshaller.marshall(packetObj))
    }

    // retrieve messages on queue
    @Throws(IOException::class, InterruptedException::class, ClassNotFoundException::class)
    override fun receive(index: Int): String {
        // finish implementation
        // client request handler send + receive

        val handler = ClientRequestHandler("192.168.15.5", 3000)
        handler.isExpectedReply = true

        val marshaller = Marshaller()
        val packet = RequestPacket()
        val message = Message()

        message.header = MessageHeader(this.queueName)
        message.body = MessageBody("")

        val packetBody = RequestPacketBody()
        val parameters = ArrayList<Any>()
        parameters.add(index)

        packetBody.parameters = parameters
        packetBody.message = message
        packet.packetHeader = RequestPacketHeader("receive")
        packet.packetBody = packetBody

        val packetObj = packet as Object
        handler.send(marshaller.marshall(packetObj))

        val response = handler.receive()
        val messageUnmarshalled = marshaller.unmarshall(response) as ReplyPacket

        return messageUnmarshalled.body!!.body!!.body!!
    }
}
