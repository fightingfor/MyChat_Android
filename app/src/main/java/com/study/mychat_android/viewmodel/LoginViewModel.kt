package com.study.mychat_android.viewmodel

import androidx.lifecycle.MutableLiveData
import com.study.mychat_android.expand.saveAccount
import com.study.mychat_android.expand.toast
import com.study.mychat_android.http.BaseException
import com.study.mychat_android.http.RequestCallback
import com.study.mychat_android.http.RequestMultiplyCallback
import com.study.mychat_android.http.datasource.LoginDataSource
import com.study.mychat_android.http.viewmodel.BaseViewModel
import com.study.mychat_android.model.LoginModel


class LoginViewModel : BaseViewModel() {

    private val loginDataSource = LoginDataSource(this)

    val loginLiveData = MutableLiveData<LoginModel>()

    val registLiveData = MutableLiveData<LoginModel>()

    fun doLogin(phone :String,password :String){
        loginDataSource.login(phone,password,object : RequestMultiplyCallback<LoginModel> {
            override fun onFail(e: BaseException) {
                toast(e.message.toString())
            }

            override fun onSuccess(data: LoginModel) {
                loginLiveData.value = data
                saveAccount(loginmodel = data)
            }
        })

    }

    fun doRegist(nickname: String,phone :String,password :String){
        loginDataSource.regist(nickname,phone,password,object :RequestMultiplyCallback<LoginModel>{
            override fun onFail(e: BaseException) {
                toast(e.message.toString())
            }

            override fun onSuccess(data: LoginModel) {
                registLiveData.value = data
                saveAccount(loginmodel = data)
            }
        })

    }
}