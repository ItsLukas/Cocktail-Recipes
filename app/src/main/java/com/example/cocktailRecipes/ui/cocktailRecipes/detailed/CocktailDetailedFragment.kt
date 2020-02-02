package com.example.cocktailRecipes.ui.cocktailRecipes.detailed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cocktailRecipes.databinding.CocktailDetailedFragmentBinding
import com.example.cocktailRecipes.internal.GlideApp
import kotlinx.android.synthetic.main.cocktail_detailed_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory

class CocktailDetailedFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactoryInstanceFactory: ((Int) -> CocktailDetailedViewModelFactory) by factory()

    private lateinit var binding: CocktailDetailedFragmentBinding

    private lateinit var viewModel: CocktailDetailedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CocktailDetailedFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let { CocktailDetailedFragmentArgs.fromBundle(it) }
        val recipeId = safeArgs?.recipeId
        viewModel = ViewModelProviders.of(this, viewModelFactoryInstanceFactory(recipeId!!))
            .get(CocktailDetailedViewModel::class.java)

        viewModel.cocktailDetailedRecipe.observe(this@CocktailDetailedFragment, Observer { recipe ->
            if (recipe == null) return@Observer

            binding.recipe = recipe

            (activity as? AppCompatActivity)?.supportActionBar?.title = recipe.strDrink
            GlideApp.with(this@CocktailDetailedFragment)
                .load(recipe.strDrinkThumb)
                .into(cocktailDetailedImage)
        })
    }

}
