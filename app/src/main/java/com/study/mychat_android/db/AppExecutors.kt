package com.study.mychat_android.db

import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull
import java.util.concurrent.Executor
import java.util.concurrent.Executors


/*
 *
 * Author: jinguang
 * Create: 2019/11/20 14:59
 * Description:
 */

class AppExecutors internal constructor(
    private val diskIO: Executor,
    private val networkIO: Executor,
    private val mainThread: Executor
) {

    constructor() : this(
        Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),
        MainThreadExecutor()
    )

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(@NonNull command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {

        private val THREAD_COUNT = 3
    }
}