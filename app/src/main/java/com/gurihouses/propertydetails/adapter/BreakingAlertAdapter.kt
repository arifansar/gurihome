package com.gurihouses.propertydetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.gurihouses.R

class BreakingAlertAdapter(private val context: Context?, private val imagelist:ArrayList<Int>?) : PagerAdapter(){

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return imagelist?.size ?:0
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.house_breaking_alert_layout,parent,false)

        val imageview = view.findViewById<ImageView>(R.id.alert_image)

        Glide.with(context!!).load(imagelist?.get(position)).into(imageview)

        // Add the view to the parent
        parent.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

}