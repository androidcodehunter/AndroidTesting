package com.sharif.androidtesting.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferenceFavorite(context: Context): Favorites {

    private var pref: SharedPreferences = context.getSharedPreferences("favorites.xml", Context.MODE_PRIVATE)

    override fun get(id: String): Boolean {
        return pref.getBoolean(id, false)
    }


    fun put(id: String, favorite: Boolean){
        val editor = pref.edit()
        if (favorite){
            editor.putBoolean(id, true)
        }else{
            editor.remove(id)
        }

        editor.apply()
    }

    override fun toggle(id: String): Boolean {
        val favorite = get(id)
        put(id, !favorite)
        return !favorite
    }

}