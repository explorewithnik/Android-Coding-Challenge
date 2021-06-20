package com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.module

import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.owner.fragments.ShaadiUserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeShaadiUserListFragment(): ShaadiUserListFragment
}