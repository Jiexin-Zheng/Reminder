package com.example.reminder

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.listviewtest.Fruit

class FruitAdapter(activity: Activity, val resourceId: Int, data: List<Fruit>) : ArrayAdapter<Fruit>(activity, resourceId, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val fruitImage: ImageView = view.findViewById(R.id.reminderIcon)
        val fruitName: TextView = view.findViewById(R.id.reminderTime)
        val fruit = getItem(position)
        if (fruit!=null){
//            fruitImage.setImageResource(fruit.imageId)
           // fruitName.text = fruit.name
        }
        return view
    }
}

