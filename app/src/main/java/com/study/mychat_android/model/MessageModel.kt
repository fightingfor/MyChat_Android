package com.study.mychat_android.model

data class MessageModel(
    val ownerId: String,
    val ownerName: String,
    val targetId: String,
    val targetName: String,
    val msg: String
)