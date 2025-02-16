package com.example.newfood.data.databaseroom

import androidx.room.TypeConverter
import com.example.newfood.models.FoodRecipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeTypeConverter {

    private var gson = Gson()

    @TypeConverter
    fun foodRecipesToString(foodRecipe: FoodRecipe): String? {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipes(data :String):FoodRecipe{
        val listType = object : TypeToken<FoodRecipe>(){}.type
        return gson.fromJson(data , listType)
    }


}