package com.sharif.androidtesting.data.local

import android.content.Context
import android.content.res.AssetManager
import com.sharif.androidtesting.data.model.Recipe
import java.io.File
import java.io.IOException
import java.io.InputStream

class RecipeStore(val context: Context, val directory: String) {
    val recipes = mutableListOf<Recipe>()
    val map = HashMap<String, Recipe>()

    fun getRecipe(id: String): Recipe? {
        return map.get(id)
    }


    init {
        val streams = getAssetStreams(context.assets, directory)
        streams.forEach {
           val recipe = Recipe.readFromStream(it)
            if (recipe != null){
                recipes.add(recipe)
                map.put(recipe.id, recipe)
            }
        }
    }

    companion object{
        fun getAssetStreams(manager: AssetManager, directory: String): MutableList<InputStream> {
            val fileNames = getFilenames(manager, directory)
            val streams = mutableListOf<InputStream>()

            fileNames?.forEach {
                val file = File(directory, it)
                try{
                    val stream = manager.open(file.path)
                    if (stream != null){
                        streams.add(stream)
                    }
                }catch (e: IOException){

                }

            }

            return streams
        }

        fun getFilenames(manager: AssetManager, directory: String): Array<String>? {
            if (directory == null){
                return arrayOf()
            }

            try {
                return manager.list(directory)
            }catch (e: IOException){
                return arrayOf()
            }
        }

    }

}