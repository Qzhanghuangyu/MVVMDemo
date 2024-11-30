package com.zhang.mymvvm.bridge.state

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhang.mymvvm.bridge.data.login_register.LoginRegisterResponse
import com.zhang.mymvvm.bridge.data.login_register.LoginRegisterResponseWrapper
import com.zhang.mymvvm.bridge.repository.HttpRequestManager
import kotlinx.coroutines.launch

class LoginRequestViewModel : ViewModel() {

    val loginSuccessData: MutableLiveData<LoginRegisterResponse> by lazy { MutableLiveData<LoginRegisterResponse>() }
    val loginErrorData: MutableLiveData<String> by lazy { MutableLiveData<String>() }


    fun requestLoginAction(context: Context, userName: String, password: String) {
        if (userName.isBlank() || password.isBlank()) {
            Toast.makeText(context, "用户名或密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }

        viewModelScope.launch {
            val dataResult = HttpRequestManager.instance.LoginAction(userName, password)
            if (dataResult != null) {
                loginSuccessData.value = dataResult
            } else {
                loginErrorData.value ="登录失败，发送了意外，请检查用户名与密码 或者 你的垃圾代码"
            }


        }


    }
}