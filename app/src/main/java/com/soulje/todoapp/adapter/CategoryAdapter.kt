package com.soulje.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.soulje.todoapp.R

class CategoryAdapter(context: Context, categories: ArrayList<String>) : ArrayAdapter<String>(context,0,categories) {

    private var categories = categories

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position,convertView,parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position,convertView,parent)
    }

    private fun createItemView(position: Int, recycledView: View?, parent: ViewGroup):View {

        val view = LayoutInflater.from(context).inflate(
            R.layout.spinner_item_layout,
            parent,
            false
        )
        val textView = view.findViewById<TextView>(R.id.spinner_item_title)
        val icon = view.findViewById<ImageView>(R.id.spinner_item_icon)

        when(categories[position]){
            "Все" -> {
                icon.setImageResource(R.drawable.ic_baseline_done_blue_24)
                textView.text = categories[position]
            }
            "Работа" -> {
                icon.setImageResource(R.drawable.ic_baseline_laptop_chromebook_24)
                textView.text = categories[position]
            }
            "Личное" -> {
                icon.setImageResource(R.drawable.ic_baseline_person_blue_24)
                textView.text = categories[position]
            }
            "Здоровье" -> {
                icon.setImageResource(R.drawable.ic_baseline_favorite_24)
                textView.text = categories[position]
            }
        }
        return view
    }


}