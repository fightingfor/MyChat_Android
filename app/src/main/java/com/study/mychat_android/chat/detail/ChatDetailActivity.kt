package com.study.mychat_android.chat.detail


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.study.mychat_android.R
import com.study.mychat_android.db.AppExecutors
import com.study.mychat_android.db.ChatMessage
import com.study.mychat_android.db.MessageDataSource_impl
import com.study.mychat_android.db.MessageRepository
import com.study.mychat_android.expand.getNickname
import com.study.mychat_android.expand.getUserId
import com.study.mychat_android.expand.isEmpty
import com.study.mychat_android.http.viewmodel.BaseViewModel
import com.study.mychat_android.model.UserModel
import com.study.mychat_android.service.ChatServiceManager
import com.study.mychat_android.util.MessageType
import com.study.mychat_android.view.BaseActivity
import com.study.testmodule.StringUtil
import kotlinx.android.synthetic.main.activity_contact_detail.*

class ChatDetailActivity : BaseActivity(), ChatDetailView {
    override fun getChatMessageSuccess(list: List<ChatMessage>) {
        list.let {
            messageList.addAll(it)
        }
        adapter.setData(messageList)
        recycle_contact_detail?.scrollToPosition(adapter.itemCount - 1)
    }

    companion object {
        const val KEY_USER = "key_user"
    }

    private lateinit var chatDetailViewModel: ChatDetailViewModel
    private var messageList = ArrayList<ChatMessage>()
    private var ownerId = getUserId()
    private var ownerName = getNickname()
    private lateinit var targetUser: UserModel
    private var adapter = ChatDetailAdapter()
    private lateinit var messageRepository: MessageRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        targetUser = intent.getParcelableExtra(KEY_USER) as UserModel
        iniView()
        targetUser.id?.let { chatDetailViewModel.getAllMessage(getUserId().toInt(), targetId = it) }
        messageRepository =
            MessageRepository(AppExecutors(), MessageDataSource_impl.getDataSourceInstance())
        val chatDetailPersent = ChatDetailPersent(messageRepository, this)
        chatDetailPersent.getChatList(getUserId().toInt(), targetUser.id ?: -1)
    }

    override fun initViewModel(): BaseViewModel? {
        chatDetailViewModel = getViewModel(ChatDetailViewModel::class.java)
        chatDetailViewModel.messageData.observe(this, Observer {
            it?.let {
                messageList.addAll(it)
            }
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
            StringUtil.string2Int()
            val msg = et_message.text.toString()
            if (isEmpty(msg)) {
                return@setOnClickListener
            }

            val message = ChatMessage()
            message.creatTime = System.currentTimeMillis()
            message.messageType = MessageType.TYPE_TXT
            message.owerId = getUserId()
            message.owerName = getNickname()
            message.targetId = targetUser.id
            message.targetName = targetUser.nickname
            message.messageTxt = msg

            val chatMessage = Gson().toJson(message)

            ChatServiceManager.startService(ChatServiceManager.ACTION_SEND_MESSAGE, chatMessage)
            messageList.add(message)

            adapter.setData(messageList)
            recycle_contact_detail?.scrollToPosition(adapter.itemCount - 1)
            et_message?.setText("")
        }
    }
}
