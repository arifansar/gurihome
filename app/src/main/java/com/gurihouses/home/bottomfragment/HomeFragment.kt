package com.gurihouses.home.bottomfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gurihouses.databinding.FragmentHomeBinding
import com.gurihouses.home.tabfragment.adapter.TabFragmentAdapter
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.gurihouses.R
import com.gurihouses.home.tabfragment.viewmodel.HomeTabViewModel
import com.gurihouses.home.tabfragment.viewmodel.StateViewModel
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener{

    lateinit var binding: FragmentHomeBinding
    private var indicatorWidth = 0
    private val mViewModel: StateViewModel by viewModels()
    private val mTabViewModel: HomeTabViewModel by viewModels()
    var state_id: Int = 0

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {

            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //view model get state
        getStateViewModel()

        initialization()
        listener()

    }



    private fun initialization() {

    }

    private fun listener() {
        binding.refreshPage.setOnRefreshListener(this)


    }

    private fun getStateViewModel() {

        binding.refreshPage.isRefreshing = true
        mViewModel.getStateDetails()
        /* Get State id*/
        mViewModel.stateResponse?.observe(viewLifecycleOwner, {
            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {
                    binding.refreshPage.isRefreshing = false
                    response.data.forEach {
                        state_id = it.id
                    }

                    getHomeTabViewModel(state_id)

                }

            }

        })



    }

    private fun getHomeTabViewModel(state_id: Int) {
        val profileArray = ArrayList<String>()
        mTabViewModel.getHomeTabDetails(state_id)
        mTabViewModel.HomeTabResponse?.observe(viewLifecycleOwner, {
            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {

                    response.data.forEach {
                        profileArray.add(it.name)
                    }

                  if (profileArray.isNotEmpty()){
                      setHomeTab(profileArray)
                  }else{
                      binding.txtNoData.visibility = View.VISIBLE
                      binding.tab.visibility = View.GONE
                  }


                }
            }


        })


    }

    private fun setHomeTab(profileArray: ArrayList<String>) {

        binding.tab.visibility = View.VISIBLE
        binding.viewPager.adapter = TabFragmentAdapter(childFragmentManager, lifecycle)
        binding.viewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->

            tab.setCustomView(R.layout.home_custom_tab_name)
            val tv = tab.customView?.findViewById<TextView>(R.id.tab_text)
            tv?.text = profileArray[position]
        }.attach()


    }

    override fun onRefresh() {

        binding.refreshPage.isRefreshing = false
        getStateViewModel()
    }


}