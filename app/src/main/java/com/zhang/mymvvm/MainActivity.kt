package com.zhang.mymvvm

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge

import androidx.databinding.DataBindingUtil
import com.zhang.mymvvm.base.BaseActivity
import com.zhang.mymvvm.bridge.data.config.Configs
import com.zhang.mymvvm.bridge.state.MainActivityViewModel
import com.zhang.mymvvm.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    var mainBinding: ActivityMainBinding? = null
    var mainActivityViewModel: MainActivityViewModel? = null
    private var isListened = false // 被倾听 为了扩展，目前还用不上

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //   mainActivityViewModel = getActivityViewM
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding?.lifecycleOwner = this
        mainBinding?.vm = mainActivityViewModel


        mSharedViewModel.activityCanBeClosedDirectly.observe(this, { aBoolean ->
            // 先不写，作用不大，以后扩展用的
            Log.d(
                Configs.TAG,
                if (aBoolean) "哈哈我来测试一下 " else "你说呢 "
            )
            Log.i("adjaad","哈哈哈哈哈哈哈哈哈哈哈哈啊哈哈哈")

        })

        mSharedViewModel.session.observe(this, {
            Log.d(Configs.TAG, it.toString())
        })


        //监听2 ，发送打开菜单的指令
        mSharedViewModel.openOrCloseDrawer.observe(this, {
            mainActivityViewModel!!.openDrawer.value = it

        })


        mSharedViewModel.enableSwipeDrawer.observe(this, {
            mainActivityViewModel!!.allowDrawerOpen.value = it

        })







    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (!isListened) {
            // 此字段只要发生了改变，就会 添加监听（可以弹上来的监听） ,可见才监听
            mSharedViewModel.timeToAddSlideListener.value = true
            isListened = true
        }
    }


    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
//        super.onBackPressed()
        mSharedViewModel.closeSlidePanelIfExpanded.value = true
    }
}