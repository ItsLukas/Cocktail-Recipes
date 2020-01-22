package com.example.cocktailRecipes.ui.cocktailRecipes.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailRecipes.data.database.entity.Drink
import com.example.cocktailRecipes.data.repository.CocktailRepository
import com.example.cocktailRecipes.internal.lazyDeferred
import kotlinx.coroutines.launch

class CocktailsViewModel(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {

    init {
        viewModelScope.launch { cocktailRepository.refreshCocktails() }
    }

    fun cocktails(): LiveData<List<Drink>> {
        return cocktailRepository.cocktails
    }
}
