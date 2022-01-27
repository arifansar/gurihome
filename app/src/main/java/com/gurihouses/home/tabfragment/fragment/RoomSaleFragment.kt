package com.gurihouses.home.tabfragment.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurihouses.R
import com.gurihouses.databinding.FragmentRoomSaleBinding
import com.gurihouses.home.tabfragment.adapter.RoomSaleAdapter
import com.gurihouses.home.tabfragment.bean.RoomSaleResponse
import com.gurihouses.home.tabfragment.bean.UserProperty
import com.gurihouses.home.tabfragment.viewmodel.RoomSaleViewModel
import com.gurihouses.home.tabfragment.viewmodel.UserPropertyViewModel
import com.gurihouses.propertydetails.PropertyDetailActivity
import com.gurihouses.util.CommonUtil

/**
 * A simple [Fragment] subclass.
 * Use the [RoomSaleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomSaleFragment : Fragment() {

    lateinit var binding: FragmentRoomSaleBinding
    lateinit var vm: RoomSaleViewModel
    val mUserViewModel: UserPropertyViewModel by viewModels()
    var mSaleList = arrayListOf<UserProperty>()

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
        savedInstanceState: Bundle?
    ): View {

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
        //vm = ViewModelProvider(this)[RoomSaleViewModel::class.java]

        // loadSaleData()
        loadUserProperty()

    }

    private fun loadUserProperty() {
        mUserViewModel.getUserPropertyDetails()
        /* User Property list */
        mUserViewModel.mUserPropertyResponse?.observe(viewLifecycleOwner, { it ->
            if (it != null) {

                val statusCode = it.status
                val message = it.message
                if (statusCode) {

                    if (it.data.isNotEmpty()) {

                        it.data.forEach {

                            if (it.listing_type == "Sale") {

                                mSaleList.add(
                                    UserProperty(
                                        it.id,
                                        it.owner_id,
                                        it.title,
                                        it.location,
                                        it.price,
                                        it.des,
                                        it.listing_type,
                                        it.property_type,
                                        it.image,
                                        it.rooms,
                                        it.created_at,
                                        it.code,
                                        it.status,
                                        it.approved
                                    )
                                )
                            }

                        }
                        setUpUserProperty(mSaleList)

                    }

                }

            }

        })

        mUserViewModel.errorMsg?.observe(viewLifecycleOwner, Observer {
            if (it != null) {

                CommonUtil.showMessage(requireContext(), it.toString())

            }

        })

//        mUserViewModel.loadingStatus?.observe(viewLifecycleOwner, Observer {
//            if (it == true) {
//                binding.progressBar.visibility = View.VISIBLE
//            } else {
//                binding.progressBar.visibility = View.GONE
//            }
//
//        })


    }

    private fun setUpUserProperty(data: List<UserProperty>) {

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = RoomSaleAdapter(context, data) {

            val mDetailScreen = Intent(context, PropertyDetailActivity::class.java)
            Intent.FLAG_ACTIVITY_NEW_TASK
            Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mDetailScreen)
            activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)

        }

    }


    private fun loadUi(list: List<RoomSaleResponse>) {


    }

    private fun listener() {

    }


}