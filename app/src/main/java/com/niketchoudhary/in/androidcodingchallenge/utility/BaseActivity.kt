package com.niketchoudhary.`in`.androidcodingchallenge.utility

import android.os.Bundle
import com.niketchoudhary.`in`.androidcodingchallenge.database.PrefManager
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefManager.getInstance(this)
    }
}
