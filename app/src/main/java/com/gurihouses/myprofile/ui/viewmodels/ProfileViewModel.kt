package com.gurihouses.myprofile.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.network.ApiInterface
import com.gurihouses.profile.models.ProfileResponse
import retrofit2.Call
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var profileResponse: MutableLiveData<ProfileResponse>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        profileResponse = MutableLiveData<ProfileResponse>()

    }


    fun getProfile(userId: String,apiKey: String){

        loadingStatus?.postValue(true)
        apiInterface.getProfile(userId,apiKey).enqueue(object : retrofit2.Callback<ProfileResponse>{
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {

                if (response.body()?.status==true) {
                    loadingStatus?.value = false
                    profileResponse?.value = response.body()
                }
                else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message!!

                }

            }
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                loadingStatus?.value = false
                errorMsg?.value = t.message
            }


        })

    }


}