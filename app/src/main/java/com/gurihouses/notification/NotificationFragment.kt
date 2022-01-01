package com.gurihouses.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurihouses.databinding.FragmentNotificationBinding
import com.gurihouses.notification.adapter.NotificationAdapter
import com.gurihouses.notification.model.NotificationData


class NotificationFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var binding: FragmentNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        getNotification()
        return binding.root
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotificationFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun getNotification(){

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        val data = ArrayList<NotificationData>()
        for (i in 1..6) {
            data.add(
                NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince.")
            )
        }
        val adapter = NotificationAdapter(data)

        binding.recyclerView.adapter = adapter

    }
}