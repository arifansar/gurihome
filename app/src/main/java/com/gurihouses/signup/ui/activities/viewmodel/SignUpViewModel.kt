package com.gurihouses.signup.ui.activities.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.network.ApiInterface
import com.gurihouses.network.ApiStatusConstant
import com.gurihouses.signup.ui.activities.model.SignUpResponse
import retrofit2.Call
import retrofit2.Response

class SignUpViewModel: ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var signUpResponse: MutableLiveData<SignUpResponse>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        signUpResponse = MutableLiveData<SignUpResponse>()

    }

    fun getSignUpDetails(role:String,fname:String,lname:String, mobile:String,device_token:String,email:String) {

        loadingStatus?.postValue(true)
        apiInterface.getRegister(role, fname, lname, mobile, device_token, email).enqueue(object : retrofit2.Callback<SignUpResponse> {

            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {

                val url = response.raw().request.url
                Log.d("mainurl", url.toString())
                Log.e("Success", Gson().toJson(response.body()))

                if (response.body()?.status == true) {

                    loadingStatus?.value = false
                    signUpResponse?.value = response.body()

                } else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message

                }

            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                // data.value = null
                loadingStatus?.value = false
                errorMsg?.value = t.message

            }
        })


    }
}