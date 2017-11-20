package com.arthurpires.x_phone_chat

import com.hnkalhp.momServer.ISubscriber

/**
 * Created by arthurpires on 19/11/17.
 */
class Subscriber : ISubscriber {

    var lambda: (String) -> Unit

    constructor(lambda: (String) -> Unit) {
        this.lambda = lambda
    }

    override fun receiveMessage(var1: String) {
        this.lambda(var1)
    }
}