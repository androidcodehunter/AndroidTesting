package com.sharif.androidtesting

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sharif.androidtesting.recipe.RecipeActivity.Companion.KEY_ID
import com.sharif.androidtesting.data.local.RecipeStore
import com.sharif.androidtesting.data.model.Recipe
import com.sharif.androidtesting.recipe.RecipeActivity

class RecipeAdapter(val store: RecipeStore): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false))
    }

    override fun getItemCount(): Int {
        return store.recipes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(store.recipes.get(position))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(recipe: Recipe) {
            (itemView as AppCompatTextView).text = recipe.title
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, RecipeActivity::class.java)
                intent.putExtra(KEY_ID, recipe.id)
                itemView.context.startActivity(intent)
            }
        }

    }

}