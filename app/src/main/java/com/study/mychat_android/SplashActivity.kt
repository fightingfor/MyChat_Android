package com.study.mychat_android

import android.content.Intent
import android.os.Bundle
import com.study.mychat_android.expand.isTokenValid
import com.study.mychat_android.home.HomeActivity
import com.study.mychat_android.login.LoginActivity
import com.study.mychat_android.view.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent : Intent = if (isTokenValid()){
            Intent(this, HomeActivity::class.java)
        }else{
            Intent(this, LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
