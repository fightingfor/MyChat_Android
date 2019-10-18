package com.study.mychat_android.http.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mychat_android.http.IBaseViewModelEvent

/**
 * Created by jinguang on 2019/10/18.
 */

open class BaseViewModel : ViewModel(), IBaseViewModelEvent {

    val baseActionEvent = MutableLiveData<BaseViewModelEvent>()

    override fun showLoading(msg: String) {
        val event =
            BaseViewModelEvent(BaseViewModelEvent.SHOW_LOADING_DIALOG)
        event.message = msg
        baseActionEvent.value = event
    }

    override fun dismissLoading() {
        val event =
            BaseViewModelEvent(BaseViewModelEvent.DISMISS_LOADING_DIALOG)
        baseActionEvent.value = event
    }

    override fun showToast(msg: String) {
        val event =
            BaseViewModelEvent(BaseViewModelEvent.SHOW_TOAST)
        event.message = msg
        baseActionEvent.value = event
    }

    override fun finishView() {
        val event =
            BaseViewModelEvent(BaseViewModelEvent.FINISH)
        baseActionEvent.value = event
    }

    override fun pop() {
        val event =
            BaseViewModelEvent(BaseViewModelEvent.POP)
        baseActionEvent.value = event
    }

}