package com.example.newfood.di

import com.example.newfood.data.api.FoodApi
import com.example.newfood.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreNetWorkModule {


    @Provides
    @Singleton
    fun providesGsonFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesOkHttps():OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15 , TimeUnit.SECONDS)
            .connectTimeout(15 ,TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }




    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit):FoodApi{
        return retrofit.create(FoodApi::class.java)
    }



}