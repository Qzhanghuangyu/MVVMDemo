package com.zhang.mymvvm.bridge.player

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kunminx.player.PlayerController
import com.kunminx.player.PlayingInfoManager
import com.kunminx.player.bean.dto.ChangeMusic
import com.kunminx.player.bean.dto.PlayingMusic
import com.kunminx.player.contract.ICacheProxy
import com.kunminx.player.contract.IPlayController
import com.kunminx.player.contract.IServiceNotifier
import com.zhang.mymvvm.bridge.data.bean.TestAlbum
import com.zhang.mymvvm.bridge.data.bean.TestAlbum.TestMusic
import com.zhang.mymvvm.bridge.data.bean.TestAlbum.TestArtist
import com.zhang.mymvvm.bridge.player.notification.PlayerService

/**
 * 播放的管理器，由此类去调用，播放库来播放 等操作
 *
 * PlayerManager  ---> 播放库 播放 暂停 等等
 */
class PlayerManager private constructor() : IPlayController<TestAlbum, TestMusic, TestArtist> {

    val mController: PlayerController<TestAlbum, TestMusic, TestArtist>

    init {

        mController = PlayerController()
    }

    private var mContext: Context? = null

   fun  init(context: Context?){
       init(context,null,null)
   }

    override fun init(context: Context?, p1: IServiceNotifier?, p2: ICacheProxy?) {
        mContext = context?.applicationContext
//        mController.init(mContext,null,null)
//        val intent = Intent(mContext, PlayerService::class.java)
//
//        if (mController.isPlaying) {
//            mContext ?.startService(intent) // 后台播放
//        } else {
//            mContext ?.stopService(intent) // 停止后台播放
//        }

    }


    override fun loadAlbum(musicAlbum: TestAlbum) {
        mController.loadAlbum(musicAlbum)
    }

    override fun loadAlbum(musicAlbum: TestAlbum, playIndex: Int) {
        mController.loadAlbum(musicAlbum, playIndex)
    }

    override fun playAudio() {
        mController.playAudio()
    }

    // 下标的 播放
    override fun playAudio(albumIndex: Int) {
        // 在这里只需要知道，调用此 playAudio函数，就能够播放音乐了
        mController.playAudio(albumIndex)
    }

    // 下一首播放
    override fun playNext() {
        mController.playNext()
    }

    // 上一首播放
    override fun playPrevious() {
        mController.playPrevious()
    }

    override fun playAgain() {
        mController.playAgain()
    }

    // 暂停 播放
    override fun pauseAudio() {
        mController.pauseAudio()
    }

    override fun resumeAudio() {
        mController.resumeAudio()
    }

    // 清除歌曲下标 的 标记
    override fun clear() {
        mController.clear()
    }

    override fun changeMode() {
        mController.changeMode()
    }

    override fun isPlaying(): Boolean {
        return mController.isPlaying
    }

    override fun isPaused(): Boolean {
        return mController.isPaused
    }

    override fun isInit(): Boolean {
        return mController.isInit
    }


    override fun requestLastPlayingInfo() {
        mController.requestLastPlayingInfo()
    }


    override fun setSeek(progress: Int) {
        mController.setSeek(progress)
    }

    override fun getTrackTime(progress: Int): String {
        return mController.getTrackTime(progress)
    }

    override fun getChangeMusicResult(): LiveData<ChangeMusic<TestAlbum, TestMusic, TestArtist>> {
        return mController.changeMusicResult
    }


    override fun getPlayingMusicResult(): LiveData<PlayingMusic<TestAlbum, TestAlbum.TestMusic, TestAlbum.TestArtist>> {


        return mController.playingMusicResult
    }

    override fun getPauseResult(): LiveData<Boolean> {
        return mController.pauseResult
    }

    override fun getPlayModeResult(): LiveData<Enum<PlayingInfoManager.RepeatMode>> {
        return mController.playModeResult
    }

    override fun getAlbum(): TestAlbum? {
        return mController.album
    }

    override fun getAlbumMusics(): List<TestMusic?> {
        return mController.albumMusics
    }

    override fun setChangingPlayingMusic(changingPlayingMusic: Boolean) {
        mController.setChangingPlayingMusic(changingPlayingMusic)
    }

    override fun getAlbumIndex(): Int {
        return mController.getAlbumIndex()
    }


    override fun getRepeatMode(): Enum<PlayingInfoManager.RepeatMode>? {
        return mController.getRepeatMode()
    }

    override fun togglePlay() {
        mController.togglePlay()
    }

    override fun getCurrentPlayingMusic(): TestMusic {
        return mController.getCurrentPlayingMusic()
    }

    companion object {
        // 单例模式
        @SuppressLint("StaticFieldLeak")
        val instance = PlayerManager() // 单例相关的
    }


}