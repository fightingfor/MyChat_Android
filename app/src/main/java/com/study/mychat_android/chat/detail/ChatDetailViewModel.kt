package com.study.mychat_android.chat.detail

import androidx.lifecycle.LiveData
import com.study.mychat_android.db.*
import com.study.mychat_android.http.datasource.ChatDetailDataSource
import com.study.mychat_android.http.viewmodel.BaseViewModel

class ChatDetailViewModel : BaseViewModel() {

    val dataSource = ChatDetailDataSource(this)
    /**
     * 获取所有聊天信息
     */
    fun getAllMessage(owerid: Int, targetId: Int): LiveData<List<ChatMessage>> {

        return  dataSource.getAllChatMessage(owerid, targetId)
    }


}