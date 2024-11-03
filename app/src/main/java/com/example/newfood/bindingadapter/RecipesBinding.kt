package com.example.newfood.bindingadapter

import android.view.View
import android.widget.RelativeLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newfood.data.databaseroom.RecipesEntity
import com.example.newfood.models.FoodRecipe
import com.example.newfood.utils.NetworkResult

class RecipesBinding {


    companion object{


            @BindingAdapter("api","database",requireAll = true)
            @JvmStatic
            fun errorLottyVisibility(
                view :RelativeLayout,
                api:MutableLiveData<NetworkResult<FoodRecipe>>,
                database:LiveData<List<RecipesEntity>>?
            ){
                if (api.value == null && database == null) {

                    view.visibility = View.VISIBLE

                }else{

                    when(api.value){

                        is NetworkResult.Success -> {
                            view.visibility = View.GONE
                        }

                        is NetworkResult.Error -> {
                            view.visibility = View.VISIBLE
                        }

                        is NetworkResult.LodIng -> {
                            view.visibility = View.GONE
                        }

                        else -> {
                            view.visibility = View.GONE
                        }
                        
                    }

                }




            }


    }


}


