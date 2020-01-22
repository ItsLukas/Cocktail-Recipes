package com.example.cocktailRecipes.data.network

import com.example.cocktailRecipes.data.database.entity.Drink

interface CocktailNetworkDataSource {
    suspend fun getCocktailRecipes(filter: String): List<Drink>
}