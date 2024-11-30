package com.zhang.mymvvm

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.zhang.mymvvm.base.BaseActivity
import com.zhang.mymvvm.bridge.data.config.Configs
import com.zhang.mymvvm.bridge.state.RegisterViewModel
import com.zhang.mymvvm.bridge.state.RequestRegisterViewModel
import com.zhang.mymvvm.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity() {

     var registerViewModel: RegisterViewModel? = null
     var requestRegisterViewModel: RequestRegisterViewModel? = null
     var binding: ActivityRegisterBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerViewModel = getActivityViewModelProvider(this).get(RegisterViewModel::class.java)
        requestRegisterViewModel =
            getActivityViewModelProvider(this).get(RequestRegisterViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding?.lifecycleOwner = this
        binding?.vm = registerViewModel
        binding?.click = ClickProxy()

        requestRegisterViewModel?.successData?.observe(this, {
            //注册成功
            Log.d(Configs.TAG, "注册成功,${it.toString()}")


        })

        requestRegisterViewModel?.failedData?.observe(this, {
            //注册失败
            Log.d(Configs.TAG, "注册失败,${it}")
        })
    }


    inner class ClickProxy {

        fun registerAction() {
            if (registerViewModel!!.userName.value.isNullOrBlank() || registerViewModel!!.userPwd.value.isNullOrBlank()) {
                registerViewModel?.registerState?.value = "用户名 或 密码 为空，请你好好检查"
                return
            }
//            requestRegisterViewModel?.requestRegister(
//                this@RegisterActivity,
//                registerViewModel!!.userName.value!!,
//                registerViewModel!!.userPwd.value!!
//            )
            requestRegisterViewModel?.requestLogin(
                this@RegisterActivity,
                registerViewModel!!.userName.value!!,
                registerViewModel!!.userPwd.value!!
            )
        }
    }
}