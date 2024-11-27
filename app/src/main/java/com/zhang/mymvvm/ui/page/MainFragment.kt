package com.zhang.mymvvm.ui.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zhang.mymvvm.bridge.state.MainViewModel
import com.zhang.mymvvm.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var fragmentMainBinding: FragmentMainBinding? = null
    private var mainViewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
