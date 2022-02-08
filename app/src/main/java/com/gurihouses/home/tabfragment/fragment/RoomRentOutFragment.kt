package com.gurihouses.home.tabfragment.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurihouses.R
import com.gurihouses.chat.bottomchat.ui.ChatActivity
import com.gurihouses.databinding.FragmentRoomRentOutBinding
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
 * Use the [RoomRentOutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomRentOutFragment : Fragment() {

    lateinit var binding: FragmentRoomRentOutBinding
    lateinit var vm : RoomSaleViewModel
    val mUserViewModel : UserPropertyViewModel by viewModels()
    val vmOwner : OwnerViewModel by viewModels()
    private lateinit var sessionManager: SessionManager
    var user_type = ""

    companion object {

        @JvmStatic
        fun newInstance() =
            RoomRentOutFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRoomRentOutBinding.inflate(layoutInflater)
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

                    setUpOwnerDetail(response.data)

                }

            }

        })

    }

    private fun initialization() {
        sessionManager = SessionManager(requireContext())
        user_type = sessionManager.getRole()[SessionVar.USER_TYPE].toString()


    }

    private fun loadUserProperty() {
        mUserViewModel.getUserPropertyDetails()
        /* User Property list */
        mUserViewModel.mUserPropertyResponse?.observe(viewLifecycleOwner, Observer {
            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {

                    if (response.data.isNotEmpty()) {
                        setUpUserProperty(response.data)
                    }

                }

            }

        })




    }

    private fun setUpUserProperty(data: List<UserProperty>) {

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = RoomSaleAdapter(context, data){

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