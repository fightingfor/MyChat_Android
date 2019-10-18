package com.study.mychat_android.util

import android.content.Context
import android.content.SharedPreferences
import com.study.mychat_android.MainApplication
import com.study.mychat_android.expand.logger_e

/**
 * Created by jinguang on 2019/10/18.
 */

object SharedPref {
    const val NAME = "Philippines"
    const val TAG = "SharedPref"
    /**
     * 记录应用的版本versionCode
     */
    const val KEY_APP_VERSION = "key_app_version"

    var mSP: SharedPreferences

    init {
        mSP = MainApplication.getAppContext().getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    fun setString(key: String, value: String?) {
        mSP.edit().putString(key, value ?: "").apply()
    }

    fun getString(key: String, defVal: String?): String? {
        return mSP.getString(key, defVal ?: "")
    }

    fun setBoolean(key: String, value: Boolean) {
        mSP.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {

        return mSP.getBoolean(key, defValue)
    }

    fun setInt(key: String, value: Int) {
        mSP.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defValue: Int): Int {
        return mSP.getInt(key, defValue)
    }

    fun setLong(key: String, value: Long) {
        mSP.edit().putLong(key, value).apply()
    }

    fun getLong(key: String, defValue: Long): Long {
        return mSP.getLong(key, defValue)
    }

    fun removeKey(key: String) {
        try {
            if (mSP.contains(key)) {
                mSP.edit().remove(key).apply()
            }
        } catch (e: Exception) {
            if (AppEnv.DEBUG) {
                logger_e(TAG, "remove key e.toString$e")
            }
        }
    }
}