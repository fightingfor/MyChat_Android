package com.study.mychat_android.chat.detail


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.mychat_android.R
import com.study.mychat_android.expand.getNickname
import com.study.mychat_android.expand.getUserId
import com.study.mychat_android.expand.isEmpty
import com.study.mychat_android.http.HttpConfig
import com.study.mychat_android.http.viewmodel.BaseViewModel
import com.study.mychat_android.model.MessageModel
import com.study.mychat_android.model.UserModel
import com.study.mychat_android.mywebsocket.MySocket
import com.study.mychat_android.view.BaseActivity
import kotlinx.android.synthetic.main.activity_contact_detail.*
import java.net.URI


class ChatDetailActivity : BaseActivity() {

    companion object {
        const val KEY_USER = "key_user"
    }

    private lateinit var mySocket: MySocket
    private lateinit var chatDetailViewModel: ChatDetailViewModel
    private var messageList = arrayListOf<MessageModel>()
    private var ownerId = getUserId()
    private var ownerName = getNickname()
    private lateinit var targetUser: UserModel
    private var adapter = ChatDetailAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        targetUser = intent.getParcelableExtra(KEY_USER) as UserModel
        mySocket =
            MySocket(URI("${HttpConfig.WEB_SOCKET_URL}/api/chat?ownerId=${getUserId()}&targetId=${targetUser.id}"))
        mySocket.setCurrentAct(this)
        mySocket.connect()
        iniView()
    }

    override fun initViewModel(): BaseViewModel? {
        chatDetailViewModel = getViewModel(ChatDetailViewModel::class.java)
        chatDetailViewModel.messageData.observe(this, Observer {
            messageList.add(
                MessageModel(
                    targetUser.id ?: "",
                    targetUser.nickname ?: "",
                    targetId = ownerId,
                    targetName = ownerName,
                    msg = it.msg
                )
            )
            adapter.setData(messageList)
            recycle_contact_detail?.scrollToPosition(adapter.itemCount - 1)
        })
        return chatDetailViewModel
    }

    private fun iniView() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.stackFromEnd = true
        recycle_contact_detail?.layoutManager = linearLayoutManager
        recycle_contact_detail?.adapter = adapter
        adapter.setData(messageList)

        btn_message_send?.setOnClickListener {
            val msg = et_message.text.toString()
            if (isEmpty(msg)) {
                return@setOnClickListener
            }
            mySocket.send(msg)
            messageList.add(
                MessageModel(
                    ownerId,
                    ownerName,
                    targetId = targetUser.id ?: "",
                    targetName = targetUser.nickname ?: "",
                    msg = msg
                )
            )

            adapter.setData(messageList)
            recycle_contact_detail?.scrollToPosition(adapter.itemCount - 1)
            et_message?.setText("")
        }
    }
}
