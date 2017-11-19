package com.arthurpires.mom.momServer

import java.io.Serializable

/**
 * Created by ceciliahunka on 16/11/17.
 */
class RequestPacket : Serializable {

    var packetHeader: RequestPacketHeader? = null
    var packetBody: RequestPacketBody? = null
}
