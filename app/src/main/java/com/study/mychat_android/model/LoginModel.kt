package com.study.mychat_android.model

data class LoginModel(
    val id: String,
    val token: String,
    val nickname: String,
    val createat: String
){
    override fun toString(): String {
        return super.toString()
    }
}