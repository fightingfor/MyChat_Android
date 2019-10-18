package com.study.mychat_android.viewmodel


import androidx.lifecycle.MutableLiveData
import com.study.mychat_android.http.RequestCallback
import com.study.mychat_android.http.datasource.TestDataSource
import com.study.mychat_android.http.viewmodel.BaseViewModel
import com.study.mychat_android.model.TestBean

/**
 * Created by jinguang on 2019/10/18.
 */

class TestViewModel : BaseViewModel() {

    private val testDataSource = TestDataSource(this)

    val testBeanLiveData = MutableLiveData<TestBean>()

    fun getTestData(test: String) {
        testDataSource.getTestData(test, object : RequestCallback<TestBean> {
            override fun onSuccess(data: TestBean) {
                testBeanLiveData.value = data
            }
        })
    }


}