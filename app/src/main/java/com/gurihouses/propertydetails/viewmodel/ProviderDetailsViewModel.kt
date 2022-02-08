package com.gurihouses.propertydetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.network.ApiInterface
import com.gurihouses.propertydetails.model.ProviderDetailsResponse
import com.gurihouses.recent.model.RecentViewResponse
import com.gurihouses.signup.ui.activities.model.SignUpResponse
import retrofit2.Call
import retrofit2.Response

class ProviderDetailsViewModel: ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var providerDetailsResponse: MutableLiveData<ProviderDetailsResponse>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        providerDetailsResponse = MutableLiveData<ProviderDetailsResponse>()

    }

    fun getPropertyDetails(providerId: Int,apiKey: String,userId: Int){

        loadingStatus?.postValue(true)
        apiInterface.getPropertyDetails(providerId,apiKey,userId).enqueue(object : retrofit2.Callback<ProviderDetailsResponse>{

            override fun onResponse(
                call: Call<ProviderDetailsResponse>, response: Response<ProviderDetailsResponse>
            ) {
                if (response.body()?.status== true){
                    loadingStatus?.value == false
                    providerDetailsResponse?.value = response.body()
                }
                else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message!!

                }

            }

            override fun onFailure(call: Call<ProviderDetailsResponse>, t: Throwable) {

                loadingStatus?.value = false
                errorMsg?.value = t.message
            }
        })

    }


}