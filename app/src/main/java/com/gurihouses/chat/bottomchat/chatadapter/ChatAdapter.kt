package com.gurihouses.chat.bottomchat.chatadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gurihouses.R
import com.gurihouses.chat.bottomchat.bean.ChatDataResponse


class ChatAdapter(val ctx: Context?, val list: List<ChatDataResponse>,val listener:(ChatDataResponse)->Unit): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.chat_row_item_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val sub = list[position]
        holder.mName.text = sub.name
        holder.mMessage.text = sub.message
        holder.mDate.text = sub.date_chat

        Glide.with(ctx!!).load(sub.imageurl).into(holder.mImage)

        holder.mMain.setOnClickListener {

            listener.invoke(list[position])
        }

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mName = itemView.findViewById<TextView>(R.id.name)
        val mMessage = itemView.findViewById<TextView>(R.id.message)
        val mDate = itemView.findViewById<TextView>(R.id.date)
        val mImage = itemView.findViewById<ImageView>(R.id.profile_image)
        val mMain = itemView.findViewById<RelativeLayout>(R.id.main_card)

    }
}