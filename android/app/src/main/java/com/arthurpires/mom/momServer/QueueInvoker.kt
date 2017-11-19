package com.arthurpires.mom.momServer

import java.io.IOException

/**
 * Created by ceciliahunka on 18/11/17.
 */
class QueueInvoker {

    @Throws(IOException::class, ClassNotFoundException::class)
    operator fun invoke(queueManager: QueueManager, port: Int) {

        val requestReader = ServerRequestReader(port)

        var messageToBeUnmarshalled: ByteArray? = null
        var messageMarshalled: ByteArray? = null
        var messageUnmarshalled = RequestPacket()

        val marshaller = Marshaller()

        while (true) {
            val requestHandler = requestReader.accept()
            messageToBeUnmarshalled = requestHandler.receive()
            messageUnmarshalled = marshaller.unmarshall(messageToBeUnmarshalled) as RequestPacket

            when (messageUnmarshalled.packetHeader!!.operation) {
                "send" -> {
                    val message = messageUnmarshalled.packetBody!!.message
                    val queueName = message!!.header!!.destination

                    val queue = queueManager.getQueue(queueName!!)
                    queue.enqueue(message)
                }
                "receive" -> {
                    val packetBody = messageUnmarshalled.packetBody!!
                    val receiveMessage = packetBody.message
                    val index = packetBody.parameters[0] as Int

                    val queueNameReceive = receiveMessage!!.header!!.destination

                    val queueReceive = queueManager.getQueue(queueNameReceive!!)
                    val messageToSend = queueReceive.dequeue(index)

                    val packet = ReplyPacket()
                    packet.body = messageToSend

                    messageMarshalled = marshaller.marshall(packet)
                    requestHandler.send(messageMarshalled)
                }
                else -> println("Not a suitable method. Try again.")
            }

        }

    }

}
