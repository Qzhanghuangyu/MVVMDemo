package com.zhang.mymvvm.ui.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zhang.mymvvm.bridge.state.MainViewModel
import com.zhang.mymvvm.bridge.state.PlayerViewModel
import com.zhang.mymvvm.databinding.FragmentMainBinding
import com.zhang.mymvvm.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {
    private var playerBinding: FragmentPlayerBinding? = null
    private var playerViewModel: PlayerViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}