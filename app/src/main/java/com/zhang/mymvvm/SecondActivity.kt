package com.zhang.mymvvm

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhang.mymvvm.base.BaseActivity
@Route(path = "/test/SecondActivity")
class SecondActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}