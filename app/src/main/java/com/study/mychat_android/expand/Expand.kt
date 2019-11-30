package com.study.mychat_android.expand

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.gson.GsonBuilder
import com.study.mychat_android.MainApplication
import com.study.mychat_android.R
import com.study.mychat_android.util.AppEnv
import kotlinx.android.synthetic.main.layout_toast_view.view.*

/**
 * Created by jinguang on 2019/10/18.
 */

 fun toast(
    message: CharSequence, @DrawableRes drawableLeft: Int = 0, @DrawableRes drawableTop: Int = 0,
    @DrawableRes drawableRight: Int = 0, @DrawableRes drawableBottom: Int = 0, duration: Int = Toast.LENGTH_SHORT
) {
    // try-catch解决在8.0版本以下NotificationManager可能取消Token,
    // 因此尝试添加View到Window的时候可能会出问题，只能通过Try-Catch解决(8.0以及以上版本已经解决和这个问题)
    try {
        val toast = Toast.makeText(MainApplication.getAppContext(), message, duration)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = duration
        val view = LayoutInflater.from(MainApplication.getAppContext())
            .inflate(R.layout.layout_toast_view, null, false)
        view.tv_toast_text.text = message
        view.tv_toast_text.setCompoundDrawablesWithIntrinsicBounds(
            drawableLeft,
            drawableTop,
            drawableRight,
            drawableBottom
        )
        toast.view = view
        toast.show()
    } catch (e: Exception) {
        // Nothing to do
        if (AppEnv.DEBUG) {
            logger_e("debug_toast", "exception = $e")
        }
    }
}


fun toast(@StringRes stringId: Int, @DrawableRes icon: Int = 0, duration: Int = Toast.LENGTH_SHORT) {
    // try-catch解决在8.0版本以下NotificationManager可能取消Token,
    // 因此尝试添加View到Window的时候可能会出问题，只能通过Try-Catch解决(8.0以及以上版本已经解决和这个问题)
    try {

        toast(MainApplication.getAppContext().getString(stringId), icon, duration)
    } catch (e: Exception) {
        // Nothing to do
    }
}

fun isEmpty(str: String?): Boolean {
    return str == null || str.isEmpty()
}

fun isNotEmpty(str: String): Boolean {
    return str.isNotEmpty()
}


/**
 * 隐藏软键盘
 */
fun hideSoftInput(activity: Activity) {
//    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(context
//            .currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
}

/**
 * 显示软键盘
 * @param view
 */
fun showSoftInput(view: View) {
    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}


fun <T : Any?> parseData(jsonMsg:String, classOfT:Class<T>):T{
    val gsonBuilder = GsonBuilder().create()
    val fromJson = gsonBuilder.fromJson(jsonMsg, classOfT)
    return fromJson
}