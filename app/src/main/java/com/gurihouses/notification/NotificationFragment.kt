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

/**
* A simple [Fragment] subclass.
* Use the [NotificationFragment.newInstance] factory method to
* create an instance of this fragment.
*/
class NotificationFragment : Fragment() {

    lateinit var binding: FragmentNotificationBinding

    companion object {
        @JvmStatic
        fun newInstance() =
            NotificationFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        getNotification()
    }

    private fun initialization() {
        
        binding.inclNotificationToolbar.ttitle.text = "Notifications"
        binding.inclNotificationToolbar.clearAll.text = "Clear all"




    }

    private fun getNotification(){

        val data = arrayListOf<NotificationData>()
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))
        data.add(NotificationData("Explore new properties","Explore newly added propoerties in your are and get your self a comtable experince."))

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = NotificationAdapter(data)

    }
}