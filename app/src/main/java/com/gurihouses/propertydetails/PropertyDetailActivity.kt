package com.gurihouses.propertydetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gurihouses.R
import com.gurihouses.databinding.ActivityPropertyDetailBinding
import com.gurihouses.propertydetails.adapter.BreakingAlertAdapter

class PropertyDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityPropertyDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initialization()
        listener()

    }

    private fun initialization() {

        binding.inlDetailToolbar.ttitle.text = "Property detail"

        val list = arrayListOf<Int>()

        list.add(R.drawable.room1)
        list.add(R.drawable.ic_room)
        list.add(R.drawable.room1)
        list.add(R.drawable.ic_room)
        list.add(R.drawable.room1)
        list.add(R.drawable.ic_room)

        binding.viewPager2.startAutoScroll()
        binding.viewPager2.interval = 3000
        binding.viewPager2.isCycle = true
        binding.viewPager2.isStopScrollWhenTouch = true
        binding.tabLayout1.setupWithViewPager(binding.viewPager2, true)

        binding.viewPager2.adapter = BreakingAlertAdapter(this, list)


    }

    private fun listener() {

        binding.inlDetailToolbar.backIcon.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

    }
}