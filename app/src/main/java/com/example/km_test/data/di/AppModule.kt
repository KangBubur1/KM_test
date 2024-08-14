package com.example.km_test.data.di

import com.example.km_test.data.dto.ReqresApi
import com.example.km_test.domain.repository.UserRepository
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

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideReqroApi(retrofit: Retrofit): ReqresApi {
        return retrofit.create(ReqresApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReqresRepository(reqresApi: ReqresApi): UserRepository {
        return UserRepository(reqresApi)
    }

}