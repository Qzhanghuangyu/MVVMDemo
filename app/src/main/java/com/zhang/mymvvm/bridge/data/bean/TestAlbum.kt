package com.zhang.mymvvm.bridge.data.bean

import com.kunminx.player.bean.base.BaseAlbumItem
import com.kunminx.player.bean.base.BaseArtistItem
import com.kunminx.player.bean.base.BaseMusicItem
/**
 * 歌曲 专辑 歌手  本身的实体Bean 对象
 * 被 PlayerManager 使用
 * 被 PlayerService 使用
 * 被 IRemoteRequest接口 使用了
 * 被 IRemoteRequest接口 使用了
 */
class TestAlbum : BaseAlbumItem<TestAlbum.TestMusic, TestAlbum.TestArtist>() {
    private var albumMid: String? = null

    fun getAlbumMid(): String? {
        return albumMid
    }

    fun setAlbumMid(albumMid: String?) {
        this.albumMid = albumMid
    }


    class TestMusic : BaseMusicItem<TestArtist>() {
        var songMid: String? = null
    }


    class TestArtist : BaseArtistItem() {
        var birthday: String? = null
    }
}