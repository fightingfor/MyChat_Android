package com.study.mychat_android.http

/**
 * Created by jinguang on 2019/10/18.
 */

interface RequestCallback<T> {

    fun onSuccess(data: T)

}

interface RequestMultiplyCallback<T> : RequestCallback<T> {

    fun onFail(e: BaseException)

}