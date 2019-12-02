package com.study.mychat_android.db

import androidx.lifecycle.LiveData
import androidx.room.*

/*
 *
 * Author: jinguang
 * Create: 2019/11/13 20:18
 * Description:
 */

@Dao
interface ChatMessageDao{

    @Query("SELECT * FROM chat_message WHERE ower_id = :owerId AND target_id = :targetId OR ower_id = :targetId AND target_id = :owerId")
    fun getChatMessageList(owerId:Int,targetId :Int):List<ChatMessage>

    @Query("SELECT * FROM chat_message WHERE ower_id = :owerId AND target_id = :targetId OR ower_id = :targetId AND target_id = :owerId ORDER BY creat_time")
    fun getChatMessageList2(owerId:Int,targetId :Int):LiveData<List<ChatMessage>>


    @Query("SELECT * FROM chat_message ORDER BY creat_time")
    fun getAllData():List<ChatMessage>
    @Insert
    fun addChatMessage( message: ChatMessage)

    @Delete
    fun deleteChatMessage(message: ChatMessage)

    @Update
    fun updateTodo( message: ChatMessage)
}