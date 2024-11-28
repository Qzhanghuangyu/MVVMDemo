
package com.zhang.mymvvm.bridge.repository
import androidx.lifecycle.MutableLiveData
import com.zhang.mymvvm.bridge.data.bean.DownloadFile
import com.zhang.mymvvm.bridge.data.bean.LibraryInfo
import com.zhang.mymvvm.bridge.data.bean.TestAlbum

/**
 * 远程请求标准接口（在仓库里面）
 * 只为 HttpRequestManager 服务
 */
interface IRemoteRequest {

   suspend fun getFreeMusic(freeMusicesLiveData: MutableLiveData<TestAlbum>?)

    fun getLibraryInfo(liveData: MutableLiveData<List<LibraryInfo>>?)

    // 下载文件
    fun downloadFile(liveData: MutableLiveData<DownloadFile>?)
}