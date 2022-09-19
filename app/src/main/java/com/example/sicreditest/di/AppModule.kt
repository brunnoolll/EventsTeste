package com.example.sicreditest.di

import com.example.sicreditest.commom.Constant
import com.example.sicreditest.data.remote.EventService
import com.example.sicreditest.data.repository.EventRepositoryImpl
import com.example.sicreditest.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerEventApi(): EventService {
        return Retrofit.Builder()
            .client(okkHttpclient)
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventService::class.java)
    }

    @Provides
    @Singleton
    fun provideEventRepository(api : EventService) : EventRepository {
        return EventRepositoryImpl(api)
    }



    val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val okkHttpclient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()!!
}