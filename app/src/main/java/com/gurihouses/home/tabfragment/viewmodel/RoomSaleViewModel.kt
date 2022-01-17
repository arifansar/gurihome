package com.gurihouses.home.tabfragment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.home.tabfragment.bean.RoomSaleResponse
import com.gurihouses.network.ApiInterface
import retrofit2.Call
import retrofit2.Response

class RoomSaleViewModel():ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var mForgotResponse: MutableLiveData<List<RoomSaleResponse>>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        mForgotResponse = MutableLiveData<List<RoomSaleResponse>>()

    }

    fun getSaleRooms() {

//        loadingStatus?.postValue(true)
//        apiInterface.getHeroes().enqueue(object : retrofit2.Callback<List<RoomSaleResponse>> {
//
//            override fun onResponse(call: Call<List<RoomSaleResponse>>, response: Response<List<RoomSaleResponse>>) {
//
//                val url = response.raw().request.url
//                Log.d("mainurl", url.toString())
//                if (response.isSuccessful) {
//                    loadingStatus?.value = false
//                    mForgotResponse?.value = response.body()
//
//                } else {
//                    loadingStatus?.value = false
//                    errorMsg?.value = "no data available"
//                }
//
//            }
//
//            override fun onFailure(call: Call<List<RoomSaleResponse>>, t: Throwable) {
//                // data.value = null
//                Log.d("mainurl", t.toString())
//                loadingStatus?.value = false
//                errorMsg?.value = t.message
//
//            }
//        })
//

    }

}