package com.arthurpires.mom.momServer

import java.io.Serializable

/**
 * Created by ceciliahunka on 16/11/17.
 */
class MessageHeader(// nome da fila em que a mensagem é armazenada
        var destination: String?) : Serializable
