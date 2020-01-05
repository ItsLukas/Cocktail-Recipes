package com.example.cocktailRecipes.ui.cocktailRecipes.list

import com.example.cocktailRecipes.R
import com.example.cocktailRecipes.data.database.entity.Drink
import com.example.cocktailRecipes.internal.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.cocktail_recipe_item.*

class CocktailRecipeItem(
    val cocktailRecipeEntry: Drink
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            cocktailName.text = cocktailRecipeEntry.strDrink
            cocktailType.text = cocktailRecipeEntry.strAlcoholic
            cocktailCategory.text = cocktailRecipeEntry.strCategory
            GlideApp.with(this.containerView)
                .load(cocktailRecipeEntry.strDrinkThumb)
                .into(cocktailImage)
        }
    }

    override fun getLayout() = R.layout.cocktail_recipe_item
}