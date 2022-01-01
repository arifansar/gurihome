package com.gurihouses.network

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HomeClientApi {

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun getHttpClient(appContext: Context): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(NetworkConnectionInterceptor(appContext))
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .build()
                chain.proceed(newRequest)
            }).build()
    }

    private val builder = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
    fun callRetrofit(appContext: Context): ApiInterface {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = builder
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getHttpClient(appContext))
            .build()
        return retrofit.create(ApiInterface::class.java)
    }
}