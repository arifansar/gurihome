package com.gurihouses.home.bottomfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gurihouses.databinding.FragmentHomeBinding
import com.gurihouses.home.tabfragment.adapter.TabFragmentAdapter
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import com.gurihouses.R


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var indicatorWidth = 0

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


        setHomeTab()

    }

    private fun setHomeTab() {

        val profileArray = arrayOf("Sale", "Rent out",)

        binding.viewPager.adapter = TabFragmentAdapter(childFragmentManager,lifecycle)
        binding.viewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tab, binding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->

                tab.setCustomView(R.layout.home_custom_tab_name)
                val tv = tab.customView?.findViewById<TextView>(R.id.tab_text)
                tv?.text = profileArray[position]
            }).attach()




    }


}