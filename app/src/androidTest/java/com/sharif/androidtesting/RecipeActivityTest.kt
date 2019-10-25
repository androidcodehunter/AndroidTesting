package com.sharif.androidtesting

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.sharif.androidtesting.data.local.InMemoryFavorites
import com.sharif.androidtesting.recipe.RecipeActivity
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeActivityTest{

    companion object{
       const val CARROT_ID = "creamed_carrots"
    }

    @get:Rule var activityTestRule
            = ActivityTestRule(RecipeActivity::class.java, true, false)
    ///@Rule @JvmField var activityActivityTestRule = ActivityTestRule(RecipeActivity::class.java)

    lateinit var favorites: InMemoryFavorites

    @Before
    fun clearFavorites(){
       val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestRecipeApplication
        favorites = app.favorites
        favorites.clear()
    }


    @Test
    fun recipeNotFound(){
        activityTestRule.launchActivity(null)
        onView(withId(R.id.tv_description)).check(matches(withText(R.string.recipe_not_found)))
        onView(withId(R.id.tv_title)).check(matches(not(isDisplayed())))
    }

    @Test
    fun clickToFavorite(){
        launchRecipe(CARROT_ID)
        onView(withId(R.id.tv_title)).check(matches(withText("Creamed Carrots")))
            .check(matches(not(isSelected())))
            .perform(click())
            .check(matches(isSelected()))
    }


    @Test
    fun alreadyFavorite(){
        favorites.put(CARROT_ID, true)
        launchRecipe(CARROT_ID)

        onView(withId(R.id.tv_title)).check(matches(isSelected()))
    }

    fun launchRecipe(id: String){
        val intent = Intent()
        intent.putExtra(RecipeActivity.KEY_ID, id)
        activityTestRule.launchActivity(intent)
    }


}