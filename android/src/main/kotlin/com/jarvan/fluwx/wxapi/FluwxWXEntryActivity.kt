/*
 * Copyright (C) 2020 The OpenFlutter Organization
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jarvan.fluwx.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.jarvan.fluwx.handlers.FluwxRequestHandler
import com.jarvan.fluwx.handlers.FluwxResponseHandler
import com.jarvan.fluwx.handlers.RegisterListener
import com.jarvan.fluwx.handlers.WXAPiHandler
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler


open class FluwxWXEntryActivity : Activity(), IWXAPIEventHandler, RegisterListener {

    // IWXAPI 是第三方app和微信通信的openapi接口

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            if (WXAPiHandler.wxApi == null) {
                startSpecifiedActivity()
                WXAPiHandler.setRegisterListener(this)
                return
            }
            WXAPiHandler.wxApi?.handleIntent(intent, this)
        } catch (e: Exception) {
            e.printStackTrace()
            startSpecifiedActivity()
//            finish()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        setIntent(intent)
        try {
            WXAPiHandler.wxApi?.handleIntent(intent, this)
        } catch (e: Exception) {
            e.printStackTrace()
            startSpecifiedActivity()
        }
    }


    override fun onReq(req: BaseReq) {
        if (req.type == ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX) {
            FluwxRequestHandler.handleRequest(req);
        }

        finish()
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    override fun onResp(resp: BaseResp) {
        FluwxResponseHandler.handleResponse(resp)
        finish()
    }

    private fun startSpecifiedActivity() {
        Intent(this, Class.forName("$packageName.MainActivity")).run {
            startActivity(this)
        }


        finish()
    }

    override fun onRegister() {
        WXAPiHandler.wxApi?.handleIntent(intent, this)
        WXAPiHandler.setRegisterListener(null)
    }
}