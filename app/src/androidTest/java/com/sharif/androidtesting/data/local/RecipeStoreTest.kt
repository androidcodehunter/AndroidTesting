package com.sharif.androidtesting.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeStoreTest{

    @Test
    fun nullDirectory(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        var store = RecipeStore(context, "")
        assertNotNull(store)
        assertNotNull(store.recipes)
        assertEquals(0, store.recipes.size)
    }


    @Test
    fun count(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        var store = RecipeStore(context, "recipes")
        assertNotNull(store)
        assertNotNull(store.recipes)
        assertEquals(4, store.recipes.size)
    }


    @Test
    fun getChocolatePudding(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        var store = RecipeStore(context, "recipes")
        val recipe = store.getRecipe("chocolate_pudding")
        assertNotNull(recipe)
        assertEquals("chocolate_pudding", recipe?.id)
        assertEquals("Chocolate Pudding", recipe?.title)
    }

}