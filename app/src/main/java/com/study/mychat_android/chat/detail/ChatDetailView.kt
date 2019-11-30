package com.study.mychat_android.chat.detail

import com.study.mychat_android.db.ChatMessage

/*
 *
 * Author: jinguang
 * Create: 2019/11/20 17:28
 * Description:
 */

interface ChatDetailView{


    fun getChatMessageSuccess(list:List<ChatMessage>)


}