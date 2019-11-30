package com.study.mychat_android.chat.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.study.mychat_android.MainApplication
import com.study.mychat_android.R
import com.study.mychat_android.db.ChatMessage
import com.study.mychat_android.expand.getUserId
import com.study.mychat_android.model.MessageModel

/*
 *
 * Author: jinguang
 * Create: 2019/10/26 15:48
 * Description:
 */
class ChatDetailAdapter : RecyclerView.Adapter<ChatDetailAdapter.ChatHolder>() {
    private var messageList = arrayListOf<ChatMessage>()
    fun setData(list: ArrayList<ChatMessage>) {
        messageList.clear()
        messageList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        return ChatHolder(
            LayoutInflater.from(MainApplication.getAppContext()).inflate(
                R.layout.item_message,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.fillData(messageList[position])
    }


    class ChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tv_name_my: TextView? = null
        private var tv_message_my: TextView? = null
        private var tv_name_target: TextView? = null
        private var tv_message_target: TextView? = null
        private var cl_message_my: ConstraintLayout? = null
        private var cl_message_target: ConstraintLayout? = null


        init {
            tv_name_my = view.findViewById(R.id.tv_name_my)
            tv_message_my = view.findViewById(R.id.tv_message_my)
            tv_name_target = view.findViewById(R.id.tv_name_target)
            tv_message_target = view.findViewById(R.id.tv_message_target)
            cl_message_my = view.findViewById(R.id.cl_message_my)
            cl_message_target = view.findViewById(R.id.cl_message_target)
        }

        fun fillData(message: ChatMessage) {
            val ownerId = message.owerId
            if (ownerId == getUserId()) {
                cl_message_my?.visibility = View.VISIBLE
                cl_message_target?.visibility = View.GONE
            } else {
                cl_message_my?.visibility = View.GONE
                cl_message_target?.visibility = View.VISIBLE
            }
            tv_message_my?.text = message.messageTxt
            tv_message_target?.text = message.messageTxt
            tv_name_my?.text = message.owerName
            tv_name_target?.text = message.targetName
        }
    }
}