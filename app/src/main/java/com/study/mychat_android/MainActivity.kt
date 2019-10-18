package com.study.mychat_android


import android.os.Bundle
import com.study.mychat_android.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by jinguang on 2019/10/18.
 */

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_province?.setOnClickListener {


        }
    }
}
