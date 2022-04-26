package com.app.nagwa.nagwatask.common.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun viewModelFactory(factory: DaggerViewModelProviderFactory): ViewModelProvider.Factory
}