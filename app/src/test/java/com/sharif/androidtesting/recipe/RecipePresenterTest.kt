package com.sharif.androidtesting.recipe

import com.sharif.androidtesting.data.local.Favorites
import com.sharif.androidtesting.data.local.RecipeStore
import com.sharif.androidtesting.data.model.Recipe
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import java.lang.IllegalStateException

class RecipePresenterTest{

    private lateinit var store: RecipeStore
    private lateinit var favorites: Favorites
    private lateinit var view: RecipeContract.View
    private lateinit var presenter: RecipePresenter

    @Before
    fun setup(){
        store = Mockito.mock(RecipeStore::class.java)
        favorites = Mockito.mock(Favorites::class.java)
        view = Mockito.mock(RecipeContract.View::class.java)
        presenter = RecipePresenter(store, view, favorites)
    }

    @Test
    fun recipeNotFound(){
        Mockito.`when`(store.getRecipe(Mockito.anyString())).thenReturn(null)
        presenter.loadRecipe("no such recipe")
        Mockito.verify(view, Mockito.times(1)).showRecipeNotFoundError()
    }

    @Test(expected = IllegalStateException::class)
    fun toggleWithoutLoad(){
        presenter.toggleFavorite()
    }

    @Test
    fun loadWaterAndFavorite(){
        val recipeStream = RecipePresenterTest::class.java.getResourceAsStream("/recipes/water.txt")
        val recipe = Recipe.readFromStream(recipeStream)
        Mockito.`when`(store.getRecipe(Mockito.anyString())).thenReturn(recipe)
        Mockito.`when`(favorites.toggle(Mockito.anyString())).thenReturn(true)
        presenter.loadRecipe("water")
        presenter.toggleFavorite()

        val captor:ArgumentCaptor<Boolean> = ArgumentCaptor.forClass(Boolean::class.java)
        Mockito.verify(view, Mockito.times(2)).setFavorite(captor.capture())
        assertFalse(captor.allValues.get(0))
        assertTrue(captor.allValues.get(1))
    }

}