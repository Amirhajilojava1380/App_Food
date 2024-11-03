package com.example.newfood.data.databaseroom

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [RecipesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipeTypeConverter::class)
abstract class RecipesDataBase :RoomDatabase() {

    abstract fun recipesDao():RecipesDao


}