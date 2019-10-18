package com.study.mychat_android.model

/**
 * Created by jinguang on 2019/10/18.
 */

data class DistrictBean(
    val adcode: String,
    val center: String,
    val level: String,
    val name: String,
    val districts: List<DistrictBean>
)