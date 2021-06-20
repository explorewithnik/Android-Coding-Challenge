package com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection

import android.app.Application
import com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.module.ActivityModule
import com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.module.AppModule
import com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.module.FragmentModule
import com.niketchoudhary.`in`.androidcodingchallenge.utility.AppController
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class, FragmentModule::class])
interface AppComponent : AndroidInjector<AppController> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(instance: AppController?)

}