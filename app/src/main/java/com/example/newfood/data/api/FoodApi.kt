package com.example.newfood.data.api

import com.example.newfood.models.FoodRecipe
import com.example.newfood.utils.Constants.Companion.API_GET_RECIPES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodApi {


    @GET(API_GET_RECIPES)
    suspend fun getRecipes(@QueryMap query :Map<String ,String>):Response<FoodRecipe>



}