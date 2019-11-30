package com.study.mychat_android.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 *
 * Author: jinguang
 * Create: 2019/11/13 20:09
 * Description:
 */

@Entity(tableName = "chat_message")
class ChatMessage {


    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "ower_name")//发送者name
    var owerName: String? = null

    @ColumnInfo(name = "ower_id")//发送者id
    var owerId: String? = null

    @ColumnInfo(name = "target_name")//接收者name
    var targetName: String? = null

    @ColumnInfo(name = "target_id")//接受者ID
    var targetId: Int? = null

    @ColumnInfo(name = "message_type")//消息类型
    var messageType: Int? = null

    @ColumnInfo(name = "is_success")//是否发送成功
    var isSuccess: Boolean = false

    @ColumnInfo(name = "message_txt")//消息内容
    var messageTxt: String? = null

    @ColumnInfo(name = "message_pic")//消息图片地址
    var messagePic: String? = null

    @ColumnInfo(name = "creat_time")//插入时间
    var creatTime: Long? = null

    override fun toString(): String {
        val  str ="id = $id>>owerName = $owerName>>owerId= $owerId>>targetName= $targetName>>targetId= $targetId>>messageType= $messageType>>messageTxt = $messageTxt>>creatTime = $creatTime"
        return str
    }
}