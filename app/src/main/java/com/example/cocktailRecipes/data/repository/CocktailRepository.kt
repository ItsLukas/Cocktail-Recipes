package com.example.cocktailRecipes.data.repository

import androidx.lifecycle.LiveData
import com.example.cocktailRecipes.data.database.entity.DetailedCocktailRecipe
import com.example.cocktailRecipes.data.database.entity.Drink

interface CocktailRepository {
    val cocktails: LiveData<DrinksResponse>
    suspend fun refreshCocktails()
    fun getCocktailRecipes(): LiveData<out List<Drink>>
    fun getDetailedCocktailRecipe(recipeId: Int): LiveData<DetailedCocktailRecipe>
}