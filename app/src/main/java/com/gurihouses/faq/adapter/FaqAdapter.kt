package com.gurihouses.faq.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gurihouses.R
import com.gurihouses.faq.model.FaqData

class FaqAdapter(private val mList: List<FaqData>) : RecyclerView.Adapter<FaqAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_faq, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqAdapter.ViewHolder, position: Int) {
        val item = mList[position]
        holder.question.text = item.question
        holder.answer.text = item.answer
        holder.imageView.setOnClickListener(View.OnClickListener {
            //holder.answer.setVisibility(View.VISIBLE);
            holder.answer.setVisibility(if (holder.answer.getVisibility() == View.VISIBLE) View.GONE else View.VISIBLE)
            holder.downDropdown.setVisibility(if (holder.downDropdown.getVisibility() == View.VISIBLE) View.GONE else View.VISIBLE)
        })
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val question: TextView = itemView.findViewById(R.id.question)
        val answer: TextView = itemView.findViewById(R.id.answer)
        val imageView: ImageView = itemView.findViewById(R.id.dropdown)
        val downDropdown: ImageView = itemView.findViewById(R.id.ic_down_dropdown)
    }


}