package com.example.cocktailRecipes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cocktailRecipes.data.database.entity.Drink

@Database(entities = [Drink::class], version = 1, exportSchema = false)
abstract class CocktailsDatabase : RoomDatabase() {
    abstract fun cocktailRecipesDao(): CocktailDao

    companion object {
        @Volatile private var instance: CocktailsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, CocktailsDatabase::class.java, "cocktailRecipes.db").build()

    }
}