package com.example.tryhilt.di

import com.example.tryhilt.repository.TaskRepository
import com.example.tryhilt.repository.TaskRepositoryImpl
import com.example.tryhilt.service.ApiService
import com.example.tryhilt.usecase.GetTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module

object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://cashaxa.com/api/") // Replace
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideTaskRepository(apiService: ApiService): TaskRepository =
        TaskRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideGetTasksUseCase(repository: TaskRepository): GetTasksUseCase =
        GetTasksUseCase(repository)
}
