package com.gurihouses.network

import android.content.Context
import com.gurihouses.getotp.ui.activities.model.LoginResponse
import com.gurihouses.home.tabfragment.bean.HomeTabResponse
import com.gurihouses.home.tabfragment.bean.PropertyByUserResponse
import com.gurihouses.home.tabfragment.bean.RoomSaleResponse
import com.gurihouses.home.tabfragment.bean.StateResponse
import com.gurihouses.otp.ui.activities.model.OtpResponse
import com.gurihouses.profile.models.ProfileResponse
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

    @POST(ApiConstants.API_VERIFY_OTP)
    fun verifyOtp(
        @Query("mobile") mobile: String,
        @Query("otp") otp: String
    ): Call<OtpResponse>

    @POST(ApiConstants.API_LOGIN)
    fun login(
        @Query("mobile") mobile: String
    ): Call<LoginResponse>

    @POST(ApiConstants.API_PROFILE)
    fun getProfile(
        @Query("user_id") userId: String
    ): Call<ProfileResponse>

    @POST(ApiConstants.API_HOME_TAB)
    fun getHomeTabList(
        @Query("state_id")state_id:Int
    ):Call<HomeTabResponse>

    @POST(ApiConstants.API_STATE)
    fun getState():Call<StateResponse>

    @GET(ApiConstants.API_USER_PROPERTY)
    fun getUserProperty():Call<PropertyByUserResponse>


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