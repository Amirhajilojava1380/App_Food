package com.example.newfood.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.newfood.models.MealAndDietType
import com.example.newfood.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys{

        val selectMealType = stringPreferencesKey(Constants.PREFERENCES_MEAL)
        val selectMealTypeID = intPreferencesKey(Constants.PREFERENCES_MEAL_ID)

        val selectDietType = stringPreferencesKey(Constants.PREFERENCES_DIET)
        val selectDietTypeID = intPreferencesKey(Constants.PREFERENCES_DIET_ID)

    }

    private val Context.dataStore by preferencesDataStore(name = Constants.PREFERENCES_NAME)

    suspend fun saveMealAndDiet(meal:String, mealId: Int, diet:String, dietId:Int){

        context.dataStore.edit {

            it[PreferenceKeys.selectMealType] = meal
            it[PreferenceKeys.selectMealTypeID] = mealId
            it[PreferenceKeys.selectDietType] = diet
            it[PreferenceKeys.selectDietTypeID] = dietId

        }


    }


    val readMealAndDiet : Flow<MealAndDietType> = context.dataStore.data.catch {

        if (it is IOException){
            emit(emptyPreferences())
        }else{
            throw  it
        }
    }
        .map {

            val selectMeal = it[PreferenceKeys.selectMealType] ?: Constants.DEFAULT_MEAL_TYPE
            val selectMealID = it[PreferenceKeys.selectMealTypeID] ?: 0

            val selectDiet = it[PreferenceKeys.selectDietType] ?: Constants.DEFAULT_DIET_TYPE
            val selectDietID = it[PreferenceKeys.selectDietTypeID] ?: 0

            MealAndDietType(selectMeal , selectMealID , selectDiet , selectDietID)
        }
}