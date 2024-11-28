package com.zhang.mymvvm.bridge.state

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhang.mymvvm.bridge.data.bean.TestAlbum
import com.zhang.mymvvm.bridge.repository.HttpRequestManager
import kotlinx.coroutines.launch

class MusicRequestViewModel : ViewModel() {
    val freeMusicesLiveData: MutableLiveData<TestAlbum>? by lazy {
        MutableLiveData<TestAlbum>()
    }

    fun requestFreeMusic() {
        viewModelScope.launch {
            HttpRequestManager.instance.getFreeMusic(freeMusicesLiveData)
        }
    }

}