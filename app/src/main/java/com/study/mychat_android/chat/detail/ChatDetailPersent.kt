package com.study.mychat_android.chat.detail

import com.study.mychat_android.db.ChatMessage
import com.study.mychat_android.db.LoadMessageCallback
import com.study.mychat_android.db.MessageRepository

/*
 *
 * Author: jinguang
 * Create: 2019/11/20 17:27
 * Description:
 */

class ChatDetailPersent {
    private lateinit var messageRepository: MessageRepository
    private lateinit var chatDetailView: ChatDetailView
    private lateinit var messageCallback: LoadMessageCallback

    constructor(messageRepository: MessageRepository, chatDetailView: ChatDetailView) {
        this.messageRepository = messageRepository
        this.chatDetailView = chatDetailView
        messageCallback = creatCallback()
    }

    fun getChatList(owerId: Int, targetId: Int) {
        messageRepository.getMessageList(owerId,targetId,messageCallback)
    }

   private fun creatCallback(): LoadMessageCallback {
        return object : LoadMessageCallback {
            override fun loadSuccess(list: List<ChatMessage>) {
                chatDetailView.getChatMessageSuccess(list)
            }

            override fun failed() {

            }

        }
    }
}