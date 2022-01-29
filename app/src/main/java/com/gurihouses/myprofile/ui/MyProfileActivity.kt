package com.gurihouses.myprofile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.gurihouses.R
import com.gurihouses.databinding.ActivityMyProfileBinding
import com.gurihouses.myprofile.ui.viewmodels.ProfileViewModel
import com.gurihouses.util.CommonUtil

class MyProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyProfileBinding
    private val mViewModel: ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initialized()
        listener()
        getViewModel()
        getProfileApi()

    }



    private fun listener() {

    }

    private fun initialized() {

    }

    private fun getViewModel() {

        mViewModel.profileResponse?.observe(this,{
            if (it != null) {
                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {

                    CommonUtil.showMessage(this, response.message)
                    binding.editName.setText(response.data.fname)
                    binding.editEmail.setText(response.data.email)
                    binding.editMobile.setText(response.data.mobile)

                }

            }

        })
        mViewModel.errorMsg?.observe(this, Observer {
            if (it != null) {
                CommonUtil.showMessage(this, it.toString())
            }

        })

        mViewModel.loadingStatus?.observe(this, Observer {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

        })

    }
    private fun getProfileApi(){
        mViewModel.getProfile("10","e10adc3949ba59abbe56e057f20f883e")
    }
}