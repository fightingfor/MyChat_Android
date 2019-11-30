package com.study.mychat_android.chat.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.study.mychat_android.db.ChatMessage
import com.study.mychat_android.http.datasource.ChatDetailDataSource
import com.study.mychat_android.http.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ChatDetailViewModel : BaseViewModel() {

    val TAG = "ChatDetailViewModel"


    val messageData = MutableLiveData<List<ChatMessage>>()
    val chatDetailDataSource = ChatDetailDataSource(this)
    fun getAllMessage(owerid: Int, targetId: Int) {
        GlobalScope.launch (Dispatchers.Main){
            val allChatMessage = chatDetailDataSource.getAllChatMessage(owerid, targetId)
            val allChatMessage2 = chatDetailDataSource.getAllChatMessage2(owerid, targetId)
            val list1 = allChatMessage.value?.let { it as ArrayList<ChatMessage> }
            val list2 = allChatMessage2.value?.let { it as  ArrayList<ChatMessage> }
            list2?.let { list1?.addAll(it) }

            messageData.value = list1
        }

        GlobalScope.launch {
            val allData = chatDetailDataSource.getAllData()
            Log.d(TAG,allData.toString())
        }
    }


}