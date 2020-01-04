package com.example.cocktailRecipes.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cocktailRecipes.data.network.response.CocktailsResponse

class CocktailNetworkDataSourceImpl(
    private val apiService: ApiService
) : CocktailNetworkDataSource {

    private val _downloadedCocktailRecipes = MutableLiveData<CocktailsResponse>()
    override val downloadedCocktailRecipes: LiveData<CocktailsResponse>
        get() = _downloadedCocktailRecipes

    override suspend fun fetchCocktailRecipes(filter: String) {
       val fetchedCocktailRecipes = apiService.getCocktailRecipesAsync(filter).await()
        _downloadedCocktailRecipes.postValue(fetchedCocktailRecipes)
    }
}