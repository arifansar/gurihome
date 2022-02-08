package com.gurihouses.recent.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurihouses.R
import com.gurihouses.databinding.FragmentRecentBinding
import com.gurihouses.home.tabfragment.adapter.RoomSaleAdapter
import com.gurihouses.home.tabfragment.bean.RoomSaleResponse
import com.gurihouses.home.tabfragment.bean.UserProperty
import com.gurihouses.home.tabfragment.viewmodel.RoomSaleViewModel
import com.gurihouses.propertydetails.PropertyDetailActivity
import com.gurihouses.recent.adapter.RecentViewAdapter
import com.gurihouses.recent.model.RecentViewData
import com.gurihouses.recent.viewmodel.RecentViewModel
import com.gurihouses.util.CommonUtil

class RecentFragment : Fragment() {

    lateinit var binding: FragmentRecentBinding
    lateinit var vm : RoomSaleViewModel
    lateinit var recentViewModel: RecentViewModel

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


        loadRecentData()

    }

    fun loadRecentData(){
        recentViewModel.getRecentView(1,"e10adc3949ba59abbe56e057f20f883e")
        recentViewModel.recentViewResoponse?.observe(viewLifecycleOwner, Observer {
            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode == true) {

                    if (response.data.isNotEmpty()) {
                        setUpRecentData(response.data)
                    }

                }

            }

        })

        recentViewModel.errorMsg?.observe(viewLifecycleOwner, Observer {
            if (it != null) {

                CommonUtil.showMessage(requireContext(), it.toString())

            }

        })

    }

    private fun setUpRecentData(list: List<RecentViewData>) {


        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = RecentViewAdapter(context, list){

            val mDetailScreen = Intent(context, PropertyDetailActivity::class.java)
            Intent.FLAG_ACTIVITY_NEW_TASK
            Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mDetailScreen)
            activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }


    }



    private fun listener() {

    }


}