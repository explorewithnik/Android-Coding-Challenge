package com.niketchoudhary.`in`.androidcodingchallenge.utility

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment


class FragmentDataBindingComponent(var fragment: Fragment) : DataBindingComponent {

    override fun getFragmentDataBindingAdapter(): FragmentDataBindingAdapter {
        return FragmentDataBindingAdapter(fragment)
    }

}
