package com.study.mychat_android.http

import com.study.mychat_android.expand.toast
import io.reactivex.observers.DisposableObserver
import com.study.mychat_android.http.model.OptionT

/**
 * Created by jinguang on 2019/10/18.
 */

class BaseSubscriber<T> constructor(private val requestCallback: RequestCallback<T>) :
    DisposableObserver<OptionT<T>>() {

    override fun onNext(t: OptionT<T>) {
        requestCallback.onSuccess(t.value)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        val msg = e.message ?: "未知错误"
        if (requestCallback is RequestMultiplyCallback) {
            if (e is BaseException) {
                requestCallback.onFail(e)
            } else {
                requestCallback.onFail(ServerResultException(msg))
            }
        } else {
            toast(msg)
        }
    }

    override fun onComplete() {

    }

}