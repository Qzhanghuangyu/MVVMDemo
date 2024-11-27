package com.zhang.mymvvm.ui.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhang.mymvvm.R
import com.zhang.mymvvm.bridge.state.PlayerViewModel
import com.zhang.mymvvm.bridge.state.SearchViewModel
import com.zhang.mymvvm.databinding.FragmentPlayerBinding
import com.zhang.mymvvm.databinding.FragmentSearchBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    private var searchBinding: FragmentSearchBinding? = null
    private var searchViewModel: SearchViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}