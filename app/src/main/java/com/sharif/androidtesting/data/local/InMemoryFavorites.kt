package com.sharif.androidtesting.data.local

class InMemoryFavorites: Favorites {

    val map = HashMap<String, Boolean>()

    override fun get(id: String): Boolean {
        return map.get(id) ?: false
    }

    override fun toggle(id: String): Boolean {
        val value = get(id)
        put(id, !value)
        return !value
    }

    fun put(id: String, value: Boolean){
        map.put(id, value)
    }

    fun clear(){
        map.clear()
    }
}