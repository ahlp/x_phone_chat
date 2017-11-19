package com.arthurpires.mom.momServer

import java.io.Serializable
import java.util.ArrayList

/**
 * Created by ceciliahunka on 16/11/17.
 */
class RequestPacketBody : Serializable {

    var parameters = ArrayList<Any>()
    var message: Message? = null
}
