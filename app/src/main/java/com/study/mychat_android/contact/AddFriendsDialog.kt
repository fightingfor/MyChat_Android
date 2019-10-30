package com.study.mychat_android.contact

import android.app.Dialog
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import com.study.mychat_android.R
import kotlinx.android.synthetic.main.dialog_addfriend.*

class AddFriendsDialog : Dialog {
    constructor(context: Context) : this(context, R.style.App_Dialog)

    constructor(context: Context, style: Int) : super(context, style)

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        val dialogView = View.inflate(context, R.layout.dialog_addfriend, null)
        setContentView(dialogView)
        setCancelable(true)
        setCanceledOnTouchOutside(true)

        val window = window
        val layoutParams = window?.attributes
        val m = window?.windowManager
        val displayMetrics = DisplayMetrics()
        m?.defaultDisplay?.getMetrics(displayMetrics) // 获取屏幕宽、高用
        layoutParams?.width = (displayMetrics.widthPixels * 0.8f).toInt()
//        layoutParams?.height = (displayMetrics.heightPixels * 0.33).toInt()
        window?.attributes = layoutParams

        btn_add?.setOnClickListener {
            val mobile = et_add?.text.toString()
            listener?.OnAddClick(mobile)
        }
    }

    var listener: OnclickListener? = null
    fun setOnAddClickListener(listener: OnclickListener) {
        this.listener = listener
    }

    interface OnclickListener {
        fun OnAddClick(mobile: String)
    }

}