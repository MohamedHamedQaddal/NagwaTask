package com.app.nagwa.nagwatask.modules.media.di

import androidx.lifecycle.ViewModel
import com.app.nagwa.nagwatask.common.di.viewmodel.ViewModelKey
import com.app.nagwa.nagwatask.modules.media.presentation.MediaViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MediaViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MediaViewModel::class)
    abstract fun bindMediaViewModel(viewModel: MediaViewModel): ViewModel
}