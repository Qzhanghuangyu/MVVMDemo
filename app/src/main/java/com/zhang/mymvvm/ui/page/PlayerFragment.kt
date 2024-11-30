package com.zhang.mymvvm.ui.page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kunminx.player.PlayingInfoManager
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.zhang.architecture.ui.materlibicon.MaterialDrawableBuilder
import com.zhang.mymvvm.R
import com.zhang.mymvvm.base.BaseFragment
import com.zhang.mymvvm.bridge.data.config.Configs
import com.zhang.mymvvm.bridge.player.PlayerManager
import com.zhang.mymvvm.bridge.state.MainViewModel
import com.zhang.mymvvm.bridge.state.PlayerViewModel
import com.zhang.mymvvm.databinding.FragmentMainBinding
import com.zhang.mymvvm.databinding.FragmentPlayerBinding
import com.zhang.mymvvm.ui.view.PlayerSlideListener
import com.sothree.slidinguppanel.PanelState
class PlayerFragment : BaseFragment() {
    private var playerBinding: FragmentPlayerBinding? = null
    private var playerViewModel: PlayerViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 VM
        playerViewModel =
            getFragmentViewModelProvider(this).get<PlayerViewModel>(PlayerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 加载界面
        val view: View = inflater.inflate(R.layout.fragment_player, container, false)
        playerBinding = FragmentPlayerBinding.bind(view)
        playerBinding?.click = ClickProxy() // 布局控制 点击事件的
       playerBinding ?.event = EventHandler() // 布局控制 拖动条的
        playerBinding?.vm = playerViewModel // ViewModel与布局关联
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSharedViewModel.timeToAddSlideListener.observe(viewLifecycleOwner, {
            if (view.parent.parent is SlidingUpPanelLayout) {
                val slidingUpPanelLayout = view.parent.parent as SlidingUpPanelLayout
                // 添加监听（可以弹上来的监听）
                slidingUpPanelLayout.addPanelSlideListener(
                    PlayerSlideListener(
                        playerBinding!!,
                        slidingUpPanelLayout
                    )
                )
            }

        })
        // 我是播放条，我要去变化，我成为观察者 ---> 播放相关的类 PlayerManager
        PlayerManager.instance.changeMusicResult.observe(viewLifecycleOwner, { changeMusic ->

            // 例如 ：理解 切歌的时候， 音乐的标题，作者，封面 状态等 改变
            playerViewModel!!.title.set(changeMusic.title)
            playerViewModel!!.artist.set(changeMusic.summary)
            playerViewModel!!.coverImg.set(changeMusic.img)
        })

        // 我是播放条，我要去变化，我成为观察者 -----> 播放相关的类PlayerManager
        PlayerManager.instance.playingMusicResult.observe(viewLifecycleOwner, { playingMusic ->

            // 例如 ：理解 切歌的时候，  播放进度的改变  按钮图标的改变
            playerViewModel!!.maxSeekDuration.set(playingMusic.duration) // 总时长
            playerViewModel!!.currentSeekPosition.set(playingMusic.playerPosition) // 拖动条
        })

        // 播放/暂停是一个控件  图标的true和false
        PlayerManager.instance.pauseResult.observe(viewLifecycleOwner, { aBoolean ->
            playerViewModel!!.isPlaying.set(!aBoolean!!) // 播放时显示暂停，暂停时显示播放
        })

        PlayerManager.instance.playModeResult.observe(viewLifecycleOwner, { anEnum ->
            val resultId: Int
            resultId = if (anEnum === PlayingInfoManager.RepeatMode.LIST_CYCLE) {
                playerViewModel!!.playModeIcon.set(MaterialDrawableBuilder.IconValue.REPEAT)
                R.string.play_repeat // 列表循环
            } else if (anEnum === PlayingInfoManager.RepeatMode.SINGLE_CYCLE) { // 单曲循环
                playerViewModel!!.playModeIcon.set(MaterialDrawableBuilder.IconValue.REPEAT_ONCE)
                R.string.play_repeat_once // 单曲循环
            } else { // 随机循环
                playerViewModel!!.playModeIcon.set(MaterialDrawableBuilder.IconValue.SHUFFLE)
                R.string.play_shuffle // 随机循环
            }

            // 提示改变
            if (view.parent.parent is SlidingUpPanelLayout) {
                val sliding = view.parent.parent as SlidingUpPanelLayout

                if (sliding.getPanelState() == PanelState.EXPANDED) { // 张开状态
                    // 这里一定会弹出：“列表循环” or “单曲循环” or “随机播放”
                    showShortToast(resultId)
                }
            }
        })

        mSharedViewModel.closeSlidePanelIfExpanded.observe(viewLifecycleOwner, {
            if (view.parent.parent is SlidingUpPanelLayout) {
                val slidingUpPanelLayout = view.parent.parent as SlidingUpPanelLayout
                if (slidingUpPanelLayout.getPanelState() == PanelState.EXPANDED) {

                    slidingUpPanelLayout.setPanelState( PanelState.COLLAPSED)

                    mSharedViewModel.activityCanBeClosedDirectly.value = true

                } else {
                    mSharedViewModel.activityCanBeClosedDirectly.value = false
                }


            } else {
                mSharedViewModel.activityCanBeClosedDirectly.value = false
            }


        })
    }

    inner class ClickProxy {


        fun previous() = PlayerManager.instance.playPrevious() // 上一首

        operator fun next() = PlayerManager.instance.playNext() // 下一首

        // 左手边的滑落，点击缩小的，可以控制 播放详情 点击/back 掉下来
        fun slideDown() = mSharedViewModel.closeSlidePanelIfExpanded.setValue(true)

        //　更多的
        fun more() = Toast.makeText(mActivity, "你能不能不要乱点", Toast.LENGTH_SHORT).show()

        fun togglePlay() = PlayerManager.instance.togglePlay()

        fun playMode() = PlayerManager.instance.changeMode() // 播放

        fun showPlayList() = showShortToast("最近播放的细节，我没有搞...")

    }

    inner class EventHandler : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) =
            PlayerManager.instance.setSeek(seekBar!!.progress)

    }

}