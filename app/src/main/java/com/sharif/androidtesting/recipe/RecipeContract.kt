package com.sharif.androidtesting.recipe

interface RecipeContract {
    interface View{
        fun showRecipeNotFoundError()
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun setFavorite(favorite: Boolean)
    }

    interface Listener{

    }
}