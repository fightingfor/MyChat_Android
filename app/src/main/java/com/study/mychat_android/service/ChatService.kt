package com.study.mychat_android.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.study.mychat_android.expand.logger_d

/*
 *
 * Author: jinguang
 * Create: 2019/11/13 15:45
 * Description:
 */

class ChatService : Service() {

    val TAG = "ChatService"
    override fun onCreate() {
        super.onCreate()
        logger_d(TAG,"onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? {
        logger_d(TAG,"onBind")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logger_d(TAG,"onStartCommand")
        ChatServiceManager.onStartCommand(intent)
        return super.onStartCommand(intent, flags, startId)
    }
}