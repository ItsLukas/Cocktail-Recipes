package com.example.cocktailRecipes.ui.cocktailRecipes.detailed

import androidx.lifecycle.ViewModel
import com.example.cocktailRecipes.data.repository.CocktailRepository
import com.example.cocktailRecipes.internal.lazyDeferred

class CocktailDetailedViewModel (
    private val cocktailRepository: CocktailRepository,
    private val recipeId: Int
) : ViewModel() {
    val cocktailDetailedRecipe by lazyDeferred {
        cocktailRepository.getDetailedCocktailRecipe(recipeId)
    }
}
