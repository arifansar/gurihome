package com.gurihouses.home.tabfragment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.home.tabfragment.bean.HomeTabResponse
import com.gurihouses.network.ApiInterface
import retrofit2.Call
import retrofit2.Response

class HomeTabViewModel :ViewModel(){

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var HomeTabResponse: MutableLiveData<HomeTabResponse>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        HomeTabResponse = MutableLiveData<HomeTabResponse>()

    }

    fun getHomeTabDetails(state_id:Int) {

        loadingStatus?.postValue(true)
        apiInterface.getHomeTabList(state_id).enqueue(object : retrofit2.Callback<HomeTabResponse> {

            override fun onResponse(call: Call<HomeTabResponse>, response: Response<HomeTabResponse>) {

                val url = response.raw().request.url
                Log.d("mainurl", url.toString())
                Log.e("Success", Gson().toJson(response.body()))

                if (response.body()?.status == true) {

                    loadingStatus?.value = false
                    HomeTabResponse?.value = response.body()

                } else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message

                }

            }

            override fun onFailure(call: Call<HomeTabResponse>, t: Throwable) {
                // data.value = null
                loadingStatus?.value = false
                errorMsg?.value = t.message

            }
        })


    }
}