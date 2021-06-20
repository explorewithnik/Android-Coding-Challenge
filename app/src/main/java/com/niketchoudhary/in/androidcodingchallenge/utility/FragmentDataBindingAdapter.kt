package com.niketchoudhary.`in`.androidcodingchallenge.utility

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import javax.inject.Inject

class FragmentDataBindingAdapter @Inject constructor(private val fragment: Fragment) {
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        fragment.activity?.applicationContext?.let { context ->
            Glide.with(context).load(url).into(imageView)
        }
    }

    @BindingAdapter("visibility")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}