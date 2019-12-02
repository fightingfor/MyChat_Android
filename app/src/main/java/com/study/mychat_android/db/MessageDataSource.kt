package com.study.mychat_android.db

import androidx.lifecycle.LiveData

/*
 *
 * Author: jinguang
 * Create: 2019/11/20 16:48
 * Description:
 */

interface MessageDataSource{
    fun getChatMessageList(owerId:Int,targetId :Int):List<ChatMessage>

    fun getChatMessageList2(owerId:Int,targetId :Int):LiveData<List<ChatMessage>>

    fun addChatMessage( message: ChatMessage)

    fun deleteChatMessage(message: ChatMessage)

    fun updateTodo( message: ChatMessage)
}