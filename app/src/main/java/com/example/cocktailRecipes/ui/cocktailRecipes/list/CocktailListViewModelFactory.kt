package com.example.cocktailRecipes.ui.cocktailRecipes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cocktailRecipes.data.repository.CocktailRepository

class CocktailListViewModelFactory(
    private val cocktailRepository: CocktailRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CocktailsViewModel(cocktailRepository) as T
    }
}