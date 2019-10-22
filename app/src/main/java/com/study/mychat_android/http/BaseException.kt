package com.study.mychat_android.http

/**
 * Created by jinguang on 2019/10/18.
 */

sealed class BaseException(errorMessage: String, val code: Int = HttpConfig.CODE_UNKNOWN) :
    RuntimeException(errorMessage){
}

class ServerResultException(message: String, code: Int = HttpConfig.CODE_UNKNOWN) : BaseException(message, code){

}