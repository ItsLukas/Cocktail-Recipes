package com.example.cocktailRecipes.data.repository

import androidx.lifecycle.LiveData
import com.example.cocktailRecipes.data.database.CocktailDao
import com.example.cocktailRecipes.data.database.entity.DetailedCocktailRecipe
import com.example.cocktailRecipes.data.database.entity.Drink
import com.example.cocktailRecipes.data.network.CocktailNetworkDataSource
import com.example.cocktailRecipes.data.network.response.CocktailsResponse
import com.example.cocktailRecipes.data.provider.FilterProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailRepositoryImpl(
    private val cocktailNetworkDataSource: CocktailNetworkDataSource,
    private val cocktailDao: CocktailDao,
    private val filterProvider: FilterProvider
) : CocktailRepository {

    init {
        cocktailNetworkDataSource.apply {
            downloadedCocktailRecipes.observeForever { newCocktailRecipes ->
                persistFetchedCocktailRecipes(newCocktailRecipes)
            }
        }
    }

    private fun persistFetchedCocktailRecipes(fetchedRecipes: CocktailsResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val cocktailRecipesList = fetchedRecipes.drinks
            cocktailDao.upsert(cocktailRecipesList)
        }
    }

    override suspend fun getCocktailRecipes(): LiveData<out List<Drink>> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext cocktailDao.getCocktails()
        }
    }

    override suspend fun getDetailedCocktailRecipe(recipeId: Int): LiveData<DetailedCocktailRecipe> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext cocktailDao.getDetailedCocktailRecipe(recipeId)
        }
    }

    private suspend fun initData() {
        if (isFetchNeeded()) {
            fetchCocktailRecipes()
        }
    }

    private suspend fun fetchCocktailRecipes() {
        cocktailNetworkDataSource.fetchCocktailRecipes(filterProvider.getFilterLetter())
    }

    private fun isFetchNeeded(): Boolean {
        if (cocktailDao.getCocktailsCount() <= 0) {
            return true
        }

        val cocktailName = cocktailDao.getFirstItem().strDrink
        val filterLetter = filterProvider.getFilterLetter().toUpperCase()
        val cocktailFirstLetter = cocktailName.first().toUpperCase().toString()

        if (filterLetter != cocktailFirstLetter) {
            cocktailDao.deleteAllCocktailRecipes()
            return true
        }

        return false
    }
}