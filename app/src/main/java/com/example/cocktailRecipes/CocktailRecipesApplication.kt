package com.example.cocktailRecipes

import android.app.Application
import com.example.cocktailRecipes.data.database.CocktailsDatabase
import com.example.cocktailRecipes.data.network.ApiService
import com.example.cocktailRecipes.data.network.CocktailNetworkDataSource
import com.example.cocktailRecipes.data.network.CocktailNetworkDataSourceImpl
import com.example.cocktailRecipes.data.provider.FilterProvider
import com.example.cocktailRecipes.data.provider.FilterProviderImpl
import com.example.cocktailRecipes.data.repository.CocktailRepository
import com.example.cocktailRecipes.data.repository.CocktailRepositoryImpl
import com.example.cocktailRecipes.ui.cocktailRecipes.detailed.CocktailDetailedViewModelFactory
import com.example.cocktailRecipes.ui.cocktailRecipes.list.CocktailListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class CocktailRecipesApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@CocktailRecipesApplication))

        bind() from singleton { CocktailsDatabase(instance()) }
        bind() from singleton { instance<CocktailsDatabase>().cocktailRecipesDao() }
        bind() from singleton { ApiService() }
        bind<CocktailNetworkDataSource>() with singleton { CocktailNetworkDataSourceImpl(instance()) }
        bind<FilterProvider>() with singleton { FilterProviderImpl(instance()) }
        bind<CocktailRepository>() with singleton { CocktailRepositoryImpl(instance(), instance(), instance()) }
        bind() from provider { CocktailListViewModelFactory(instance()) }
        bind() from factory{recipeId: Int -> CocktailDetailedViewModelFactory(instance(), recipeId)}
    }
}