package com.study.mychat_android.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.study.mychat_android.R
import com.study.mychat_android.chat.ChatListFragment
import com.study.mychat_android.contact.ContactListFragment
import com.study.mychat_android.view.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private val TAG = "HomeActivity"
    private val TAB_NUMBER = 3

    companion object {
        const val INDEX_CHAT = 0
        const val INDEX_CONTACT = 1
        const val INDEX_PERSONAL = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
    }

    private fun initView() {
        home_viewpager?.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return TAB_NUMBER
            }

            override fun getItem(position: Int): Fragment {
                when (position) {

                    INDEX_CHAT -> {
                        return ChatListFragment()
                    }
                    INDEX_CONTACT -> {
                        return ContactListFragment()
                    }
                    INDEX_PERSONAL -> {
                        return PersonalInfoFragment()
                    }
                    else -> {
                        return ChatListFragment()
                    }
                }
            }
        }
        tv_chat?.setOnClickListener {
            home_viewpager?.currentItem = INDEX_CHAT
        }
        tv_contact.setOnClickListener {
            home_viewpager?.currentItem = INDEX_CONTACT
        }
        tv_personal.setOnClickListener {
            home_viewpager?.currentItem = INDEX_PERSONAL
        }

    }

}
