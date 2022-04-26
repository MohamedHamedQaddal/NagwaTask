package com.app.nagwa.nagwatask.modules.media.di

import com.app.nagwa.nagwatask.modules.media.presentation.MediaFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MediaFragmentProvider {

    @ContributesAndroidInjector(
        modules = [
            MediaModule::class,
            MediaViewModelModule::class
        ]
    )
    @MediaScope
    abstract fun provideMediaFragment(): MediaFragment

}