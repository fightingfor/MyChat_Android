package com.study.mychat_android

import android.app.Application
import android.content.Context

/**
 * Created by jinguang on 2019/10/18.
 */

class MainApplication : Application() {

    companion object {
        private lateinit var mAppContext: Context
        fun getAppContext(): Context = mAppContext
    }


    override fun onCreate() {
        super.onCreate()
        mAppContext = applicationContext
    }

}