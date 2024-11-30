package com.zhang.mymvvm.bridge.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var userName = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var loginState = MutableLiveData<Boolean>()

    init {
        userName.value = ""
        password.value = ""
        loginState.value = false
    }
}