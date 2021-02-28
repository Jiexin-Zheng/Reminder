package com.example.reminder

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class ReminderActivity : AppCompatActivity() {

    companion object {

        const val ADD_KEY = 100
    }

    private val reminderList = ArrayList<BeanOfReminder>()
    private val RemindHelperByCyrus = HelperOfReminder()
    private lateinit var AdapterByCyrus: AdapterOfReminder
    private var itemPosition = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)
        /*
        initFruits()
        val listView = findViewById<ListView>(R.id.listView)
        val adapter = FruitAdapter(this,R.layout.fruit_item,fruitList)
        listView.adapter  = adapter
        */
        val createBtn = findViewById<Button>(R.id.createBtn)
        val listView = findViewById<ListView>(R.id.listView)
//        println("hahahahahahaha")
//        println(username_g)
        SQLiteHelperOfReminder.init(this)

        createBtn.setOnClickListener {
            startActivityForResult(Intent(this@ReminderActivity, CreateReminderActivity::class.java), ADD_KEY)
        }
        getListData("showAll")
        AdapterByCyrus = AdapterOfReminder(this, R.layout.reminder_item, reminderList)
        listView.adapter = AdapterByCyrus
        listView.setOnItemClickListener { parent, view, position, id ->
            val get = reminderList[position]
            val intent = Intent(this@ReminderActivity, CreateReminderActivity::class.java)
            intent.putExtra(CreateReminderActivity.ReminderItem, get)
            itemPosition = position
            startActivityForResult(intent, ADD_KEY)
        }
        RemindHelperByCyrus.setOnThreadRemindListener {
            runOnUiThread {
/*
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.title_remind))
                builder.setMessage(it.content)
                builder.setPositiveButton(getString(R.string.txt_ok)) { _: DialogInterface, _: Int -> }
                builder.show()
*/

                val builder = AlertDialog.Builder(this)
                builder.setTitle("creation information")
                builder.setMessage(it.reminder_message)
                builder.setPositiveButton("OK") { _: DialogInterface, _: Int -> }
                builder.show()
            }
        }
        /*
        tvSetting.setOnClickListener {
            startActivity(Intent(this@ReminderActivity,PersonCenterActivity::class.java))
        }
        */

    }


    fun RespondHide(view: View)
    {
        val createBtn = findViewById<Button>(R.id.createBtn)
        val listView = findViewById<ListView>(R.id.listView)
        SQLiteHelperOfReminder.init(this)

        createBtn.setOnClickListener {
            startActivityForResult(Intent(this@ReminderActivity, CreateReminderActivity::class.java), ADD_KEY)
        }
        getListData("hide")
        AdapterByCyrus = AdapterOfReminder(this, R.layout.reminder_item, reminderList)
        listView.adapter = AdapterByCyrus
        listView.setOnItemClickListener { parent, view, position, id ->
            val get = reminderList[position]
            val intent = Intent(this@ReminderActivity, CreateReminderActivity::class.java)
            intent.putExtra(CreateReminderActivity.ReminderItem, get)
            itemPosition = position
            startActivityForResult(intent, ADD_KEY)
        }
    }

    fun RespondShowAll(view: View)
    {
        val createBtn = findViewById<Button>(R.id.createBtn)
        val listView = findViewById<ListView>(R.id.listView)
        SQLiteHelperOfReminder.init(this)

        createBtn.setOnClickListener {
            startActivityForResult(Intent(this@ReminderActivity, CreateReminderActivity::class.java), ADD_KEY)
        }
        getListData("showAll")
        AdapterByCyrus = AdapterOfReminder(this, R.layout.reminder_item, reminderList)
        listView.adapter = AdapterByCyrus
        listView.setOnItemClickListener { parent, view, position, id ->
            val get = reminderList[position]
            val intent = Intent(this@ReminderActivity, CreateReminderActivity::class.java)
            intent.putExtra(CreateReminderActivity.ReminderItem, get)
            itemPosition = position
            startActivityForResult(intent, ADD_KEY)
        }
    }


    private fun getListData(string: String) {
        reminderList.clear()
        val list = SQLiteHelperOfReminder.getAllList()
        val date = Date()
        list.forEach {
            date.time = it.reminder_time
            if (string == "hide"){
                if(it.reminder_seen > 1){
                    reminderList.add(
                            BeanOfReminder(
                                    it.id,
                                    it.reminder_seen == 1,
                                    SimpleDateFormat(
                                            "yyyy-MM-dd H:mm:ss",
                                            Locale.getDefault()
                                    ).format(date),
                                    it.reminder_message,
                                    SimpleDateFormat(
                                            "yyyy-MM-dd H:mm:ss",
                                            Locale.getDefault()
                                    ).format(date),
                                    it.location_x,
                                    it.location_y,
                                    it.icon_id
                            )
                    )
                }
            }else{
                reminderList.add(
                        BeanOfReminder(
                                it.id,
                                it.reminder_seen == 1,
                                SimpleDateFormat(
                                        "yyyy-MM-dd H:mm:ss",
                                        Locale.getDefault()
                                ).format(date),
                                it.reminder_message,
                                SimpleDateFormat(
                                        "yyyy-MM-dd H:mm:ss",
                                        Locale.getDefault()
                                ).format(date),
                                it.location_x,
                                it.location_y,
                                it.icon_id
                        )
                )
            }

        }
    }

    override fun onPause() {
        super.onPause()
        RemindHelperByCyrus.onPause()
    }

    override fun onResume() {
        super.onResume()
        RemindHelperByCyrus.onResume()
        getListData("showAll")
        AdapterByCyrus.notifyDataSetChanged()
    }


    override fun onDestroy() {
        super.onDestroy()
        RemindHelperByCyrus.quit()
    }


}






