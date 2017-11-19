package com.arthurpires.mom.momServer

import java.io.*

/**
 * Created by ceciliahunka on 16/11/17.
 */
class Marshaller {

    @Throws(IOException::class)
    fun marshall(obj: Any): ByteArray {

        val outputStream = ByteArrayOutputStream()
        val objectStream = ObjectOutputStream(outputStream)
        objectStream.writeObject(obj)

        return outputStream.toByteArray()
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    fun unmarshall(obj: ByteArray): Any {

        val inputStream = ByteArrayInputStream(obj)
        val objectStream = ObjectInputStream(inputStream)

        return objectStream.readObject() as Any
    }
}
