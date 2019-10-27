package com.sharif.androidtesting.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sharif.androidtesting.R
import com.sharif.androidtesting.RecipeAdapter
import com.sharif.androidtesting.data.local.RecipeStore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.title)


        val tvGreet = findViewById<AppCompatTextView>(R.id.tv_greet)
        val btnGreet = findViewById<AppCompatButton>(R.id.btn_greet)


        btnGreet.setOnClickListener {
            btnGreet.isEnabled = false
            tvGreet.text = getString(R.string.hello)
        }


 /*       val recyclerView = findViewById<RecyclerView>(R.id.recipes)

        val store = RecipeStore(this, "recipes")
        val viewManager = LinearLayoutManager(this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = RecipeAdapter(store)
        }
*/
    }


}
