package com.gurihouses.propertydetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.gurihouses.R
import com.gurihouses.databinding.ActivityPropertyDetailBinding
import com.gurihouses.postproperty.viewmodel.PostPropertyViewModel
import com.gurihouses.propertydetails.adapter.BreakingAlertAdapter
import com.gurihouses.utilities.session.SessionManager
import com.gurihouses.utilities.session.SessionVar
import com.gurihouses.propertydetails.viewmodel.ProviderDetailsViewModel
import com.gurihouses.util.CommonUtil

class PropertyDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityPropertyDetailBinding
    private lateinit var sessionManager: SessionManager
    var user_type = ""
    private val propertyDetailsViewModel: ProviderDetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initialization()
        listener()


        if (user_type == SessionVar.PROPERTY_OWNER){
           binding.rlMain4.visibility = View.GONE
            binding.rlMain5.visibility = View.VISIBLE
        }


    }

    private fun initialization() {
        binding.inlDetailToolbar.ttitle.text = "Property detail"
        sessionManager = SessionManager(this)
        user_type = sessionManager.getRole()[SessionVar.USER_TYPE].toString()

        val list = arrayListOf<Int>()

        list.add(R.drawable.room1)
        list.add(R.drawable.ic_room)
        list.add(R.drawable.room1)
        list.add(R.drawable.ic_room)
        list.add(R.drawable.room1)
        list.add(R.drawable.ic_room)

        binding.viewPager2.startAutoScroll()
        binding.viewPager2.interval = 3000
        binding.viewPager2.isCycle = true
        binding.viewPager2.isStopScrollWhenTouch = true
        binding.tabLayout1.setupWithViewPager(binding.viewPager2, true)

        binding.viewPager2.adapter = BreakingAlertAdapter(this, list)
        loadPropertyDetailsData()


    }

    private fun listener() {

        binding.inlDetailToolbar.backIcon.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

    }

    fun loadPropertyDetailsData(){
    propertyDetailsViewModel.getPropertyDetails(9,"e10adc3949ba59abbe56e057f20f883e",1)
    propertyDetailsViewModel.providerDetailsResponse?.observe(this, Observer {
            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode == true) {

                    if (response.data?.sql!=null) {
                        binding.saleName.setText(response.data?.sql?.title)
                        binding.saleLocation.setText(response.data?.sql?.location)
                        binding.propertyRoomNumber.setText(response.data?.sql?.rooms)
                        binding.propertyPrice.setText(response.data?.sql?.price)
                        binding.propertyDesc.setText(response.data?.sql?.des)
                    }

                }

            }

        })

        propertyDetailsViewModel.errorMsg?.observe(this, Observer {
            if (it != null) {

                CommonUtil.showMessage(this, it.toString())

            }

        })


    }

    private fun setUpProviderData() {



    }
}