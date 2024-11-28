package com.zhang.mymvvm.ui.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhang.mymvvm.R
import com.zhang.mymvvm.base.BaseFragment
import com.zhang.mymvvm.bridge.data.bean.TestAlbum
import com.zhang.mymvvm.bridge.state.MainViewModel
import com.zhang.mymvvm.bridge.state.MusicRequestViewModel
import com.zhang.mymvvm.databinding.AdapterPlayItemBinding
import com.zhang.mymvvm.databinding.FragmentMainBinding
import com.zhang.mymvvm.ui.adapter.SimpleBaseBindingAdapter

class MainFragment : BaseFragment() {
    private var fragmentMainBinding: FragmentMainBinding? = null
    private var mainViewModel: MainViewModel? = null

    private var musicRequestViewModel: MusicRequestViewModel? = null

    private var adapter: SimpleBaseBindingAdapter<TestAlbum.TestMusic?, AdapterPlayItemBinding?>? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = getFragmentViewModelProvider(this).get(MainViewModel::class.java)
        musicRequestViewModel =
            getFragmentViewModelProvider(this).get(MusicRequestViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        fragmentMainBinding = FragmentMainBinding.bind(view)
        fragmentMainBinding?.click = ClickProxy()
        fragmentMainBinding?.vm = mainViewModel
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mainViewModel!!.initTabAndPage.set(true)
        mainViewModel!!.pageAssetPath.set("JetPack之 WorkManager.html")

        adapter = object : SimpleBaseBindingAdapter<TestAlbum.TestMusic?, AdapterPlayItemBinding?>(
            context,
            R.layout.adapter_play_item
        ) {
            override fun onSimpleBindItem(
                binding: AdapterPlayItemBinding?,
                item: TestAlbum.TestMusic?,
                holder: RecyclerView.ViewHolder?
            ) {
                binding?.tvTitle?.text = item?.title
                binding?.tvArtist?.text = item?.artist?.name
                Glide.with(binding?.ivCover!!.context).load(item?.coverImg)
                    .into(binding.ivCover) // 左右边的图片
                // 歌曲下标记录
                // val currentIndex = PlayerManager.instance.albumIndex // 歌曲下标记录

                // 播放的标记
                /*binding.ivPlayStatus.setColor(
                    if (currentIndex == holder ?.adapterPosition) resources.getColor(R.color.colorAccent) else Color.TRANSPARENT
                ) // 播放的时候，右变状态图标就是红色， 如果对不上的时候，就是没有*/

                binding.rootView.setOnClickListener {
                    Toast.makeText(context, "播放音乐", Toast.LENGTH_LONG).show()

                }

            }

        }

        fragmentMainBinding!!.rv.adapter = adapter


        musicRequestViewModel!!.freeMusicesLiveData!!.observe(viewLifecycleOwner,
            { musicAlbum: TestAlbum? ->
                if (musicAlbum != null && musicAlbum.musics != null) {
                    adapter?.list = musicAlbum.musics
                    adapter?.notifyDataSetChanged()
                }

            })
        musicRequestViewModel!!.requestFreeMusic()
    }

    inner class ClickProxy {
        fun openMenu() {
            mSharedViewModel.openOrCloseDrawer.value = true
        }

        fun search() {
            nav().navigate(R.id.action_mainFragment_to_searchFragment)
        }

    }
}
