package com.arthurpires.x_phone_chat

import com.hnkalhp.momServer.ISubscriber

/**
 * Created by arthurpires on 19/11/17.
 */
class Subscriber : ISubscriber {

    var lambda: (var1: String) -> Any

    constructor(lambda: (var1: String) -> Any) {
        this.lambda = lambda
    }

    override fun receiveMessage(var1: String) {
        this.lambda(var1)
    }
}