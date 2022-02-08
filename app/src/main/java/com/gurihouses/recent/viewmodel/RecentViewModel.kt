package com.gurihouses.recent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.network.ApiInterface
import com.gurihouses.recent.model.RecentViewResponse
import retrofit2.Call
import retrofit2.Response

class RecentViewModel : ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var recentViewResoponse: MutableLiveData<RecentViewResponse>? = null


    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        recentViewResoponse = MutableLiveData<RecentViewResponse>()

    }

    fun getRecentView(userId: Int, apiKey: String){

        loadingStatus?.postValue(true)
        apiInterface.getRecentView(userId,apiKey).enqueue(object : retrofit2.Callback<RecentViewResponse>{
            override fun onResponse(
                call: Call<RecentViewResponse>, response: Response<RecentViewResponse>
            ) {
                if (response.body()?.status== true){
                    loadingStatus?.value == false
                    recentViewResoponse?.value = response.body()
                }
                else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message!!

                }

            }

            override fun onFailure(call: Call<RecentViewResponse>, t: Throwable) {

                loadingStatus?.value = false
                errorMsg?.value = t.message
            }


        }
        )
    }

}