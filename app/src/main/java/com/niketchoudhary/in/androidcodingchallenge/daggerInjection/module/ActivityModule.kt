package com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.module

import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.owner.activity.ShaadiUserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeShaadiUserActivity(): ShaadiUserActivity
}