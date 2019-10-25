package com.sharif.androidtesting

import android.app.Application
import com.sharif.androidtesting.data.local.Favorites
import com.sharif.androidtesting.data.local.PreferenceFavorite

open class RecipeApplication: Application() {

    private lateinit var favorites: Favorites
    override fun onCreate() {
        super.onCreate()
        favorites = PreferenceFavorite(this)
    }

    open fun getFavorites(): Favorites {
        return favorites
    }

}