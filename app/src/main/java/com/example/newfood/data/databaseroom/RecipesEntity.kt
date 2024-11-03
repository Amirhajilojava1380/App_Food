package com.example.newfood.data.databaseroom

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newfood.models.FoodRecipe
import com.example.newfood.utils.Constants.Companion.NAME_TABLE

@Entity(tableName =  NAME_TABLE)
class RecipesEntity (var foodRecipe: FoodRecipe) {

    @PrimaryKey(autoGenerate = false)
    var id :Int = 0


}