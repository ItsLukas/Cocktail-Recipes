package com.example.cocktailRecipes.ui.cocktailRecipes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailRecipes.R
import com.example.cocktailRecipes.data.database.entity.Drink
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.cocktails_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CocktailsFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: CocktailListViewModelFactory by instance()

    private lateinit var viewModel: CocktailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cocktails_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Cocktails"
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CocktailsViewModel::class.java)

        viewModel.cocktails().observe(this@CocktailsFragment, Observer { recipes ->
            if (recipes.isNullOrEmpty()) {
                errorMessage.text = "No Cocktail Recipes Were found with chosen filters"
                return@Observer
            }
            errorMessage.text = ""
            initRecyclerView(recipes.toCocktailRecipeItems())
        })
    }

    private fun List<Drink>.toCocktailRecipeItems(): List<CocktailRecipeItem> {
        return this.map {
            CocktailRecipeItem(it)
        }
    }

    private fun initRecyclerView(items: List<CocktailRecipeItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CocktailsFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? CocktailRecipeItem)?.let {
                showCocktailDetailedRecipe(it.cocktailRecipeEntry.idDrink.toInt(), view)
            }
        }
    }

    private fun showCocktailDetailedRecipe(recipeId: Int, view: View) {
        val actionDetail = CocktailsFragmentDirections.actionDetail(recipeId)
        Navigation.findNavController(view).navigate(actionDetail)
    }

}
