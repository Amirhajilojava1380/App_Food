package com.example.newfood.di

import android.content.Context
import androidx.room.Room
import com.example.newfood.data.databaseroom.RecipesDataBase
import com.example.newfood.utils.Constants.Companion.NAME_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreLocalModule {

    @Provides
    @Singleton
    fun provideDataBase(

        @ApplicationContext context: Context

    ) = Room.databaseBuilder(

        context,
        RecipesDataBase::class.java,
        NAME_DATABASE

    ).build()



    @Provides
    @Singleton
    fun provideDao(dataBase: RecipesDataBase) = dataBase.recipesDao()



}