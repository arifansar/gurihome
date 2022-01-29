package com.gurihouses.postproperty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.network.ApiInterface
import com.gurihouses.postproperty.model.PostPropertyResponse
import com.gurihouses.profile.models.ProfileResponse
import retrofit2.Call
import retrofit2.Response

class PostPropertyViewModel : ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var postPropertyResponse: MutableLiveData<PostPropertyResponse>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        postPropertyResponse = MutableLiveData<PostPropertyResponse>()

    }

    fun postProperty(ownerId: Int,title: String,location: String,lat: String,lng: String,
                     price: String,state: String,city: String,des: String,image: String,
                     rooms: String,area: String,balcony: String,face: String,apiKey: String){

        loadingStatus?.postValue(true)
        apiInterface.postProperty(ownerId,title,location,lat,lng,price,state,city,des,
        image,rooms,area,balcony,face,apiKey).enqueue(object : retrofit2.Callback<PostPropertyResponse>{

            override fun onResponse(call: Call<PostPropertyResponse>, response: Response<PostPropertyResponse>) {

                if (response.body()?.status==true) {
                    loadingStatus?.value = false
                    postPropertyResponse?.value = response.body()
                }
                else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message!!

                }

            }
            override fun onFailure(call: Call<PostPropertyResponse>, t: Throwable) {
                loadingStatus?.value = false
                errorMsg?.value = t.message
            }

        })


    }



}