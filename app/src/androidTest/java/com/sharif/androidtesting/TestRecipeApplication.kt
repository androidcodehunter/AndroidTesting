package com.sharif.androidtesting

import com.sharif.androidtesting.data.local.Favorites
import com.sharif.androidtesting.data.local.InMemoryFavorites

class TestRecipeApplication : RecipeApplication() {

    val favorites = InMemoryFavorites()

    override fun getFavorites(): Favorites {
        return favorites
    }
}