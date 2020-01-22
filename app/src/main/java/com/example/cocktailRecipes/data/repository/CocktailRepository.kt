package com.example.cocktailRecipes.data.repository

import androidx.lifecycle.LiveData
import com.example.cocktailRecipes.data.database.entity.DetailedCocktailRecipe
import com.example.cocktailRecipes.data.database.entity.Drink

interface CocktailRepository {
    val cocktails: LiveData<List<Drink>>
    suspend fun refreshCocktails()
    suspend fun getCocktailRecipes(): LiveData<out List<Drink>>
    suspend fun getDetailedCocktailRecipe(recipeId: Int): LiveData<DetailedCocktailRecipe>
}