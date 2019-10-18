package com.study.mychat_android.http.viewmodel

/**
 * Created by jinguang on 2019/10/18.
 */

open class BaseEvent(open val action: Int)

class BaseViewModelEvent(override val action: Int) : BaseEvent(action) {

    companion object {

        const val SHOW_LOADING_DIALOG = 1

        const val DISMISS_LOADING_DIALOG = 2

        const val SHOW_TOAST = 3

        const val FINISH = 4

        const val POP = 5
    }

    var message: String = ""

}