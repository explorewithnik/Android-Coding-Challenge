package com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.ViewModelKey
import com.niketchoudhary.`in`.androidcodingchallenge.daggerInjection.factory.AppModelFactory
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.observer.ShaadiUserDataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShaadiUserDataViewModel::class)
    abstract fun bindShaadiUserDataViewModel(storageViewModel: ShaadiUserDataViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppModelFactory): ViewModelProvider.Factory

}