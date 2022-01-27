package com.gurihouses.home.tabfragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gurihouses.home.tabfragment.fragment.RoomRentOutFragment
import com.gurihouses.home.tabfragment.fragment.RoomSaleFragment


class TabFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager,lifecycle){


    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return RoomRentOutFragment.newInstance()
            }
            1 -> {
                return RoomSaleFragment.newInstance()

            }
        }
        return RoomRentOutFragment.newInstance()
    }
    override fun getItemCount(): Int {
        return TAB_COUNT
    }

    companion object {
        private const val TAB_COUNT = 2
    }
}