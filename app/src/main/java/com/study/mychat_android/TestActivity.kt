package com.study.mychat_android

import android.os.Bundle
import androidx.lifecycle.Observer
import com.study.mychat_android.http.viewmodel.BaseViewModel
import com.study.mychat_android.view.BaseActivity
import com.study.mychat_android.viewmodel.TestViewModel

/**
 * Created by jinguang on 2019/10/18.
 */

class TestActivity : BaseActivity() {
    private lateinit var testViewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getTestData()
    }

    private fun getTestData() {
        testViewModel.getTestData("test")
    }

    override fun initViewModel(): BaseViewModel? {
        testViewModel = getViewModel(TestViewModel::class.java)
        testViewModel.testBeanLiveData.observe(this, Observer {

        })
        return testViewModel
    }
}