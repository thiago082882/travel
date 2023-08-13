package com.example.travelguiaai.home.di

import com.example.travelguiaai.home.data.HomeRepositoryImpl
import com.example.travelguiaai.home.data.remote.ApiKeyInterceptor
import com.example.travelguiaai.home.data.remote.dto.ChatGptApi
import com.example.travelguiaai.home.domain.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HomeModule {
    @Provides
    @Singleton
    fun provideApi(): ChatGptApi {
        return Retrofit.Builder().baseUrl(ChatGptApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor())
                    .addInterceptor(ApiKeyInterceptor()).build()
            ).build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRepository(api: ChatGptApi): HomeRepository {
        return HomeRepositoryImpl(api)
    }
}
