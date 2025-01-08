package com.nisum.randomusersapp.di

import com.nisum.randomusersapp.model.ApiServices
import com.nisum.randomusersapp.model.RetrofitClient
import com.nisum.randomusersapp.model.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    fun provideRetrofitClient(): RetrofitClient {
//        return RetrofitClient()
//    }

//    @Provides
//    fun provideApiServices(): ApiServices {
//        return RetrofitClient().apiServices
//
//    }
}

//    @Module
//    @InstallIn(SingletonComponent::class)
//    object NetworkModule {
//
//        @Provides
//        @Singleton
//        fun provideRetrofit(): Retrofit {
//            return Retrofit.Builder()
//                .baseUrl("https://randomuser.me/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//
//        @Provides
//        @Singleton
//        fun provideApiService(retrofit: Retrofit): ApiServices {
//            return retrofit.create(ApiServices::class.java)
//        }
//    }
//
//    @Module
//    @InstallIn(SingletonComponent::class)
//    object RepositoryModule {
//
//        @Provides
//        @Singleton
//        fun provideUserRepository(apiService: ApiServices): UserRepository {
//            return UserRepository(apiService)
//        }
//    }
