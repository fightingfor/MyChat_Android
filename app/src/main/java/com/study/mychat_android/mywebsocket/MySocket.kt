package com.study.mychat_android.mywebsocket


import com.study.mychat_android.expand.logger_d
import com.study.mychat_android.service.ChatServiceManager
import com.study.mychat_android.view.BaseActivity
import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class MySocket : WebSocketClient {


    private val TAG = "MySocket"
    private lateinit var mYActivity: BaseActivity

    constructor(serverUri: URI?) : super(serverUri)
    constructor(serverUri: URI?, protocolDraft: Draft?) : super(serverUri, protocolDraft)

    fun setCurrentAct(activity: BaseActivity) {
        mYActivity = activity
    }

    override fun onOpen(handshakedata: ServerHandshake?) {
        logger_d(TAG, "onopen")
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        logger_d(TAG, "onClose")

    }

    override fun onMessage(message: String?) {
//        val model = ViewModelProviders.of(mYActivity).get(ChatDetailViewModel::class.java)
//        model.messageData.postValue(MessageModel("", "", "", "", message ?: ""))
        logger_d(TAG, "onMessage>>>$message")

        ChatServiceManager.startService(ChatServiceManager.ACTION_RECEIVE_MESSAGE,message?:"")

    }

    override fun onError(ex: Exception?) {
        logger_d(TAG, "onError")

    }
}