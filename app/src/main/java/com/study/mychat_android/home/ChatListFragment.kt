package com.study.mychat_android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.mychat_android.R
import com.study.mychat_android.view.BaseFragment

/**
 * 聊天界面
 */
class ChatListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chatlist,container,false)
    }


}