package com.study.mychat_android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.study.mychat_android.http.RequestCallback
import com.study.mychat_android.http.datasource.WeatherDataSource
import com.study.mychat_android.http.viewmodel.BaseViewModel
import com.study.mychat_android.model.ForecastsBean

/**
 * Created by jinguang on 2019/10/18.
 */


class WeatherViewModel : BaseViewModel() {

    private val weatherDataSource = WeatherDataSource(this)

    val forecastsBeanLiveData = MutableLiveData<ForecastsBean>()

    fun getWeather(city: String) {
        weatherDataSource.getWeather(city, object : RequestCallback<List<ForecastsBean>> {
            override fun onSuccess(data: List<ForecastsBean>) {
                if (data.isNotEmpty()) {
                    forecastsBeanLiveData.value = data[0]
                }
            }
        })
    }

}