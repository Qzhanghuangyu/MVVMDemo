package com.zhang.mymvvm.bridge.data.bean

import java.io.File

data class DownLoadFile(var progress: Int = 0, var file: File?= null, var isForgive: Boolean = false) {

}