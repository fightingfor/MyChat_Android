package com.study.mychat_android.http.datasource

import com.study.mychat_android.http.BaseRemoteDataSource
import com.study.mychat_android.http.HttpConfig
import com.study.mychat_android.http.IBaseViewModelEvent
import com.study.mychat_android.http.RequestCallback
import com.study.mychat_android.http.api.ApiService
import com.study.mychat_android.model.DistrictBean
import com.study.mychat_android.model.ForecastsBean
import com.study.mychat_android.model.TestBean

/**
 * Created by jinguang on 2019/10/18.
 */

class MapDataSource(baseViewModelEvent: IBaseViewModelEvent) : BaseRemoteDataSource(baseViewModelEvent) {

    fun getProvince(callback: RequestCallback<List<DistrictBean>>) {
        execute(getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getProvince(), callback)
    }

    fun getCity(keywords: String, callback: RequestCallback<List<DistrictBean>>) {
        execute(getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getCity(keywords), callback)
    }

    fun getCounty(keywords: String, callback: RequestCallback<List<DistrictBean>>) {
        execute(getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getCounty(keywords), callback)
    }

}

class WeatherDataSource(baseViewModelEvent: IBaseViewModelEvent) : BaseRemoteDataSource(baseViewModelEvent) {

    fun getWeather(city: String, callback: RequestCallback<List<ForecastsBean>>) {
        executeQuietly(getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getWeather(city), callback)
    }

}

class TestDataSource(baseViewModelEvent: IBaseViewModelEvent) : BaseRemoteDataSource(baseViewModelEvent) {
    fun getTestData(test: String, callback: RequestCallback<TestBean>) {
        execute(getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getTestInfo(test), callback)
    }
}