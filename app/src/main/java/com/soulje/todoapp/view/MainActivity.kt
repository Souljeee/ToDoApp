package com.soulje.todoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.soulje.todoapp.R
import com.soulje.todoapp.adapter.CategoryAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val array = arrayListOf<String>("Все","Работа","Личное","Здоровье")
        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = CategoryAdapter(this,array)
        spinner.adapter = adapter
        supportFragmentManager.beginTransaction().add(R.id.container, MainFragment()).commit()
    }
}