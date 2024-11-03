package com.example.newfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newfood.databinding.RecipesRowLayoutBinding
import com.example.newfood.models.FoodRecipe
import com.example.newfood.models.Result

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var recipes = emptyList<Result>()

    class MyViewHolder(private val binding: RecipesRowLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(result: Result){
            binding.result = result
            binding.executePendingBindings()
        }

        companion object{

            fun form (parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val  binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent ,false)
                return MyViewHolder(binding)
            }



        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapter.MyViewHolder{
        return MyViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: RecipesAdapter.MyViewHolder, position: Int) {
        val currentResult = recipes[position]
        holder.bind(currentResult)
    }

    override fun getItemCount(): Int {
        return  recipes.size
    }

    fun setData(newData :FoodRecipe){
        val recipeDiff = com.example.newfood.utils.DiffUtil(recipes,newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipeDiff)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}