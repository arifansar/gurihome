package com.gurihouses.home.tabfragment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.home.tabfragment.bean.StateResponse
import com.gurihouses.network.ApiInterface
import retrofit2.Call
import retrofit2.Response

class StateViewModel:ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var stateResponse: MutableLiveData<StateResponse>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        stateResponse = MutableLiveData<StateResponse>()

    }

    fun getStateDetails() {

        loadingStatus?.postValue(true)
        apiInterface.getState().enqueue(object : retrofit2.Callback<StateResponse> {

            override fun onResponse(call: Call<StateResponse>, response: Response<StateResponse>) {

                val url = response.raw().request.url
                Log.d("mainurl", url.toString())
                Log.e("Success", Gson().toJson(response.body()))

                if (response.body()?.status == true) {

                    loadingStatus?.value = false
                    stateResponse?.value = response.body()

                } else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message

                }

            }

            override fun onFailure(call: Call<StateResponse>, t: Throwable) {
                // data.value = null
                loadingStatus?.value = false
                errorMsg?.value = t.message

            }
        })


    }
}