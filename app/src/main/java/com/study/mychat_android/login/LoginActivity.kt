package com.study.mychat_android.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.study.mychat_android.R
import com.study.mychat_android.expand.isEmpty
import com.study.mychat_android.expand.logger_d
import com.study.mychat_android.home.HomeActivity
import com.study.mychat_android.http.viewmodel.BaseViewModel
import com.study.mychat_android.view.BaseActivity
import com.study.mychat_android.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private val TAG = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        bt_login?.setOnClickListener {
            val phone = et_phone_number?.text?.toString() ?: ""
            val password = et_password?.text?.toString() ?: ""
            if (isEmpty(phone)) {
                return@setOnClickListener
            }
            if (isEmpty(password)) {
                return@setOnClickListener
            }
            loginViewModel.doLogin(phone, password)
        }

        bt_regist?.setOnClickListener {
            val nickname = et_nick_name?.text?.toString() ?: ""
            val phone = et_phone_number?.text?.toString() ?: ""
            val password = et_password?.text?.toString() ?: ""
            if (isEmpty(nickname)) {
                et_nick_name?.visibility = View.VISIBLE
                return@setOnClickListener
            }
            if (isEmpty(phone)) {
                return@setOnClickListener
            }
            if (isEmpty(password)) {
                return@setOnClickListener
            }
            loginViewModel.doRegist(nickname, phone, password)
        }
    }

    override fun initViewModel(): BaseViewModel? {
        loginViewModel = getViewModel(LoginViewModel::class.java)
        loginViewModel.loginLiveData.observe(this, Observer {
            val createat = it.createat
            val nickname = it.nickname
            val token = it.token
            logger_d(TAG, "登录》》》》createat=$createat>>>nickname=$nickname>>>>token=$token")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        })

        loginViewModel.registLiveData.observe(this, Observer {

            val createat = it.createat
            val nickname = it.nickname
            val token = it.token
            logger_d(TAG, "注册》》》》createat=$createat>>>nickname=$nickname>>>>token=$token")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        })

        return loginViewModel
    }
}
