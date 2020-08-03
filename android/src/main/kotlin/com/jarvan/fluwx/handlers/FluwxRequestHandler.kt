package com.jarvan.fluwx.handlers

import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX
import io.flutter.plugin.common.MethodChannel

object FluwxRequestHandler {

    private var channel: MethodChannel? = null


    fun setMethodChannel(channel: MethodChannel) {
        FluwxRequestHandler.channel = channel
    }

    fun handleRequest(req: BaseReq) {
        when (req) {
            is ShowMessageFromWX.Req -> handleShowMessage(req)


        }
    }

    private fun handleShowMessage(req: ShowMessageFromWX.Req) {
        val mediaMsg = req.message
        val extInfo = mediaMsg.messageExt
        val result = {
            "extInfo" to extInfo
        }

        channel?.invokeMethod("onShowMessageReq", result)
    }
}