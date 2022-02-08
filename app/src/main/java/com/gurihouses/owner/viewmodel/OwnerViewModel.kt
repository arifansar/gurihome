package com.gurihouses.owner.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.network.ApiInterface
import com.gurihouses.owner.bean.OwnerPropertyResponse
import retrofit2.Call
import retrofit2.Response

class OwnerViewModel:ViewModel() {


    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var mOwnerPropertyResponse: MutableLiveData<OwnerPropertyResponse>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        mOwnerPropertyResponse = MutableLiveData<OwnerPropertyResponse>()

    }

    fun getOwnerPropertyDetails(ownerId:Int,apiKey:String) {

        loadingStatus?.postValue(true)
        apiInterface.getOwnerProperty(ownerId,apiKey).enqueue(object : retrofit2.Callback<OwnerPropertyResponse> {

            override fun onResponse(call: Call<OwnerPropertyResponse>, response: Response<OwnerPropertyResponse>) {

                val url = response.raw().request.url
                Log.d("mainurl", url.toString())
                Log.e("Success", Gson().toJson(response.body()))

                if (response.body()?.status == true) {

                    loadingStatus?.value = false
                    mOwnerPropertyResponse?.value = response.body()

                } else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message

                }

            }

            override fun onFailure(call: Call<OwnerPropertyResponse>, t: Throwable) {
                // data.value = null
                loadingStatus?.value = false
                errorMsg?.value = t.message

            }
        })


    }
}