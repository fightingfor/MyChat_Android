package com.study.mychat_android.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.mychat_android.R
import com.study.mychat_android.expand.clearAccount
import com.study.mychat_android.expand.getNickname
import com.study.mychat_android.expand.isEmpty
import com.study.mychat_android.login.LoginActivity
import com.study.mychat_android.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_personal.*
import kotlinx.android.synthetic.main.item_contact.*

/**
 * 个人信息界面
 */
class PersonalInfoFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nickname = getNickname()
        tv_personal_name?.text = nickname
        if (isEmpty(nickname)) {
            tv_login_out?.visibility = View.GONE
        } else {
            tv_login_out?.visibility = View.VISIBLE

        }
        tv_login_out?.setOnClickListener {
            clearAccount()
            activity?.apply {
                this.startActivity(Intent(this, LoginActivity::class.java))
                this.finish()
            }
        }
    }
}