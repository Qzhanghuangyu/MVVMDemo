package com.zhang.mymvvm.bridge.repository.api

import com.zhang.mymvvm.bridge.data.login_register.LoginRegisterResponse
import com.zhang.mymvvm.bridge.data.login_register.LoginRegisterResponseWrapper
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WanAndroidAPI {

    @POST("/user/register")
    @FormUrlEncoded
    suspend fun registerActionCoroutine(
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): LoginRegisterResponseWrapper<LoginRegisterResponse>

    @POST("/user/login")
    @FormUrlEncoded
    suspend fun loginActionCoroutine(
        @Field("username") username: String,
        @Field("password") password: String
    )
            : LoginRegisterResponseWrapper<LoginRegisterResponse> // 返回值

}