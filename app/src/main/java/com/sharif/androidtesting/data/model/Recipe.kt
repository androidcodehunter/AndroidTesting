package com.sharif.androidtesting.data.model

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

data class Recipe(val id: String, val title: String, val description: String){

    companion object{

        const val ID_PREFIX = "id="
        const val TITLE_PREFIX = "title="

        fun readFromStream(stream: InputStream?): Recipe? {
            var id = ""
            var title = ""
            var desBuilder = StringBuilder()

           val reader = BufferedReader(InputStreamReader(stream))

            reader.readLines().forEach {
                if(it.startsWith(ID_PREFIX)){
                    id = it.substring(ID_PREFIX.length)
                }else if(it.startsWith(TITLE_PREFIX)){
                    title = it.substring(TITLE_PREFIX.length)
                }else{
                    if (desBuilder.length > 0){
                        desBuilder.append("\n")
                    }
                    desBuilder.append(it)
                }

            }

            return Recipe(id, title, desBuilder.toString())
        }
    }

}