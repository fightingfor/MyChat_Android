package com.study.mychat_android.http.api

import com.study.mychat_android.http.model.BaseResponse
import com.study.mychat_android.model.DistrictBean
import com.study.mychat_android.model.ForecastsBean
import com.study.mychat_android.model.TestBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by jinguang on 2019/10/18.
 */

interface ApiService {

    @GET("config/district")
    fun getProvince(): Observable<BaseResponse<List<DistrictBean>>>

    @GET("config/district")
    fun getCity(@Query("keywords") keywords: String): Observable<BaseResponse<List<DistrictBean>>>

    @GET("config/district")
    fun getCounty(@Query("keywords") keywords: String): Observable<BaseResponse<List<DistrictBean>>>

    @GET("weather/weatherInfo?extensions=all")
    fun getWeather(@Query("city") city: String): Observable<BaseResponse<List<ForecastsBean>>>


    @GET("test/test")
    fun getTestInfo(@Query("test") test: String): Observable<BaseResponse<TestBean>>

}