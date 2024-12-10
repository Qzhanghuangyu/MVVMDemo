package com.zhang.mymvvm.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.zhang.mymvvm.R
import com.zhang.mymvvm.databinding.AdapterPlayItemBinding

class TitleListView(context: Context) : LinearLayout(context) {
/*    var mBinding: AdapterPlayItemBinding? = null

    init {

        val inflater =
            getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_play_item, this, false)
        mBinding?.getRoot()?.setOnClickListener(OnClickListener {
            *//*  WebviewActivity.startCommonWeb(
                  getContext(),
                  "News",
                  mViewModel.jumpUri
              )*//*
        })
        addView(mBinding?.getRoot())

    }

    override fun setData(data: ItemViewModel?) {
        mBinding?.item = data
        mBinding?.executePendingBindings()


    }*/

}