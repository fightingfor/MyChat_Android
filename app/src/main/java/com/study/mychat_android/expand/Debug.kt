package com.study.mychat_android.expand

import android.util.Log

/**
 * Created by jinguang on 2019/10/18.
 */



fun isDebug(): Boolean = true

inline fun debug(run: () -> Unit) {
    if (isDebug()) {
        run()
    }
}

inline fun logger_v(tag: String, message: String) {
    if (isDebug()) {
        Log.v(tag, message)
    }
}

inline fun logger_i(tag: String, message: String) {
    if (isDebug()) {
        Log.i(tag, message)
    }
}

inline fun logger_d(tag: String, message: String) {
    if (isDebug()) {
        Log.d(tag, message)
    }
}

fun logger_w(tag: String, message: String) {
    if (isDebug()) {
        Log.w(tag, message)
    }
}

inline fun logger_e(tag: String, message: String) {
    if (isDebug()) {
        Log.e(tag, message)
    }
}