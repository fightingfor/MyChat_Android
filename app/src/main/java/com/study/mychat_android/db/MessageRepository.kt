package com.study.mychat_android.db

import java.lang.ref.WeakReference

/*
 *
 * Author: jinguang
 * Create: 2019/11/20 16:55
 * Description:
 */

class MessageRepository(val appExecutors: AppExecutors, val messageDataSource: MessageDataSource) {

    /**
     * 获取聊天信息
     */
    fun getMessageList(owerId: Int, targetId: Int, callback: LoadMessageCallback) {
        val weakReference = WeakReference<LoadMessageCallback>(callback)
        appExecutors.diskIO().execute {
            val chatMessageList = messageDataSource.getChatMessageList(owerId, targetId)
            appExecutors.mainThread().execute {
                val messageCallback = weakReference.get() ?: return@execute

                if (chatMessageList.isNotEmpty()) {
                    messageCallback.loadSuccess(chatMessageList)
                }
                if (chatMessageList.isNullOrEmpty()) {
                    messageCallback.failed()
                }
            }
        }
    }

    /**
     * 插入聊天记录
     */
    fun addMessage(message: ChatMessage, callback: AddMessageCallback) {
        val weakReference = WeakReference<AddMessageCallback>(callback)
        appExecutors.diskIO().execute {
            val addChatMessage = messageDataSource.addChatMessage(message)

            appExecutors.mainThread().execute {
                val addMessageCallback = weakReference.get() ?: return@execute
                addMessageCallback.insertSuccess()
            }
        }
    }
}