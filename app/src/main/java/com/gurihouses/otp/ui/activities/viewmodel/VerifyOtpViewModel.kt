package com.gurihouses.otp.ui.activities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.network.ApiInterface
import com.gurihouses.otp.ui.activities.model.OtpResponse
import com.gurihouses.signup.ui.activities.model.SignUpResponse
import retrofit2.Call
import retrofit2.Response

class VerifyOtpViewModel: ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var otpResponse: MutableLiveData<OtpResponse>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        otpResponse = MutableLiveData<OtpResponse>()

    }

    fun verifyOtp(number: String,otp: String){

        loadingStatus?.postValue(true)
        apiInterface.verifyOtp(number,otp).enqueue(object : retrofit2.Callback<OtpResponse>{
            override fun onResponse(call: Call<OtpResponse>, response: Response<OtpResponse>) {

               if (response.body()?.status==true) {
                   loadingStatus?.value = false
                   otpResponse?.value = response.body()
               }
               else {
                   loadingStatus?.value = false
                   errorMsg?.value = response.body()?.message!!

               }

            }

            override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                loadingStatus?.value = false
                errorMsg?.value = t.message
            }

        })

    }


}