package com.app.nagwa.nagwatask.common.di

import android.app.Application
import com.app.nagwa.nagwatask.common.application.BaseApplication
import com.app.nagwa.nagwatask.common.di.viewmodel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    AndroidInjectionModule::class,
    ActivityBuildersModule::class,
    ViewModelFactoryModule::class,])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplication(application: Application): Builder

        fun build(): AppComponent
    }
}