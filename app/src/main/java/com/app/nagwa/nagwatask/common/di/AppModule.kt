package com.app.nagwa.nagwatask.common.di

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
abstract class AppModule {

    companion object {


        @Singleton
        @Provides
        fun provideInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        @Singleton
        @Provides
        fun provideMoshi() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi) = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .client(okHttpClient)
            .build()
    }
}