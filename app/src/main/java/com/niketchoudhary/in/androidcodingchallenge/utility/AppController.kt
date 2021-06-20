package com.niketchoudhary.`in`.androidcodingchallenge.utility

import android.app.Activity
import android.os.Bundle
import com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.DaggerAppComponent
import com.niketchoudhary.`in`.androidcodingchallenge.database.PrefManager
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AppController : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        PrefManager.getInstance(this)
        instance = this

        appInitialization()
        registerActivityLifecycleCallbacks(activityCallbacks)
    }


    @Synchronized
    fun getInstance(): AppController? {
        return mInstance
    }

    private val applicationInjector = DaggerAppComponent.builder().application(this).build()
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector

    private fun appInitialization() {
        defaultUEH = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(_unCaughtExceptionHandler)
    }


    fun handleUncaughtException(thread: Thread?, e: Throwable) {
        e.printStackTrace() // not all Android versions will print the stack trace automatically
    }

    private var defaultUEH: Thread.UncaughtExceptionHandler? = null

    // handler listener
    private val _unCaughtExceptionHandler = Thread.UncaughtExceptionHandler { thread, ex ->
        try {
            handleUncaughtException(thread, ex)
        } finally {
            defaultUEH!!.uncaughtException(thread, ex)
        }
    }

    var activityCallbacks: ActivityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
        override fun onActivityStarted(activity: Activity) {}
        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {}
    }

    companion object {
        const val TAG = "Shadi"
        val mInstance: AppController? = null

        @get:Synchronized
        lateinit var instance: AppController

    }

}
