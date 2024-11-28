package com.zhang.mymvvm.bridge.state

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    @JvmField
    val initTabAndPage = ObservableBoolean()

    @JvmField
    val pageAssetPath = ObservableField<String>()
}