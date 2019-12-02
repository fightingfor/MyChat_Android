package com.study.mychat_android.http.datasource

import androidx.lifecycle.LiveData
import com.study.mychat_android.MainApplication
import com.study.mychat_android.db.AppDatabase
import com.study.mychat_android.db.ChatMessage
import com.study.mychat_android.expand.getToken
import com.study.mychat_android.http.*
import com.study.mychat_android.http.api.ApiService
import com.study.mychat_android.model.*

/**
 * Created by jinguang on 2019/10/18.
 */

class MapDataSource(baseViewModelEvent: IBaseViewModelEvent) :
    BaseRemoteDataSource(baseViewModelEvent) {

    fun getProvince(callback: RequestCallback<List<DistrictBean>>) {
        execute(getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getProvince(), callback)
    }

    fun getCity(keywords: String, callback: RequestCallback<List<DistrictBean>>) {
        execute(
            getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getCity(keywords),
            callback
        )
    }

    fun getCounty(keywords: String, callback: RequestCallback<List<DistrictBean>>) {
        execute(
            getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getCounty(keywords),
            callback
        )
    }

}

class WeatherDataSource(baseViewModelEvent: IBaseViewModelEvent) :
    BaseRemoteDataSource(baseViewModelEvent) {

    fun getWeather(city: String, callback: RequestCallback<List<ForecastsBean>>) {
        executeQuietly(
            getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getWeather(city),
            callback
        )
    }

}

class TestDataSource(baseViewModelEvent: IBaseViewModelEvent) :
    BaseRemoteDataSource(baseViewModelEvent) {
    fun getTestData(test: String, callback: RequestCallback<TestBean>) {
        execute(
            getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getTestInfo(test),
            callback
        )
    }
}

/*------------------------------------------------------------------------------------------------------------------*/
class LoginDataSource(baseViewModelEvent: IBaseViewModelEvent) :
    BaseRemoteDataSource(baseViewModelEvent) {
    fun login(phone: String, password: String, callback: RequestMultiplyCallback<LoginModel>) {
        val map = mutableMapOf<String, String>()
        map["mobile"] = phone
        map["password"] = password
        execute(getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).login(map), callback)
    }

    fun regist(
        nickname: String,
        phone: String,
        password: String,
        callback: RequestMultiplyCallback<LoginModel>
    ) {
        val map = mutableMapOf<String, String>()
        map["nickname"] = nickname
        map["mobile"] = phone
        map["password"] = password
        execute(getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).regist(map), callback)
    }
}

class ContactDataSource(baseViewModelEvent: IBaseViewModelEvent) :
    BaseRemoteDataSource(baseViewModelEvent) {
    fun addFriend(
        userid: String,
        mobile: String,
        callback: RequestMultiplyCallback<ResponseModel>
    ) {
        val map = mutableMapOf<String, String>()
        map["userid"] = userid
        map["mobile"] = mobile
        map["token"] = getToken()
        execute(
            getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).addFriend(map),
            callback
        )
    }

    fun getContacts(userid: String, callback: RequestMultiplyCallback<ArrayList<UserModel>>) {
        val map = mutableMapOf<String, String>()
        map["userid"] = userid
        map["token"] = getToken()
        execute(
            getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getContactList(map),
            callback
        )
    }
}

class ChatDetailDataSource(baseViewModelEvent: IBaseViewModelEvent) :
    BaseRemoteDataSource(baseViewModelEvent) {

    fun getAllChatMessage(owerid: Int, targetId: Int) : LiveData<List<ChatMessage>> {
        val chatMessageDao = AppDatabase.getDababaseInstance().chatMessageDao()
        val chatMessageList = chatMessageDao.getChatMessageList2(owerid, targetId)

        return chatMessageList
    }


    fun getAllData():List<ChatMessage>{
        val chatMessageDao = AppDatabase.getDababaseInstance().chatMessageDao()
        return chatMessageDao.getAllData()
    }
}

