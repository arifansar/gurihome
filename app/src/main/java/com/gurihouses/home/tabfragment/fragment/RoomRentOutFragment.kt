package com.gurihouses.home.tabfragment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gurihouses.R

/**
 * A simple [Fragment] subclass.
 * Use the [RoomRentOutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomRentOutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_rent_out, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            RoomRentOutFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}