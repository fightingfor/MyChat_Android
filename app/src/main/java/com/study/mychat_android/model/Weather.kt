package com.study.mychat_android.model

/**
 * Created by jinguang on 2019/10/18.
 */

data class ForecastsBean(
    val city: String, val adcode: String, val province: String,
    val reporttime: String, val casts: List<CastsBean>
)

data class CastsBean(
    val date: String, val week: String, val dayweather: String, val nightweather: String, val daytemp: String,
    val nighttemp: String, val daywind: String, val nightwind: String, val daypower: String, val nightpower: String
)