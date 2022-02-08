package com.gurihouses.postproperty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gurihouses.base.GuriBaseApp
import com.gurihouses.network.ApiInterface
import com.gurihouses.postproperty.model.CreatePostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class PostPropertyViewModel:ViewModel() {

    private var apiInterface = ApiInterface.init(GuriBaseApp.instance)
    var errorMsg: MutableLiveData<String>? = null
    var loadingStatus: MutableLiveData<Boolean>? = null
    var mPostResponse: MutableLiveData<CreatePostResponse>? = null

    init {

        errorMsg = MutableLiveData()
        loadingStatus = MutableLiveData()
        mPostResponse = MutableLiveData<CreatePostResponse>()

    }

    fun getPostProperty(contributors: HashMap<String, RequestBody>, image: List<MultipartBody.Part>){

        loadingStatus?.postValue(true)
        apiInterface.getCreatePost(contributors,image).enqueue(object : retrofit2.Callback<CreatePostResponse>{
            override fun onResponse(call: Call<CreatePostResponse>, response: Response<CreatePostResponse>) {

                if (response.body()?.status==true) {
                    loadingStatus?.value = false
                    mPostResponse?.value = response.body()
                }
                else {
                    loadingStatus?.value = false
                    errorMsg?.value = response.body()?.message

                }

            }

            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
                loadingStatus?.value = false
                errorMsg?.value = t.message
            }

        })

    }



}