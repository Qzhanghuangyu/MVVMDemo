package com.zhang.mymvvm.bridge.repository.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {
    private object Holder {
        val INSTANCE = APIClient()

    }

    companion object {

        val instance = Holder.INSTANCE

    }


    fun <T> instanceRetrofit(apiInterface: Class<T>): T {

        val mOkHttpClient = OkHttpClient().newBuilder().myApply {

            readTimeout(10000, TimeUnit.SECONDS)
            connectTimeout(10000, TimeUnit.SECONDS)
            writeTimeout(10000, TimeUnit.SECONDS)

        }.build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(mOkHttpClient)
           .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(apiInterface)
//            .addConverterFactory(GsonConverterFactory())

    }

    fun <T> T.myApply(mm: T.() -> Unit): T {
        mm()
        return this


    }

}