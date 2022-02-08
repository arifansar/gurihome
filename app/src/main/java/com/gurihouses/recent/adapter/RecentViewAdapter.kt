package com.gurihouses.recent.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gurihouses.R
import com.gurihouses.home.tabfragment.bean.RoomSaleResponse
import com.gurihouses.recent.model.RecentViewData

class RecentViewAdapter(val ctx: Context?, val list: List<RecentViewData>, val listener:(RecentViewData)->Unit): RecyclerView.Adapter<RecentViewAdapter.ViewHolder>()  {



    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.room_sale_row_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val sub = list[position]
        holder.saleName.text = sub.title
        holder.saleLocation.text = sub.location
        holder.salePrice.text = sub.price
        holder.saleBhk.text = sub.rooms
        Glide.with(ctx!!).load(R.drawable.ic_room).into(holder.roomImage)

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

        val saleName = itemView.findViewById<TextView>(R.id.sale_name)
        val saleLocation = itemView.findViewById<TextView>(R.id.sale_location)
        val salePrice = itemView.findViewById<TextView>(R.id.sale_price)
        val saleBhk = itemView.findViewById<TextView>(R.id.sale_bhk)
        val roomImage = itemView.findViewById<ImageView>(R.id.item_image)
        val mMain = itemView.findViewById<CardView>(R.id.card_view)

    }
}