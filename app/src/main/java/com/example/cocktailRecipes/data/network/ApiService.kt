package com.example.cocktailRecipes.data.network

import com.example.cocktailRecipes.data.network.response.CocktailsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("search.php")
    fun getCocktailRecipesAsync(
        @Query("f") filter: String
    ): Deferred<CocktailsResponse>

    companion object {
        operator fun invoke(): ApiService {
            return Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }
    }
}