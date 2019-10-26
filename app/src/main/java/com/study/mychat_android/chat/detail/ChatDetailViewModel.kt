package com.study.mychat_android.chat.detail

import androidx.lifecycle.MutableLiveData
import com.study.mychat_android.http.viewmodel.BaseViewModel
import com.study.mychat_android.model.MessageModel

class ChatDetailViewModel : BaseViewModel() {
    val messageData = MutableLiveData<MessageModel>()
    fun getMessage(msg:MessageModel){
        messageData.value = msg
    }

}