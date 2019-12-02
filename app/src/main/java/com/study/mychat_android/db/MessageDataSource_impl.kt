package com.study.mychat_android.db

import androidx.lifecycle.LiveData

/*
 *
 * Author: jinguang
 * Create: 2019/11/20 16:50
 * Description:
 */

class MessageDataSource_impl(val dao: ChatMessageDao) : MessageDataSource {


    companion object{
        var source: MessageDataSource_impl? = null
        fun getDataSourceInstance(): MessageDataSource_impl {

            synchronized(MessageDataSource_impl::class.java) {
                if (source == null) {
                    source = MessageDataSource_impl(AppDatabase.getDababaseInstance().chatMessageDao())
                }
                return source as MessageDataSource_impl
            }
        }
    }


    override fun getChatMessageList(owerId: Int, targetId: Int): List<ChatMessage> {
        return dao.getChatMessageList(owerId, targetId)
    }


    override fun getChatMessageList2(owerId: Int, targetId: Int): LiveData<List<ChatMessage>> {
        return dao.getChatMessageList2(owerId,targetId)
    }

    override fun addChatMessage(message: ChatMessage) {
         dao.addChatMessage(message)
    }

    override fun deleteChatMessage(message: ChatMessage) {

        dao.deleteChatMessage(message)
    }

    override fun updateTodo(message: ChatMessage) {

        dao.updateTodo(message)
    }

}