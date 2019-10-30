package com.study.mychat_android.customview

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.EditText



/*
 *
 * Author: jinguang
 * Create: 2019/10/29 20:16
 * Description:
 */

class ChatEditText : EditText {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode ==  KeyEvent.KEYCODE_BACK && event?.action == 1){
            super.onKeyPreIme(keyCode, event)
            return false
        }
        return super.onKeyPreIme(keyCode, event)

    }

    /**
     * 键盘监听接口
     */
//    var onKeyBoardHideListener: OnKeyBoardHideListener?=null
//
//    interface OnKeyBoardHideListener {
//        fun onKeyHide(keyCode: Int, event: KeyEvent)
//    }
//
//    fun setOn

}