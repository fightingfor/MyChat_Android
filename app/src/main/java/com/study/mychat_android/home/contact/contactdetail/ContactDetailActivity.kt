package com.study.mychat_android.home.contact.contactdetail


import android.os.Bundle
import com.study.mychat_android.R
import com.study.mychat_android.view.BaseActivity

class ContactDetailActivity : BaseActivity() {

    companion object{
        const val KEY_USER = "key_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
    }
}
