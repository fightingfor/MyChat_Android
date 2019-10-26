package com.study.mychat_android.contact

import androidx.lifecycle.MutableLiveData
import com.study.mychat_android.http.RequestCallback
import com.study.mychat_android.http.datasource.ContactDataSource
import com.study.mychat_android.http.viewmodel.BaseViewModel
import com.study.mychat_android.model.ResponseModel
import com.study.mychat_android.model.UserModel

class ContactListViewModel : BaseViewModel() {

    private val conotactDataSource = ContactDataSource(this)

    val addFriendLiveData = MutableLiveData<ResponseModel>()

    val getContactListLiveData = MutableLiveData<ArrayList<UserModel>>()

    fun addFriend(userid: String, dstid: String) {
        conotactDataSource.addFriend(userid, dstid, object : RequestCallback<ResponseModel> {
            override fun onSuccess(data: ResponseModel) {
                addFriendLiveData.value = data
            }
        })
    }

    fun getContactList(userid: String) {
        conotactDataSource.getContacts(userid, object : RequestCallback<ArrayList<UserModel>> {
            override fun onSuccess(data: ArrayList<UserModel>) {
                getContactListLiveData.value = data
            }

        })
    }

}