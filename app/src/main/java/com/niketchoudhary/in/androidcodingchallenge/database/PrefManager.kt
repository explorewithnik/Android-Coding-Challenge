package com.niketchoudhary.`in`.androidcodingchallenge.database

import android.content.Context

class PrefManager private constructor(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, 0)


    companion object {
        const val PREF_NAME = "prefManager"

        @Volatile
        var manager: PrefManager? = null

        @Synchronized
        fun getInstance(context: Context): PrefManager {
            if (manager == null)
                manager = PrefManager(context)
            return manager!!
        }
    }
}