package com.zhang.mymvvm.bridge.state

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhang.mymvvm.bridge.data.login_register.LoginRegisterResponse
import com.zhang.mymvvm.bridge.repository.HttpRequestManager
import kotlinx.coroutines.launch

class RequestRegisterViewModel : ViewModel() {

    val successData: MutableLiveData<LoginRegisterResponse> by lazy { MutableLiveData<LoginRegisterResponse>() }
    val failedData: MutableLiveData<String> by lazy { MutableLiveData<String>() }


    fun requestRegister(context: Context, userName: String, password: String) {

        viewModelScope.launch {

            val dataResult = HttpRequestManager.instance.registerAction(userName, password)
            if (dataResult != null) {
                successData.value = dataResult
            } else {
                failedData.value = "注册失败，请重试"
            }


        }

    }
    fun requestLogin(context: Context, userName: String, password: String) {

        viewModelScope.launch {

            val dataResult = HttpRequestManager.instance.LoginAction(userName, password)
            if (dataResult != null) {
                successData.value = dataResult
            } else {
                failedData.value = "登录失败，发送了意外，请检查用户名与密码 或者 你的垃圾代码"
            }


        }

    }


}