package com.example.cocktailRecipes.ui.cocktailRecipes.detailed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cocktailRecipes.data.repository.CocktailRepository
import com.example.cocktailRecipes.ui.cocktailRecipes.list.CocktailsViewModel

class CocktailDetailedViewModelFactory (
    private val cocktailRepository: CocktailRepository,
    private val recipeId: Int
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CocktailDetailedViewModel(cocktailRepository, recipeId) as T
    }
}