package com.app.nagwa.nagwatask.common.di

import com.app.nagwa.nagwatask.modules.home.HomeActivity
import com.app.nagwa.nagwatask.modules.media.di.MediaFragmentProvider
import com.app.nagwa.nagwatask.modules.welcome.di.WelcomeScreenFragmentProviderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            WelcomeScreenFragmentProviderModule::class,
            MediaFragmentProvider::class,
        ]

    )
    abstract fun contributeUsersMainActivity(): HomeActivity

}