package com.example.reminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listviewtest.Fruit

class IconActivity : AppCompatActivity() {
    var reminderBeanByCyrus: BeanOfReminder? = null
    private val iconList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icon)
        /*
        initFruits()
        val listView = findViewById<ListView>(R.id.iconLV)
        val adapter = FruitAdapter(this,R.layout.fruit_item,iconList)
        listView.adapter  = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val get = iconList[position]
            reminderBeanByCyrus = intent.getParcelableExtra(CreateReminderActivity.ReminderItem)
            reminderBeanByCyrus?.id?.let { SQLiteHelperOfReminder.updateIcon(it,get.id) }

        }
    }

    private fun initFruits() {
        repeat(2){
            fruitList.add(Fruit("apple",R.drawable.pricture))
            fruitList.add(Fruit("banana",R.drawable.pricture))
            fruitList.add(Fruit("orange",R.drawable.pricture))
            fruitList.add(Fruit("watermelon",R.drawable.pricture))
            fruitList.add(Fruit("pear",R.drawable.pricture))
            fruitList.add(Fruit("grape",R.drawable.pricture))
            fruitList.add(Fruit("pineapple",R.drawable.pricture))
            fruitList.add(Fruit("strawberry",R.drawable.pricture))
            fruitList.add(Fruit("cherry",R.drawable.pricture))
            fruitList.add(Fruit("mango",R.drawable.pricture))
        }

    */
    }
}


