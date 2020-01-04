package com.example.cocktailRecipes.data.network

import androidx.lifecycle.LiveData
import com.example.cocktailRecipes.data.network.response.CocktailsResponse

interface CocktailNetworkDataSource {
    val downloadedCocktailRecipes: LiveData<CocktailsResponse>

    suspend fun fetchCocktailRecipes(
        filter: String
    )
}