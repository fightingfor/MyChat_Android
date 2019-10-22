package com.study.mychat_android.http.api

import com.study.mychat_android.http.model.BaseResponse
import com.study.mychat_android.model.*
import io.reactivex.Observable
import retrofit2.http.*


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
/*---------------------------------------------------------------------------------------------------------*/
    @FormUrlEncoded
    @POST("api/login")
    fun login(@FieldMap map: Map<String, String>): Observable<BaseResponse<LoginModel>>

    @FormUrlEncoded
    @POST("api/registe")
    fun regist(@FieldMap map: Map<String, String>): Observable<BaseResponse<LoginModel>>

    @FormUrlEncoded
    @POST("api/addfriend")
    fun addFriend(@FieldMap map: Map<String, String>): Observable<BaseResponse<ResponseModel>>

    @FormUrlEncoded
    @POST("api/loadfriends")
    fun getContactList(@FieldMap map: Map<String, String>): Observable<BaseResponse<ArrayList<UserModel>>>

    @GET("test/test")
    fun getTestInfo(@Query("test") test: String): Observable<BaseResponse<TestBean>>




}