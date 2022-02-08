package com.gurihouses.home.tabfragment.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.gurihouses.network.ApiConstants
import com.gurihouses.owner.adapter.OwnerPropertyAdapter
import com.gurihouses.owner.bean.OwnerProperty
import com.gurihouses.owner.viewmodel.OwnerViewModel
import com.gurihouses.propertydetails.PropertyDetailActivity
import com.gurihouses.util.CommonUtil
import com.gurihouses.utilities.session.SessionManager
import com.gurihouses.utilities.session.SessionVar

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
    var mOwnerList = arrayListOf<OwnerProperty>()
    val vmOwner : OwnerViewModel by viewModels()
    private lateinit var sessionManager: SessionManager
    var user_type = ""

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


        if (user_type == SessionVar.PROPERTY_OWNER){
            loadOwnerDetails()
        }else{
            loadUserProperty()
        }

        Log.d("user_type",user_type)
    }


    private fun initialization() {
        sessionManager = SessionManager(requireContext())
        user_type = sessionManager.getRole()[SessionVar.USER_TYPE].toString()


    }

    private fun loadOwnerDetails() {

        vmOwner.getOwnerPropertyDetails(2, ApiConstants.API_KEY)
        /* Get Owner Details*/
        vmOwner.mOwnerPropertyResponse?.observe(viewLifecycleOwner, {
            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {

                    if (it.data.isNotEmpty()) {

                        it.data.forEach {

                            if (it.listing_type == "Sale") {

                                mOwnerList.add(
                                    OwnerProperty(
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
                                        it.state,
                                        it.city
                                    )
                                )
                            }

                        }
                        setUpOwnerDetail(mOwnerList)

                    }

                }

            }

        })

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




    private fun listener() {

    }

    private fun setUpOwnerDetail(data: List<OwnerProperty>) {

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = OwnerPropertyAdapter(context, data) {

            val mDetailScreen = Intent(context, PropertyDetailActivity::class.java)
            Intent.FLAG_ACTIVITY_NEW_TASK
            Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mDetailScreen)
            activity?.overridePendingTransition(R.anim.fadein, R.anim.fadeout)

        }

    }

}