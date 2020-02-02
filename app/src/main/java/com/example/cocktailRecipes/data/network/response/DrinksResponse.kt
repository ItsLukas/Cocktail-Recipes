package com.example.cocktailRecipes.data.repository

import com.example.cocktailRecipes.data.database.entity.Drink
import com.example.cocktailRecipes.ui.cocktailRecipes.list.CocktailRecipeItem

data class DrinksResponse(val items: List<Drink>) {

    fun getDrinksAsListItems(): List<CocktailRecipeItem> {
        return items.toCocktailRecipeItems()
    }
}

private fun List<Drink>.toCocktailRecipeItems(): List<CocktailRecipeItem> {
    return this.map {
        CocktailRecipeItem(it)
    }
}