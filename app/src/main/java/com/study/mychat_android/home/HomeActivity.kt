package com.study.mychat_android.home

import android.os.Bundle
import com.study.mychat_android.R
import com.study.mychat_android.view.BaseActivity

class HomeActivity : BaseActivity() {

    private val TAG = "HomeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

}
