package com.gurihouses.home.tabfragment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurihouses.databinding.FragmentRoomSaleBinding
import com.gurihouses.home.tabfragment.adapter.RoomSaleAdapter
import com.gurihouses.home.tabfragment.bean.RoomSaleResponse
import com.gurihouses.home.tabfragment.viewmodel.RoomSaleViewModel
import com.gurihouses.util.CommonUtil

/**
 * A simple [Fragment] subclass.
 * Use the [RoomSaleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomSaleFragment : Fragment() {

    lateinit var binding: FragmentRoomSaleBinding
    lateinit var vm : RoomSaleViewModel

    companion object {
        @JvmStatic
        fun newInstance() =
            RoomSaleFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        // Inflate the layout for this fragment
        binding = FragmentRoomSaleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        listener()


    }


    private fun initialization() {
        vm = ViewModelProvider(this)[RoomSaleViewModel::class.java]

        loadSaleData()

    }

    private fun loadSaleData() {

        vm.getSaleRooms()
        vm.mForgotResponse?.observe(viewLifecycleOwner, Observer {

            if (it !=null){

                loadUi(it)
            }
        })

        vm.errorMsg?.observe(viewLifecycleOwner, Observer {
            if (it != null) {

                context?.let { it1 -> CommonUtil.showMessage(it1, it.toString()) }

            }
        })

        vm.loadingStatus?.observe(viewLifecycleOwner, Observer {
            if (it == true) {

                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

        })

    }

    private fun loadUi(list: List<RoomSaleResponse>) {

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = RoomSaleAdapter(context, list)
    }

    private fun listener() {

    }




}