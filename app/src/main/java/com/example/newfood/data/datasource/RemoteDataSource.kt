package com.example.newfood.data.datasource

import com.example.newfood.data.api.FoodApi
import com.example.newfood.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(

    private val foodApi: FoodApi

) {

    suspend fun getRecipes(query:Map<String,String>):Response<FoodRecipe>{
        return foodApi.getRecipes(query)
    }


}