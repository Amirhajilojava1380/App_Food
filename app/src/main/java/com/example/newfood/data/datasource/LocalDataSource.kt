package com.example.newfood.data.datasource

import com.example.newfood.data.databaseroom.RecipesDao
import com.example.newfood.data.databaseroom.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource @Inject constructor(

    private val recipesDao: RecipesDao

){

     fun readDatabase() :Flow<List<RecipesEntity>>{
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity){
        recipesDao.insertRecipes(recipesEntity)
    }



}