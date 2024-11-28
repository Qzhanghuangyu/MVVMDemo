package com.zhang.mymvvm.bridge.data.binding

import androidx.core.view.GravityCompat
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout

object DrawerBinderAdapter {

    // 在Java中非常OK， 在KT有问题(kapt Kotlin的注解处理器)

    // 打开抽屉 与 关闭抽屉
    @JvmStatic // 代表是 静态函数
    @BindingAdapter(value = ["isOpenDrawer"], requireAll = false)
    fun openDrawer(drawerLayout: DrawerLayout, isOpenDrawer: Boolean) {
        if (isOpenDrawer && !drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START)
        } else {
            drawerLayout.closeDrawer(GravityCompat.START)
        }


    }

    //允许抽屉打开与关闭
    @JvmStatic
    @BindingAdapter(value = ["allowDrawerOpen"], requireAll = false)
    fun allowDrawerOpen(drawerLayout: DrawerLayout,allowDrawerOpen:Boolean){
        drawerLayout.setDrawerLockMode(if (allowDrawerOpen) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    }

}