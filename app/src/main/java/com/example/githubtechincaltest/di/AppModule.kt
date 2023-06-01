package com.example.githubtechincaltest.di

import com.example.githubtechincaltest.data.GithubApi
import com.example.githubtechincaltest.data.domain.GithubRepository
import com.example.githubtechincaltest.data.domain.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGithubApi(): GithubApi {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Add the logging interceptor to the OkHttp client
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(GithubApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGithubRepository(githubApi: GithubApi): GithubRepository {
        return GithubRepositoryImpl(githubApi)
    }
}