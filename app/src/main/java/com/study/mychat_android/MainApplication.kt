package com.study.mychat_android

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

/**
 * Created by jinguang on 2019/10/18.
 */

class MainApplication : MultiDexApplication() {

    companion object {
        private lateinit var mAppContext: Context
        fun getAppContext(): Context = mAppContext

    }


    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
        mAppContext = applicationContext
    }

}