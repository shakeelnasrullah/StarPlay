package com.pak.starplayz.base

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pak.lib_module.BuildConfig
import com.pak.lib_module.models.Media
import com.pak.starplayz.R

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Fragment.showToast(msg : String) {
    this.context?.let {
        Toast.makeText(it, msg, Toast.LENGTH_SHORT).show()
    }
}

fun ImageView.loadImageFromUrl(media: Media) {
    this.context?.let {
        if (media.poster_path?.isNotEmpty() == true) {
            Glide.with(it).load(BuildConfig.IMAGE_BASE_URL + media.poster_path)
                .placeholder(R.drawable.ic_default_image)
                .error(R.drawable.ic_default_image)
                .into(this)
        } else {
            Glide.with(it).load(BuildConfig.IMAGE_BASE_URL + media.profile_path)
                .placeholder(R.drawable.ic_default_image)
                .error(R.drawable.ic_default_image)
                .into(this)
        }
    }
}