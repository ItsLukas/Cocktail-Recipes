package com.example.cocktailRecipes.data.network.response


import com.example.cocktailRecipes.data.database.entity.Drink

data class CocktailsResponse(
    val drinks: List<Drink>
)