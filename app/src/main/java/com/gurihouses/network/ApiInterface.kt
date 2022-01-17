package com.gurihouses.network

import android.content.Context
import com.gurihouses.home.tabfragment.bean.RoomSaleResponse
import com.gurihouses.signup.ui.activities.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {


    @POST(ApiConstants.API_SIGNUP)
    fun getRegister(
        @Query("role") role: String,
        @Query("fname") fname: String,
        @Query("lname") lname: String,
        @Query("mobile") mobile: String,
        @Query("device_token") device_token: String,
        @Query("email") email: String
    ): Call<SignUpResponse>


    companion object Factory {
        @Volatile
        private var instance: ApiInterface? = null
        fun init(context: Context): ApiInterface {
            return instance ?: synchronized(this) {
                instance ?: HomeClientApi.callRetrofit(context).also { instance = it }
            }
        }
    }

}