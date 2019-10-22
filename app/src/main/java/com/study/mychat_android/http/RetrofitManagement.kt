package com.study.mychat_android.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

/**
 * Created by jinguang on 2019/10/18.
 */

class RetrofitManagement private constructor() {

    companion object {

        private const val READ_TIMEOUT: Long = 10000

        private const val WRITE_TIMEOUT: Long = 10000

        private const val CONNECT_TIMEOUT: Long = 10000

        val instance: RetrofitManagement by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitManagement()
        }

    }

    private val serviceMap = ConcurrentHashMap<String, Any>()

    private fun createRetrofit(url: String): Retrofit {
        val builder = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
//            .addInterceptor(FilterInterceptor()) todo暂时不用

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(httpLoggingInterceptor)
//        builder.addInterceptor(MonitorInterceptor(ContextHolder.context))

        val client = builder.build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun <T : Any> getService(clz: Class<T>, host: String): T {
        if (serviceMap.containsKey(host)) {
            val obj = serviceMap[host] as? T
            obj?.let {
                return obj
            }
        }
        val value = createRetrofit(host).create(clz)
        serviceMap[host] = value
        return value
    }

}