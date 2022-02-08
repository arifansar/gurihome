package com.gurihouses.contactus.ui.activities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.contactus.ui.activities.model.ContactUsResponse
import com.gurihouses.getotp.ui.activities.model.LoginResponse
import com.gurihouses.network.ApiInterface
import retrofit2.Call
import retrofit2.Response

class ContactUsViewModel : ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var contactUsResponse: MutableLiveData<ContactUsResponse>?=null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        contactUsResponse = MutableLiveData<ContactUsResponse>()

    }

    fun contactUs(name: String,email: String,mobile: String,message: String,apiKey: String){

        loadingStatus?.postValue(true)
        apiInterface.contactUs(name,email,mobile,message,apiKey).enqueue(object : retrofit2.Callback<ContactUsResponse>{
            override fun onResponse(call: Call<ContactUsResponse>, response: Response<ContactUsResponse>) {

                if (response.body()?.status==true) {
                    loadingStatus?.value = false
                    contactUsResponse?.value = response.body()
                }
                else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message!!

                }

            }

            override fun onFailure(call: Call<ContactUsResponse>, t: Throwable) {
                loadingStatus?.value = false
                errorMsg?.value = t.message
            }

        })


    }

}