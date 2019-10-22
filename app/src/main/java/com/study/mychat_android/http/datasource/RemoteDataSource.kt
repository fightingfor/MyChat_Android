package com.study.mychat_android.http.datasource

import com.study.mychat_android.expand.getToken
import com.study.mychat_android.http.BaseRemoteDataSource
import com.study.mychat_android.http.HttpConfig
import com.study.mychat_android.http.IBaseViewModelEvent
import com.study.mychat_android.http.RequestCallback
import com.study.mychat_android.http.api.ApiService
import com.study.mychat_android.model.*

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
/*------------------------------------------------------------------------------------------------------------------*/
class LoginDataSource(baseViewModelEvent: IBaseViewModelEvent) : BaseRemoteDataSource(baseViewModelEvent){
    fun login(phone:String,password:String,callback: RequestCallback<LoginModel>){
        val map = mutableMapOf<String, String>()
        map["mobile"] = phone
        map["password"] = password
        execute(getService(ApiService::class.java,HttpConfig.BASE_URL_MAP).login(map),callback)
    }

    fun regist(phone:String,password:String,callback: RequestCallback<LoginModel>){
        val map = mutableMapOf<String, String>()
        map["mobile"] = phone
        map["password"] = password
        execute(getService(ApiService::class.java,HttpConfig.BASE_URL_MAP).regist(map),callback)
    }
}

class ContactDataSource(baseViewModelEvent: IBaseViewModelEvent):BaseRemoteDataSource(baseViewModelEvent){
    fun addFriend(userid:String,dstid:String,callback: RequestCallback<ResponseModel>){
        val map = mutableMapOf<String,String>()
        map["userid"] = userid
        map["dstid"] = dstid
        map["token"] = getToken()
        execute(getService(ApiService::class.java,HttpConfig.BASE_URL_MAP).addFriend(map),callback)
    }

    fun getContacts(userid: String,callback: RequestCallback<ArrayList<UserModel>>){
        val map = mutableMapOf<String,String>()
        map["userid"] = userid
        map["token"] = getToken()
        execute(getService(ApiService::class.java,HttpConfig.BASE_URL_MAP).getContactList(map),callback)
    }
}