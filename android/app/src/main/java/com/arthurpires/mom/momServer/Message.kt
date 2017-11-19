package com.arthurpires.mom.momServer

import java.io.Serializable

/**
 * Created by ceciliahunka on 16/11/17.
 */
class Message : Serializable {

    var header: MessageHeader? = null
    var body: MessageBody? = null
}
