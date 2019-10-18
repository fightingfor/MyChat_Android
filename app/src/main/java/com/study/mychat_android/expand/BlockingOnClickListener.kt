package com.study.mychat_android.expand

import android.view.View

/**
 * Created by jinguang on 2019/10/18.
 */


fun View.setBlockingOnClickListener(listener: View.OnClickListener) {
    setOnClickListener(object : OKashBlockingOnClickListener() {
        override fun onValidClick(view: View?) {
            listener.onClick(view)
        }
    })
}


abstract class OKashBlockingOnClickListener(
    private val thresholdMs: Long = 1000L
) : View.OnClickListener {
    private var preTimesMill = 0L

    abstract fun onValidClick(view: View?)

    final override fun onClick(view: View?) {
        val prev = preTimesMill
        val curr = System.currentTimeMillis()
        if (isWithinThreshold(curr, prev)) {
            return
        }
        preTimesMill = curr
        onValidClick(view)
    }

    private fun isWithinThreshold(curr: Long, prev: Long) = (curr - prev) <= thresholdMs
}