package com.study.mychat_android.db

/*
 *
 * Author: jinguang
 * Create: 2019/11/20 17:10
 * Description:
 */

interface LoadMessageCallback {
    fun loadSuccess(list: List<ChatMessage>)
    fun failed()
}

interface AddMessageCallback {
    fun insertSuccess()
    fun failed()
}