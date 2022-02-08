package com.gurihouses.owner.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gurihouses.R
import com.gurihouses.databinding.FragmentOwnerHomeBinding
import com.gurihouses.home.tabfragment.adapter.RoomSaleAdapter
import com.gurihouses.network.ApiConstants
import com.gurihouses.owner.adapter.OwnerPropertyAdapter
import com.gurihouses.owner.bean.OwnerProperty
import com.gurihouses.owner.viewmodel.OwnerViewModel
import com.gurihouses.propertydetails.PropertyDetailActivity
import com.gurihouses.util.CommonUtil


/**
 * A simple [Fragment] subclass.
 * Use the [OwnerHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OwnerHomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var binding: FragmentOwnerHomeBinding
    val vm : OwnerViewModel by viewModels()



    companion object {
        fun newInstance() =
            OwnerHomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentOwnerHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        listener()

        getOwnerViewModel()


    }

    private fun getOwnerViewModel() {
        binding.refreshPage.isRefreshing = true
        vm.getOwnerPropertyDetails(2,ApiConstants.API_KEY)
        /* Get Owner Details*/
        vm.mOwnerPropertyResponse?.observe(viewLifecycleOwner, {
            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {
                    binding.refreshPage.isRefreshing = false

                    setUpOwnerDetail(response.data)

                }

            }

        })

        vm.errorMsg?.observe(viewLifecycleOwner, Observer {

            if (it !=null){

                context?.let { it1 -> CommonUtil.showMessage(it1,it.toString()) }
            }

        })




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

    private fun initialization() {

    }

    private fun listener() {
        binding.refreshPage.setOnRefreshListener(this)
    }

    override fun onRefresh() {

        binding.refreshPage.isRefreshing = false
        vm.getOwnerPropertyDetails(2,ApiConstants.API_KEY)
    }

}