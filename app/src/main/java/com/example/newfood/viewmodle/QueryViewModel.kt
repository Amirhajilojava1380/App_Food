package com.example.newfood.viewmodle

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfood.data.repository.DataStoreRepository
import com.example.newfood.utils.Constants.Companion.API_KEY
import com.example.newfood.utils.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.newfood.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.newfood.utils.Constants.Companion.FILL
import com.example.newfood.utils.Constants.Companion.INFORMATION
import com.example.newfood.utils.Constants.Companion.NAME_API_KEY
import com.example.newfood.utils.Constants.Companion.NAME_DIET
import com.example.newfood.utils.Constants.Companion.NAME_FILL
import com.example.newfood.utils.Constants.Companion.NAME_INFORMATION
import com.example.newfood.utils.Constants.Companion.NAME_NUMBER
import com.example.newfood.utils.Constants.Companion.NAME_TYPE
import com.example.newfood.utils.Constants.Companion.NAME_VEGAN
import com.example.newfood.utils.Constants.Companion.NUMBER
import com.example.newfood.utils.Constants.Companion.VEGAN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QueryViewModel @Inject constructor(

    private val dataStoreRepository: DataStoreRepository

):ViewModel(){

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    val readMealAndDietType = dataStoreRepository.readMealAndDiet

    fun saveMealAndDiet(meal:String, mealId: Int, diet:String, dietId:Int)
    = viewModelScope.launch(Dispatchers.IO) {

        dataStoreRepository.saveMealAndDiet(meal, mealId, diet, dietId)

    }



    fun queryRecipes():HashMap<String,String>{

         val map = HashMap<String,String>()

         viewModelScope.launch {

             readMealAndDietType.collect{

                 mealType = it.selectMeal
                 dietType = it.selectDiet

                 Log.d("recipesFragment" , "collect:$mealType")
                 Log.d("recipesFragment" , "collect:$dietType")
             }



         }



        map[NAME_API_KEY] = API_KEY
        map[NAME_NUMBER] = NUMBER

        map[NAME_TYPE] = mealType
        map[NAME_DIET] = dietType
        map[NAME_FILL] = FILL
        map[NAME_INFORMATION] = INFORMATION

        Log.d("recipesFragment", mealType)
        Log.d("recipesFragment", dietType)

     return  map
    }


}