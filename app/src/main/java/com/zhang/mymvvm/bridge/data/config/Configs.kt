package com.zhang.mymvvm.bridge.data.config

import android.os.Environment
import com.zhang.architecture.utils.Utils

object Configs {
    //封面路径
    val COVER_PATH = Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath

    const val  TAG ="zhangaaaaaaa"
}