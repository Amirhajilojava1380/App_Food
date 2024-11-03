package com.example.newfood.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.newfood.R
import com.example.newfood.utils.Constants
import com.example.newfood.viewmodle.QueryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class RecipesBottomSheet : BottomSheetDialogFragment() {

    private val  queryViewModel: QueryViewModel by viewModels()

    private var mealType = Constants.DEFAULT_MEAL_TYPE
    private var mealTypeId = 0
    private var dietType = Constants.DEFAULT_DIET_TYPE
    private var dietTypeId = 0

    private lateinit var mealChipGroup : ChipGroup
    private lateinit var dietChipGroup : ChipGroup

    private lateinit var apply :Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipes_bottom_sheet, container, false)



        mealChipGroup = view.findViewById(R.id.chipgroup_meal)
        dietChipGroup = view.findViewById(R.id.chipgroup_Diet)

        apply = view.findViewById(R.id.button)

        queryViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner){

            mealType = it.selectMeal
            dietType = it.selectDiet

            updateChip(it.selectDietID, dietChipGroup)
            updateChip(it.selectMealID , mealChipGroup)

        }

        mealChipGroup.setOnCheckedStateChangeListener   { group , checkedIds ->

            val selectChipId = checkedIds.firstOrNull()
            val  chip = group.findViewById<Chip>(selectChipId!!)
            val  selectMeal = chip?.text.toString().lowercase(Locale.ROOT)
            mealType = selectMeal
            mealTypeId = selectChipId

        }

        dietChipGroup.setOnCheckedStateChangeListener { group , checkedIds ->

            val selectChipId = checkedIds.firstOrNull()
            val  chip = group.findViewById<Chip>(selectChipId!!)
            val  selectDiet = chip?.text.toString().lowercase(Locale.ROOT)
            dietType = selectDiet
            dietTypeId = selectChipId


        }

        apply.setOnClickListener {

            queryViewModel.saveMealAndDiet(mealType , mealTypeId , dietType , dietTypeId)

            findNavController().navigate(RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(true))

        }

        return view
    }

    private fun updateChip(selectMealID: Int, mealChipGroup: ChipGroup){

        if (selectMealID != 0){

            try {

                mealChipGroup.findViewById<Chip>(selectMealID).isChecked =true

            }catch (e:Exception){
                Log.d("recipesFragment" , "${e.message}")
            }

        }


    }


}