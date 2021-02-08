package com.example.reminder


//import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.listviewtest.Fruit
import java.util.*



class ReminderActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        initFruits()
        val listView = findViewById<ListView>(R.id.listView)
        val adapter = FruitAdapter(this,R.layout.fruit_item,fruitList)
        listView.adapter  = adapter
    }

    private fun initFruits() {

        /*
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
        repeat(2){
            fruitList.add(Fruit("reminder1"))
            fruitList.add(Fruit("reminder2"))
            fruitList.add(Fruit("reminder3"))
            fruitList.add(Fruit("reminder4"))
            fruitList.add(Fruit("reminder5"))
            fruitList.add(Fruit("reminder6"))
            fruitList.add(Fruit("reminder7"))
            fruitList.add(Fruit("reminder8"))
            fruitList.add(Fruit("reminder9"))
            fruitList.add(Fruit("reminder10"))
        }

    }
}
