package com.gurihouses.recent

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurihouses.R
import com.gurihouses.databinding.FragmentRecentBinding
import com.gurihouses.home.tabfragment.adapter.RoomSaleAdapter
import com.gurihouses.home.tabfragment.bean.RoomSaleResponse
import com.gurihouses.home.tabfragment.viewmodel.RoomSaleViewModel
import com.gurihouses.propertydetails.PropertyDetailActivity


/**
 * A simple [Fragment] subclass.
 * Use the [RecentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecentFragment : Fragment() {

    lateinit var binding: FragmentRecentBinding
    lateinit var vm : RoomSaleViewModel

    companion object {
        @JvmStatic
        fun newInstance() =
            RecentFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        listener()
    }

    private fun initialization() {
        vm = ViewModelProvider(this)[RoomSaleViewModel::class.java]

        binding.inclToolbar.ttitle.text = "Recently viewed"


        loadSaleData()

    }

    private fun loadSaleData() {

        val list = arrayListOf<RoomSaleResponse>()

        list.add(
            RoomSaleResponse(
                "2 BHK in Cape Town ",
                "Location: Cape town",
                "","",
                "Price: R1000",
                "2BHK, Duplex, Apartment",
                R.drawable.ic_room,"")
        )
        list.add(
            RoomSaleResponse(
                "2 BHK in Cape Town ",
                "Location: Cape town",
                "","",
                "Price: R1000",
                "2BHK, Duplex, Apartment",
                R.drawable.room1,"")
        )
        list.add(
            RoomSaleResponse(
                "2 BHK in Cape Town ",
                "Location: Cape town",
                "","",
                "Price: R1000",
                "2BHK, Duplex, Apartment",
                R.drawable.ic_room,"")
        )




        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = RoomSaleAdapter(context, list){

            val mDetailScreen = Intent(context, PropertyDetailActivity::class.java)
            Intent.FLAG_ACTIVITY_NEW_TASK
            Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mDetailScreen)
            activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }


//        vm.getSaleRooms()
//        vm.mForgotResponse?.observe(viewLifecycleOwner, Observer {
//
//            if (it !=null){
//
//                loadUi(it)
//            }
//        })
//
//        vm.errorMsg?.observe(viewLifecycleOwner, Observer {
//            if (it != null) {
//
//                context?.let { it1 -> CommonUtil.showMessage(it1, it.toString()) }
//
//            }
//        })
//
//        vm.loadingStatus?.observe(viewLifecycleOwner, Observer {
//            if (it == true) {
//
//                binding.progressBar.visibility = View.VISIBLE
//            } else {
//                binding.progressBar.visibility = View.GONE
//            }
//
//        })

    }

    private fun loadUi(list: List<RoomSaleResponse>) {


    }

    private fun listener() {

    }


}