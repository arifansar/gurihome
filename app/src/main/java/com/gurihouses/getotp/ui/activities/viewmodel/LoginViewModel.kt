package com.gurihouses.getotp.ui.activities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.getotp.ui.activities.model.LoginResponse
import com.gurihouses.network.ApiInterface
import com.gurihouses.otp.ui.activities.model.OtpResponse
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var loginResponse: MutableLiveData<LoginResponse>?=null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        loginResponse = MutableLiveData<LoginResponse>()

    }

    fun login(number: String){

        loadingStatus?.postValue(true)
        apiInterface.login(number).enqueue(object : retrofit2.Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (response.body()?.status==true) {
                    loadingStatus?.value = false
                    loginResponse?.value = response.body()
                }
                else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message!!

                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loadingStatus?.value = false
                errorMsg?.value = t.message
            }

        })

    }






}