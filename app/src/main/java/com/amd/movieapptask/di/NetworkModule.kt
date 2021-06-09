package com.amd.movieapptask.di

import com.amd.movieapptask.BuildConfig
import com.amd.movieapptask.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun providesApiKey() : Interceptor = Interceptor { chain ->
        var request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.apiKey)
            .build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun providesHttpClient(
        interceptor: HttpLoggingInterceptor,
        apiKey: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(interceptor)
            addInterceptor(apiKey)
        }.build()
    }

    @Provides
    @Singleton
    fun providesHttpAdapter(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            client(client)
            baseUrl(BuildConfig.baseUrl)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun providesApiEndPoint(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

}