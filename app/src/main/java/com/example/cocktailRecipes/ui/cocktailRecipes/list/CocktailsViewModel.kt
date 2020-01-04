package com.example.cocktailRecipes.ui.cocktailRecipes.list

import androidx.lifecycle.ViewModel
import com.example.cocktailRecipes.data.repository.CocktailRepository
import com.example.cocktailRecipes.internal.lazyDeferred

class CocktailsViewModel(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {
    val cocktailRecipes by lazyDeferred {
        cocktailRepository.getCocktailRecipes()
    }
}
