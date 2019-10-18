package com.study.mychat_android.http

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by jinguang on 2019/10/18.
 */

class FilterInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val httpBuilder = originalRequest.url().newBuilder()
        httpBuilder.addEncodedQueryParameter(HttpConfig.KEY, HttpConfig.KEY_MAP)
        val requestBuilder = originalRequest.newBuilder()
            .url(httpBuilder.build())
        return chain.proceed(requestBuilder.build())
    }

}