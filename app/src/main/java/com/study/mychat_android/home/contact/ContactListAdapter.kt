package com.study.mychat_android.home.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.mychat_android.MainApplication
import com.study.mychat_android.R
import com.study.mychat_android.model.UserModel

class ContactListAdapter : RecyclerView.Adapter<ContactListAdapter.ContactListViewHold>() {
    var listdata: ArrayList<UserModel> = arrayListOf()
    fun setData(list: ArrayList<UserModel>) {
        listdata.clear()
        this.listdata = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHold {

        return ContactListViewHold(
            LayoutInflater.from(MainApplication.getAppContext()).inflate(
                R.layout.item_contact,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: ContactListViewHold, position: Int) {
        holder.item?.setOnClickListener {
            listener?.onItemClick(listdata[position])
        }
        holder.fillView(listdata[position])
    }


    var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(user: UserModel)
    }

    class ContactListViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var icon: ImageView? = null
        var name: TextView? = null
        var item: FrameLayout? = null

        init {
            item = itemView.findViewById(R.id.item_view)
            icon = itemView.findViewById(R.id.imag_icon)
            name = itemView.findViewById(R.id.tv_name)
        }

        fun fillView(userModel: UserModel) {
            name?.text = userModel.nickname
        }

    }
}