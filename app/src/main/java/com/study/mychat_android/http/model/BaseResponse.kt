package com.study.mychat_android.http.model


/**
 * Created by jinguang on 2019/10/18.
 */

class BaseResponse<T>(
   var code: Int = 0,
   var message: String? = null,
   var data: T
)

class OptionT<T>(val value: T)