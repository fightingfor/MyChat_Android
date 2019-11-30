package com.study.mychat_android.service

import android.content.Intent
import com.study.mychat_android.MainApplication
import com.study.mychat_android.db.*
import com.study.mychat_android.expand.getUserId
import com.study.mychat_android.expand.logger_d
import com.study.mychat_android.expand.parseData
import com.study.mychat_android.http.HttpConfig
import com.study.mychat_android.mywebsocket.MySocket
import java.net.URI

/*
 *
 * Author: jinguang
 * Create: 2019/11/13 15:47
 * Description:
 */
class ChatServiceManager {
    companion object {

        const val TAG = "ChatServiceManager"
        const val ACTION_CONNECTION = "connection"//建立连接
        const val ACTION_CONNECTION_CLOSE = "connection_close"//关闭连接
        const val ACTION_SEND_MESSAGE = "send_message"//发送消息
        const val ACTION_RECEIVE_MESSAGE = "receive_message"//接收消息
        const val ACTION_SAVE_MESSAGE = "save_message"//保存消息
        const val MESSAGE = "message"

        /**
         * 开启服务 传入相应操作指示 action
         */
        fun startService(action: String, message: String = "") {
            val appContext = MainApplication.getAppContext()
            val intent = Intent(appContext, ChatService::class.java)
            intent.putExtra(MESSAGE, message)
            intent.action = action
            appContext.applicationContext.startService(intent)
        }

        /**
         * 根据接收到是action 做出处理
         */
        fun onStartCommand(intent: Intent?) {
            when (intent?.action) {
                ACTION_CONNECTION -> { //创建连接
                    logger_d(TAG, "创建连接")
                    chatConnect()
                }
                ACTION_CONNECTION_CLOSE ->{
                    closeChatConnect()
                }
                ACTION_SEND_MESSAGE -> {//发送消息
                    logger_d(TAG, "发送消息")
                    val message = intent.getStringExtra(MESSAGE) ?: ""
                    sendMessage(message)
                }
                ACTION_RECEIVE_MESSAGE -> { //接收消息
                    logger_d(TAG, "接收消息")
                    val message = intent.getStringExtra(MESSAGE) ?: ""
                    receiveMessage(message)
                }
                ACTION_SAVE_MESSAGE -> {//保存消息
                    logger_d(TAG, "保存消息")
                    val message = intent.getStringExtra(MESSAGE) ?: ""
                    saveMessage(message)
                }
            }
        }


        /**
         * 接收消息
         */
        private fun receiveMessage(message: String) {
            logger_d(TAG, "接收消息>>>$message")
            saveMessage(message)
        }

        /**
         *保存消息
         */
        private fun saveMessage(message: String) {
            logger_d(TAG, "保存消息>>>$message")
            val parseData = parseData(message, ChatMessage::class.java)
            val messageRepository =
                MessageRepository(AppExecutors(), MessageDataSource_impl.getDataSourceInstance())
            messageRepository.addMessage(parseData, object : AddMessageCallback {
                override fun insertSuccess() {
                    logger_d(TAG, "数据保存成功")
                }

                override fun failed() {
                    logger_d(TAG, "数据保存失败")
                }

            })

        }

        /**
         * 创建socket连接
         */
        private var mySocket: MySocket? = null

        fun chatConnect() {
            if (mySocket == null || mySocket?.isClosed!!) {
                mySocket =
                    MySocket(URI("${HttpConfig.WEB_SOCKET_URL}/api/chat?ownerId=${getUserId()}&targetId="))
                mySocket?.connect()
            }
        }

        /**
         * 关闭链接
         */
        fun closeChatConnect(){
            if (mySocket != null && !mySocket?.isClosed!!){
                mySocket?.close()
            }
        }

        /**
         * 发送消息
         */
        fun sendMessage(message: String) {
            val open = mySocket?.isOpen ?: false
            if (open) {
                mySocket?.send(message)//发消息成功
                saveMessage(message)
            } else {
                startService(ACTION_CONNECTION)//发消息失败
            }
        }


    }
}