package com.app.nagwa.nagwatask.modules.media.di

import com.app.nagwa.nagwatask.modules.media.data.repository.MediaRepositoryImpl
import com.app.nagwa.nagwatask.modules.media.domain.repository.MediaRepository
import dagger.Binds
import dagger.Module

@Module
abstract class MediaModule {

    @Binds
    @MediaScope
    abstract fun bindMediaRepository(mediaRepositoryImpl: MediaRepositoryImpl): MediaRepository

}