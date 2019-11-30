package com.study.mychat_android.db

/*
 *
 * Author: jinguang
 * Create: 2019/11/20 16:48
 * Description:
 */

interface MessageDataSource{
    fun getChatMessageList(owerId:Int,targetId :Int):List<ChatMessage>

    fun addChatMessage( message: ChatMessage)

    fun deleteChatMessage(message: ChatMessage)

    fun updateTodo( message: ChatMessage)
}