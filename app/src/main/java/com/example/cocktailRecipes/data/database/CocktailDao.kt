package com.example.cocktailRecipes.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktailRecipes.data.database.entity.DetailedCocktailRecipe
import com.example.cocktailRecipes.data.database.entity.Drink

@Dao
interface CocktailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(cocktail: List<Drink>)

    @Query("SELECT * FROM cocktail")
    fun getCocktails(): LiveData<List<Drink>>

    @Query("SELECT * FROM cocktail WHERE idDrink == :recipeId")
    fun getDetailedCocktailRecipe(recipeId: Int): LiveData<DetailedCocktailRecipe>

    @Query("SELECT count(*) FROM cocktail")
    fun getCocktailsCount(): Int

    @Query("DELETE FROM cocktail")
    fun deleteAllCocktailRecipes()

    @Query("SELECT * FROM cocktail LIMIT 1")
    fun getFirstItem() : DetailedCocktailRecipe
}