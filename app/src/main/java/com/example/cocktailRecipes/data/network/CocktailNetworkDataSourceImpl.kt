package com.example.cocktailRecipes.data.network

import android.util.Log
import com.example.cocktailRecipes.data.database.entity.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CocktailNetworkDataSourceImpl(
    private val apiService: ApiService
) : CocktailNetworkDataSource {

    override suspend fun getCocktailRecipes(filter: String): List<Drink> =
        withContext(Dispatchers.IO) {
            val request = apiService.getCocktailRecipesAsync(filter)
            try {
                val response = request.await()
                return@withContext response.drinks
            } catch (exception: HttpException) {
                Log.e("RequestError", "Error: ${exception.printStackTrace()}")
                return@withContext emptyList<Drink>()
            } catch (exception: Throwable) {
                Log.e("RequestError", "Error: ${exception.printStackTrace()}")
                return@withContext emptyList<Drink>()
            }
        }
}