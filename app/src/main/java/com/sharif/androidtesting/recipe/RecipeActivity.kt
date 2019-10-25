package com.sharif.androidtesting.recipe

import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.sharif.androidtesting.R
import com.sharif.androidtesting.RecipeApplication
import com.sharif.androidtesting.data.local.RecipeStore

class RecipeActivity : AppCompatActivity(), RecipeContract.View{

    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvDescription: AppCompatTextView

    companion object{
        const val KEY_ID = "id"
    }

    //Set up ui
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_recipe)

        tvTitle = findViewById<AppCompatTextView>(R.id.tv_title)
        tvDescription = findViewById<AppCompatTextView>(R.id.tv_description)

        // Step 2 : Load recipe from store
        val store = RecipeStore(this, "recipes")
        val id = intent.getStringExtra(KEY_ID)
        val favorites = (application as RecipeApplication).getFavorites()
        val presenter = RecipePresenter(store, this, favorites)
        val recipe = presenter.loadRecipe(id)

        //Step 3: if recipe is null, show error. This is done in the presenter.

        //Step 4: if recipe is available, show the recipe



        //Step 5: when the title clicked, toggle the favorites
        tvTitle.setOnClickListener {
            presenter.toggleFavorite()
        }

    }

    override fun showRecipeNotFoundError() {
        tvTitle.visibility = GONE
        tvDescription.text = getString(R.string.recipe_not_found)
    }

    override fun setTitle(title: String) {
        tvTitle.text = title
    }

    override fun setDescription(description: String) {
        tvDescription.text = description
    }

    override fun setFavorite(favorite: Boolean) {
        tvTitle.isSelected = favorite
    }

}