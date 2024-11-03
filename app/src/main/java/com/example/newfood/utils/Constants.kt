package com.example.newfood.utils

class Constants {

    companion object{

        const val BASE_URL = "https://api.spoonacular.com/recipes/"
        const val API_GET_RECIPES = "complexSearch"

        const val API_KEY = "6fc776d176ac48a0ba582a7528a5b1f2"
        const val NAME_API_KEY = "apiKey"

        const val NUMBER = "30"                 //value
        const val NAME_NUMBER = "number"        //key

        const val VEGAN = "Vegan"                //value
        const val NAME_VEGAN = "diet"            //key

        const val INFORMATION = "true"
        const val NAME_INFORMATION = "addRecipeInformation"

        const val FILL = "true"
        const val NAME_FILL = "fillIngredients"


        //room database
        const val NAME_TABLE = "recipes_table"
        const val NAME_DATABASE = "recipes_database"


        //bottom sheet and Preferences

        const val DEFAULT_MEAL_TYPE = "main course"
        const val NAME_TYPE = "type"

        const val DEFAULT_DIET_TYPE = "gluten free"
        const val NAME_DIET = "diet"



        const val PREFERENCES_DIET = "dietType"
        const val PREFERENCES_DIET_ID = "dietTypeID"

        const val PREFERENCES_MEAL = "mealType"
        const val PREFERENCES_MEAL_ID = "mealTypeID"

        const val PREFERENCES_NAME = "Food"




    }

}