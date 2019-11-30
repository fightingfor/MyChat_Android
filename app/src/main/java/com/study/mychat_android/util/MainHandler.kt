package com.study.mychat_android.util

import android.os.Handler
import android.os.Looper


class MainHandler {
    companion object {
        fun post(runnable: ()->Unit) {
            HanderHolder.handler.post(runnable)
        }

        fun postDelay(runnable: ()->Unit,delayMillis: Long) {
            HanderHolder.handler.postDelayed(runnable,delayMillis)
        }

        fun removeAll(){
            HanderHolder.handler.removeCallbacksAndMessages(null)
        }
    }

    private object HanderHolder{
        val handler = Handler(Looper.getMainLooper())
    }

}