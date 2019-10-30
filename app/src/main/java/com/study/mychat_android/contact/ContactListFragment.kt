package com.study.mychat_android.contact

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.mychat_android.MainApplication
import com.study.mychat_android.R
import com.study.mychat_android.expand.getUserId
import com.study.mychat_android.expand.isEmpty
import com.study.mychat_android.chat.detail.ChatDetailActivity
import com.study.mychat_android.http.viewmodel.BaseViewModel
import com.study.mychat_android.model.UserModel
import com.study.mychat_android.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_contactlist.*


/**
 * 联系人界面
 */
class ContactListFragment : BaseFragment() {

    /*--------------------------------------------------------------------------*/
    private lateinit var contactListViewModel: ContactListViewModel
    private val contactListAdapter = ContactListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contactlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addFriendsDialog = AddFriendsDialog(activity!!)
        addFriendsDialog.setOnAddClickListener(object : AddFriendsDialog.OnclickListener {
            override fun OnAddClick(mobile: String) {
                if (isEmpty(mobile)) {
                    return
                }
                contactListViewModel.addFriend(userid = getUserId(), mobile = mobile)
                addFriendsDialog.dismiss()
            }
        })
        tv_addfriend?.setOnClickListener {
            activity?.apply {
                addFriendsDialog.show()
            }
        }
        initRecycleView()
        loadFriends()
    }

    private fun initRecycleView() {
        val linearLayoutManager = LinearLayoutManager(MainApplication.getAppContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycle_contact?.layoutManager = linearLayoutManager
        recycle_contact?.adapter = contactListAdapter
        contactListAdapter.setOnItemClickListener(object : ContactListAdapter.OnItemClickListener {
            override fun onItemClick(user: UserModel) {
                val intent = Intent(activity!!, ChatDetailActivity::class.java)
                intent.putExtra(ChatDetailActivity.KEY_USER, user)
                startActivity(intent)
            }

        })
    }

    private fun loadFriends() {
        contactListViewModel.getContactList(userid = getUserId())
    }

    override fun initViewModel(): BaseViewModel? {
        contactListViewModel = getViewModel(ContactListViewModel::class.java)
        contactListViewModel.addFriendLiveData.observe(this, Observer {
            loadFriends()//添加成功后刷新好友列表
        })
        contactListViewModel.getContactListLiveData.observe(this, Observer {
            contactListAdapter.setData(it)
        })
        return contactListViewModel
    }
}