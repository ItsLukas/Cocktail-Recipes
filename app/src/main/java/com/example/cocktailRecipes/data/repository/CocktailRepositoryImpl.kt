package com.example.cocktailRecipes.data.repository

import androidx.lifecycle.LiveData
import com.example.cocktailRecipes.data.database.CocktailDao
import com.example.cocktailRecipes.data.database.entity.DetailedCocktailRecipe
import com.example.cocktailRecipes.data.database.entity.Drink
import com.example.cocktailRecipes.data.network.CocktailNetworkDataSource
import com.example.cocktailRecipes.data.provider.FilterProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocktailRepositoryImpl(
    private val cocktailNetworkDataSource: CocktailNetworkDataSource,
    private val cocktailDao: CocktailDao,
    private val filterProvider: FilterProvider
) : CocktailRepository {

    override val cocktails: LiveData<List<Drink>> by lazy {
        cocktailDao.getCocktails()
    }

    override suspend fun refreshCocktails() {
        withContext(Dispatchers.IO) {
            val result =
                cocktailNetworkDataSource.getCocktailRecipes(filterProvider.getFilterLetter())
            if (!result.isNullOrEmpty()) {
                cocktailDao.deleteAllCocktailRecipes()
                cocktailDao.upsert(result)
            }
        }
    }

    override suspend fun getCocktailRecipes(): LiveData<out List<Drink>> {
        return withContext(Dispatchers.IO) {
            return@withContext cocktailDao.getCocktails()
        }
    }

    override suspend fun getDetailedCocktailRecipe(recipeId: Int): LiveData<DetailedCocktailRecipe> {
        return withContext(Dispatchers.IO) {
            return@withContext cocktailDao.getDetailedCocktailRecipe(recipeId)
        }
    }
}