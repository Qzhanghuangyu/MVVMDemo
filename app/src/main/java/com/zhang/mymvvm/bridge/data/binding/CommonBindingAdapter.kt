package com.zhang.mymvvm.bridge.data.binding

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.zhang.architecture.utils.ClickUtils

@SuppressWarnings("unused")
object CommonBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
    fun loadUrl(view: ImageView, url: String?, placeHolder: Drawable?) {
        Glide.with(view.context).load(url).placeholder(placeHolder).into(view)

    }
    @JvmStatic
    @BindingAdapter(value = ["loadImageUrl"], requireAll = false)
    fun loadImageUrl(view: AppCompatImageView, imageUrl: String?) {
        Glide.with(view.context).load(imageUrl).into(view)

    }

    @JvmStatic
    @BindingAdapter(value = ["onClickWithDebouncing"], requireAll = false)
    fun onClickWithDebouncing(view: View?, clickListener: View.OnClickListener?) {
        ClickUtils.applySingleDebouncing(view, clickListener)
    }

}