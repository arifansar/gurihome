package com.gurihouses.notification.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gurihouses.R
import com.gurihouses.faq.adapter.FaqAdapter
import com.gurihouses.notification.model.NotificationData

class NotificationAdapter(private val mList: List<NotificationData>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_notification, parent, false)
        return NotificationAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {
       val item = mList[position]
        holder.title.text = item.title
        holder.description.text = item.description
    }

    override fun getItemCount(): Int {
      return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.question)
        val description: TextView = itemView.findViewById(R.id.answer)

    }
}