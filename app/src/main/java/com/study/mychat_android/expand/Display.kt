package com.study.mychat_android.expand

import android.content.Context

/**
 * Created by jinguang on 2019/10/18.
 */

/**
 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
 */
fun dip2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}
