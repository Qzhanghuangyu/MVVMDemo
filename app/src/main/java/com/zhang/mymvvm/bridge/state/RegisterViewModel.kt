package com.zhang.mymvvm.bridge.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhang.architecture.bridge.callback.UnPeekLiveData

class RegisterViewModel : ViewModel() {

    //注册状态

    val registerState = MutableLiveData<String>()//注册成功 ，失败 等等
    val userName = MutableLiveData<String>()//注册成功 ，失败 等等
    val userPwd = MutableLiveData<String>()//注册成功 ，失败 等等


    init {
        registerState.value = ""
        userPwd.value = ""
        userName.value = ""
    }
}