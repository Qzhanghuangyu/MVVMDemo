package com.zhang.mymvvm.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.zhang.mymvvm.MainActivity
import com.zhang.mymvvm.R
import com.zhang.mymvvm.base.BaseActivity
import com.zhang.mymvvm.bridge.data.login_register.Session
import com.zhang.mymvvm.bridge.state.LoginRequestViewModel
import com.zhang.mymvvm.bridge.state.LoginViewModel
import com.zhang.mymvvm.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private var loginBinding: ActivityLoginBinding? = null
    private var loginViewModel: LoginViewModel? = null
    private var loginRequestViewModel: LoginRequestViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = getActivityViewModelProvider(this).get(LoginViewModel::class.java)

        loginRequestViewModel =
            getActivityViewModelProvider(this).get(LoginRequestViewModel::class.java)
        loginBinding?.lifecycleOwner = this
        loginBinding?.vm = loginViewModel
        loginBinding?.click = ClickProxy()

        loginRequestViewModel?.loginSuccessData?.observe(this, {
            //登录成功
            mSharedViewModel.session.value = Session(true, it)
            startToMain()

        })

        loginRequestViewModel?.loginErrorData?.observe(this, {

            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }

    fun startToMain() = startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    inner class ClickProxy() {
        fun loginAction() {
            loginRequestViewModel?.requestLoginAction(
                this@LoginActivity,
                loginViewModel!!.userName.value!!, loginViewModel!!.password.value!!
            )
        }

    }

}