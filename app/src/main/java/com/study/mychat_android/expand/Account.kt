package com.study.mychat_android.expand

import com.study.mychat_android.model.LoginModel
import com.study.mychat_android.util.SharedPref
import com.study.mychat_android.util.SharedPrefKeyManager

/**
 * Created by jinguang on 2019/10/18.
 */


fun saveAccount(loginmodel: LoginModel) {
    setToken(loginmodel.token)
    setUserId(loginmodel.id)
    setNickname(loginmodel.nickname)
}



fun clearAccount() {
    setToken("")
    setUserId("")
    setNickname("")
}

fun getToken(): String = SharedPref.getString(SharedPrefKeyManager.KEY_TOKEN, "") ?: ""

fun setToken(token: String) {
    SharedPref.setString(SharedPrefKeyManager.KEY_TOKEN, token)
}

fun getUserId(): String = SharedPref.getString(SharedPrefKeyManager.KEY_USER_ID, "") ?: ""

fun setUserId(userid: String) {
    SharedPref.setString(SharedPrefKeyManager.KEY_USER_ID, userid)
}

fun setNickname(nickname: String) {
    SharedPref.setString(SharedPrefKeyManager.KEY_NICK_NAME, nickname)
}
fun getNickname() = SharedPref.getString(SharedPrefKeyManager.KEY_NICK_NAME, "")?:""

fun isTokenValid(): Boolean {
    if (isEmpty(getToken())) {
        return false
    }
    return true
}