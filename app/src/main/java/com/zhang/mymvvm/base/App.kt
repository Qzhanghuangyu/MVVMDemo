package com.zhang.mymvvm.base

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.zhang.architecture.utils.Utils

class App : Application(), ViewModelStoreOwner {

    private var mAppViewModelStore: ViewModelStore? = null
    private var mFactory: ViewModelProvider.Factory? = null


    override fun onCreate() {
        super.onCreate()

        Utils.init(this)
        mAppViewModelStore = ViewModelStore()

    }
    //暴露给baseActivity和baseFragment使用的
    fun getAppViewModelProvider(activity: Activity): ViewModelProvider {

        return ViewModelProvider(
            (activity.applicationContext as App),
            (activity.applicationContext as App).getAppFactory(activity)!!
        )
    }

    private fun getAppFactory(activity: Activity): ViewModelProvider.Factory? {
        val application = checkApplication(activity)
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        }
        return mFactory

    }


    // 监测下 Activity是否为null
    private fun checkApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException(
                "Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call."
            )
    }

    //检测Activity是否为null
    private fun checkActivity(fragment: Fragment): Activity? {

        return fragment.activity
            ?: throw IllegalStateException("Can't create ViewModelProvider for detached fragment")
    }


    override val viewModelStore: ViewModelStore
        get() = mAppViewModelStore!!
}