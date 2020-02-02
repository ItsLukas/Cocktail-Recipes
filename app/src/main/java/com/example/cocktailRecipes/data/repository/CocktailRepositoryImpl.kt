package com.example.cocktailRecipes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cocktailRecipes.data.database.CocktailDao
import com.example.cocktailRecipes.data.database.entity.DetailedCocktailRecipe
import com.example.cocktailRecipes.data.database.entity.Drink
import com.example.cocktailRecipes.data.network.CocktailNetworkDataSource
import com.example.cocktailRecipes.data.provider.FilterProvider
import com.example.cocktailRecipes.ui.cocktailRecipes.list.CocktailRecipeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocktailRepositoryImpl(
    private val cocktailNetworkDataSource: CocktailNetworkDataSource,
    private val cocktailDao: CocktailDao,
    private val filterProvider: FilterProvider
) : CocktailRepository {

    override val cocktails: LiveData<DrinksResponse> by lazy {
        Transformations.map(cocktailDao.getCocktails()) {
            DrinksResponse(it)
        }
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

    override fun getCocktailRecipes(): LiveData<out List<Drink>> {
        return cocktailDao.getCocktails()
    }

    override fun getDetailedCocktailRecipe(recipeId: Int): LiveData<DetailedCocktailRecipe> {
        return cocktailDao.getDetailedCocktailRecipe(recipeId)
    }
}