package com.sharif.androidtesting.recipe

import com.sharif.androidtesting.data.local.Favorites
import com.sharif.androidtesting.data.local.RecipeStore
import com.sharif.androidtesting.data.model.Recipe

class RecipePresenter(
    val store: RecipeStore,
    val view: RecipeContract.View,
    val favorites: Favorites
): RecipeContract.Listener {

    private var recipe: Recipe? = null

    fun loadRecipe(id: String){
        recipe = store.getRecipe(id)
        if (recipe == null){
            view.showRecipeNotFoundError()
        }else{
            recipe?.title?.let { view.setTitle(it) }
            recipe?.description?.let { view.setDescription(it) }
            view.setFavorite(favorites.get(recipe!!.id))
        }
    }

    fun toggleFavorite() {
        val result = recipe?.id?.let { favorites.toggle(it) }
        if (result != null) {
            view.setFavorite(result)
        }
    }

}