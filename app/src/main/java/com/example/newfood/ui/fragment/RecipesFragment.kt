package com.example.newfood.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfood.R
import com.example.newfood.adapter.RecipesAdapter
import com.example.newfood.databinding.FragmentRecipesBinding
import com.example.newfood.utils.NetworkResult
import com.example.newfood.utils.observeOnce
import com.example.newfood.viewmodle.QueryViewModel
import com.example.newfood.viewmodle.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecipesFragment : Fragment() {


    private val args by navArgs<RecipesFragmentArgs>()
    private var _binding :FragmentRecipesBinding? = null
    private val binding  get() = _binding!!

    private val recipesViewModel :RecipesViewModel by viewModels()
    private val queryViewModel   :QueryViewModel   by viewModels()

    private var hasErrorOccurred = false

    private val myAdapter by lazy { RecipesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = recipesViewModel

        binding.floatingActionButton.setOnClickListener {

            findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheet)

        }

        setUpRecyclerView()
        readDatabase()


        return binding.root
    }


    private fun readDatabase(){
        lifecycleScope.launch {

            recipesViewModel.readRecipes.observeOnce(viewLifecycleOwner){

                if (it.isNotEmpty()&& !args.bottomSheet){

                    Log.e("recipesFragment" , "readDataBase Offline")
                    myAdapter.setData(it[0].foodRecipe)
                    hideShimmer()

                }else{

                    requestApiData()

                }

            }


        }

    }

    private fun localDataCash(){

        lifecycleScope.launch {

            recipesViewModel.readRecipes.observe(viewLifecycleOwner){

                if (it.isNotEmpty()){
                    myAdapter.setData(it[0].foodRecipe)
                }

            }


        }


    }


    private fun setUpRecyclerView(){

        binding.recyclerview
        binding.recyclerview.adapter = myAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmer()

    }

     private fun requestApiData(){

         recipesViewModel.getRecipe(queryViewModel.queryRecipes())

         recipesViewModel.recipeData.observe(viewLifecycleOwner){ response ->
             when(response){

                     is NetworkResult.Success -> {
                         hideShimmer()
                         hasErrorOccurred = false
                         response.data?.let { myAdapter.setData(it) }
                     }

                     is NetworkResult.Error -> {
                         hideShimmer()
                         Log.d("recipesFragment", response.message.toString())
                         if (!hasErrorOccurred) {
                             readDatabase()
                             localDataCash()
                             hasErrorOccurred = true
                         }


                     }

                     is NetworkResult.LodIng -> showShimmer()

                 }

         }

    }


    private fun showShimmer(){
        binding.shimmerRecyclerView.showShimmerAdapter()
    }
    private fun hideShimmer(){
        binding.shimmerRecyclerView.hideShimmerAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}