package com.sharif.androidtesting

import com.sharif.androidtesting.data.model.Recipe
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class RecipeTest {

    @Test
    fun water(){
        val recipeStream = RecipeTest::class.java.getResourceAsStream("/recipes/water.txt")
        val recipe = Recipe.readFromStream(recipeStream)
        assertNotNull(recipe)
        assertEquals("water", recipe?.id)
        assertEquals("Water", recipe?.title)
        assertEquals("Put glass under tap. Open tap. Close tap. Drink.", recipe?.description)
    }


    @Test
    fun mixed(){
        val recipeStream = RecipeTest::class.java.getResourceAsStream("/recipes/mixed.txt")
        val recipe = Recipe.readFromStream(recipeStream)
        assertNotNull(recipe)
        assertEquals("punch", recipe?.id)
        assertEquals("Punch", recipe?.title)
        assertEquals("Juice of 3 lemons\n" +
                "1 orange\n" +
                "1 pint grape juice\n" +
                "1 cup sugar\n" +
                "1 cup water\n" +
                "1 pine apple juice\n" +
                "Mix all together and strain. Add large piece of ice.", recipe?.description)
    }

}