package com.gurihouses.home.tabfragment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.home.tabfragment.bean.HomeTabResponse
import com.gurihouses.home.tabfragment.bean.PropertyByUserResponse
import com.gurihouses.network.ApiInterface
import retrofit2.Call
import retrofit2.Response

class UserPropertyViewModel:ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var mUserPropertyResponse: MutableLiveData<PropertyByUserResponse>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        mUserPropertyResponse = MutableLiveData<PropertyByUserResponse>()

    }

    fun getUserPropertyDetails() {

        loadingStatus?.postValue(true)
        apiInterface.getUserProperty().enqueue(object : retrofit2.Callback<PropertyByUserResponse> {

            override fun onResponse(call: Call<PropertyByUserResponse>, response: Response<PropertyByUserResponse>) {

                val url = response.raw().request.url
                Log.d("mainurl", url.toString())
                Log.e("Success", Gson().toJson(response.body()))

                if (response.body()?.status == true) {

                    loadingStatus?.value = false
                    mUserPropertyResponse?.value = response.body()

                } else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message

                }

            }

            override fun onFailure(call: Call<PropertyByUserResponse>, t: Throwable) {
                // data.value = null
                loadingStatus?.value = false
                errorMsg?.value = t.message

            }
        })


    }
}