package com.zhang.mymvvm.bridge.state

import androidx.lifecycle.ViewModel
import com.zhang.architecture.bridge.callback.UnPeekLiveData

class ItemViewModel : ViewModel() {

    val title = UnPeekLiveData<String>()
    val coverImg = UnPeekLiveData<String>()
    val summary = UnPeekLiveData<String>()


 init {
     title.value=""
     coverImg.value=""
     summary.value=""
 }
}