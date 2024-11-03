package com.example.newfood.viewmodle

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.newfood.data.databaseroom.RecipesEntity
import com.example.newfood.data.repository.Repository
import com.example.newfood.models.FoodRecipe
import com.example.newfood.utils.CheckConnect
import com.example.newfood.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(

    private val repository: Repository ,
    @ApplicationContext private val context: Context

):ViewModel() {

    ////// data room
    val readRecipes : LiveData<List<RecipesEntity>> = repository.local.readDatabase().asLiveData()

    private fun insertRecipes(recipesEntity: RecipesEntity){
        viewModelScope.launch(Dispatchers.IO) {

            repository.local.insertRecipes(recipesEntity)

        }
    }



    //////ViewModel Recipes getData

    //save Data
    val recipeData : MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    // add data
    fun getRecipe (query: Map<String ,String>) = viewModelScope.launch{

        recipeData.value = NetworkResult.LodIng()

        if (CheckConnect(context).getConnect()){

                try {

                    val result = repository.remote.getRecipes(query)
                    recipeData.value = checkRecipes(result)

                    val foodRecipes = recipeData.value!!.data

                    if (foodRecipes != null) {

                        offlineCashRecipes(foodRecipes)

                    }

                } catch (e: Exception) {

                    recipeData.value = NetworkResult.Error("Recipe not found")

                }



        }else{

                recipeData.value = NetworkResult.Error("disConnect")

        }



    }

    private fun offlineCashRecipes(foodRecipes: FoodRecipe) {

        val recipesEntity = RecipesEntity(foodRecipes)
        insertRecipes(recipesEntity)

    }



    // check data
    private fun checkRecipes(result: Response<FoodRecipe>): NetworkResult<FoodRecipe>?{

        when{

            result.isSuccessful->{
                return NetworkResult.Success(result.body()!!)
            }


            result.code() == 402 ->{
                return  NetworkResult.Error("Api Key Limit")
            }


            result.body()!!.results.isNotEmpty()->{
                return NetworkResult.Error("Recipes  not found")
            }

            result.message().toString().contains("timeout")->{
                return NetworkResult.Error("timeout")
            }

            else ->{
                return NetworkResult.Error(result.message())
            }

        }

    }






}