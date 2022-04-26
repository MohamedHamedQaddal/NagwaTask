package com.app.nagwa.nagwatask.modules.welcome.di

import com.app.nagwa.nagwatask.modules.welcome.presentation.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class WelcomeScreenFragmentProviderModule {

    @ContributesAndroidInjector()
    abstract fun provideWelcomeScreenFragment(): WelcomeFragment
}