package com.gurihouses.network

import android.content.Context
import com.gurihouses.home.tabfragment.bean.RoomSaleResponse
import retrofit2.Call
import retrofit2.http.GET




interface ApiInterface {


    @GET("marvel")
    fun getHeroes(): Call<List<RoomSaleResponse>>



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