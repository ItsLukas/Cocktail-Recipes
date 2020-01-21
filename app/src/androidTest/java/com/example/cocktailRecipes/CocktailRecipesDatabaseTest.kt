package com.example.cocktailRecipes;

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry

import com.example.cocktailRecipes.data.database.CocktailDao;
import com.example.cocktailRecipes.data.database.CocktailsDatabase;
import com.example.cocktailRecipes.data.database.entity.Drink
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith;
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CocktailRecipeDatabaseTest {
    private lateinit var cocktailDao: CocktailDao
    private lateinit var database: CocktailsDatabase

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, CocktailsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        cocktailDao = database.cocktailRecipesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetCocktailRecipe() {
        val drinks = listOf(Drink("0", "Not", "Coffee", "Drink name", "", "Glass", "Do this and that", "", "","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""))
        cocktailDao.upsert(drinks)
        val cocktail = cocktailDao.getFirstItem()
        Assert.assertEquals(cocktail.idDrink, "0")
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetCocktailRecipeCount() {
        val drinks = listOf(Drink("0", "Not", "Coffee", "Drink name", "", "Glass", "Do this and that", "", "","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""))
        cocktailDao.upsert(drinks)
        val cocktailCount = cocktailDao.getCocktailsCount()
        Assert.assertNotEquals(cocktailCount, 0)
    }

    @Test
    @Throws(Exception::class)
    fun deleteCocktailRecipe() {
        val drinks = listOf(Drink("0", "Not", "Coffee", "Drink name", "", "Glass", "Do this and that", "", "","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""))
        cocktailDao.upsert(drinks)

        val cocktailCount = cocktailDao.getCocktailsCount()
        Assert.assertNotEquals(cocktailCount, 0)

        cocktailDao.deleteAllCocktailRecipes()
        val cocktailCount2 = cocktailDao.getCocktailsCount()
        Assert.assertEquals(cocktailCount2, 0 )
    }

}
