package com.example.cocktailRecipes.ui.cocktailRecipes.detailed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer


import com.example.cocktailRecipes.R
import com.example.cocktailRecipes.internal.GlideApp
import kotlinx.android.synthetic.main.cocktail_detailed_fragment.*
import kotlinx.android.synthetic.main.cocktail_recipe_item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance

class CocktailDetailedFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactoryInstanceFactory: ((Int) -> CocktailDetailedViewModelFactory) by factory()


    private lateinit var viewModel: CocktailDetailedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cocktail_detailed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let{CocktailDetailedFragmentArgs.fromBundle(it)}
        val recipeId = safeArgs?.recipeId
        viewModel = ViewModelProviders.of(this, viewModelFactoryInstanceFactory(recipeId!!)).get(CocktailDetailedViewModel::class.java)

        GlobalScope.launch (Dispatchers.Main){
            val cocktailDetailedRecipe = viewModel.cocktailDetailedRecipe.await()

            cocktailDetailedRecipe.observe(this@CocktailDetailedFragment, Observer { recipe ->
                if (recipe == null) return@Observer

                (activity as? AppCompatActivity)?.supportActionBar?.title = recipe.strDrink
                GlideApp.with(this@CocktailDetailedFragment)
                    .load(recipe.strDrinkThumb)
                    .into(cocktailDetailedImage)

                instructions.text = recipe.strInstructions
                cocktailDetailedName.text = recipe.strDrink
                cocktailDetailedCategory.text = recipe.strCategory
                cocktailDetailedType.text = recipe.strAlcoholic
                cocktailDetailedGlass.text = recipe.strGlass

                //Ingredients
                ingredient1.text = recipe.strIngredient1
                ingredient2.text = recipe.strIngredient2
                ingredient3.text = recipe.strIngredient3
                ingredient4.text = recipe.strIngredient4
                ingredient5.text = recipe.strIngredient5
                ingredient6.text = recipe.strIngredient6
                ingredient7.text = recipe.strIngredient7
                ingredient8.text = recipe.strIngredient8
                ingredient9.text = recipe.strIngredient9
                ingredient10.text = recipe.strIngredient10
                ingredient11.text = recipe.strIngredient11
                ingredient12.text = recipe.strIngredient12
                ingredient13.text = recipe.strIngredient13
                ingredient14.text = recipe.strIngredient14
                ingredient15.text = recipe.strIngredient15

                //Ingredient Measurements
                measurement1.text = recipe.strMeasure1
                measurement2.text = recipe.strMeasure2
                measurement3.text = recipe.strMeasure3
                measurement4.text = recipe.strMeasure4
                measurement5.text = recipe.strMeasure5
                measurement6.text = recipe.strMeasure6
                measurement7.text = recipe.strMeasure7
                measurement8.text = recipe.strMeasure8
                measurement9.text = recipe.strMeasure9
                measurement10.text = recipe.strMeasure10
                measurement11.text = recipe.strMeasure11
                measurement12.text = recipe.strMeasure12
                measurement13.text = recipe.strMeasure13
                measurement14.text = recipe.strMeasure14
                measurement15.text = recipe.strMeasure15
            })
        }
    }

}
