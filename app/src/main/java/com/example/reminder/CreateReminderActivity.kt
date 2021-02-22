package com.example.reminder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*


var CAMERA_CODE = 0
//lateinit var picture_scene_img: ImageView


class CreateReminderActivity : AppCompatActivity(){
    companion object {
        const val ReminderItem = "ReminderItem.Edit"
    }

    private var reminderBeanByCyrus: BeanOfReminder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_reminder)

        val create_tmp = findViewById<Button>(R.id.modifyBtn)
        val message_tmp = findViewById<EditText>(R.id.reminderMessageC)
        val timeC = findViewById<TextView>(R.id.createTimeC)
        val timeR = findViewById<TextView>(R.id.reminderTimeC)
        val locationX = findViewById<TextView>(R.id.locationX)
        val locationY = findViewById<TextView>(R.id.locationY)
        val icon = findViewById<ImageView>(R.id.icon)


//        ivBackPage.setOnClickListener { finish() }
        create_tmp.setOnClickListener {
            if (message_tmp.text.toString().isNotBlank()) {
                val calendar = Calendar.getInstance()
                val dataDialog = DatePickerDialog(
                    this,
                    { view, year, month, dayOfMonth ->
                        val timeDialog = TimePickerDialog(
                            this,
                            { view, hourOfDay, minute ->
                                calendar.set(year, month, dayOfMonth, hourOfDay, minute)
                                if (reminderBeanByCyrus != null && reminderBeanByCyrus!!.id >= 0) {
                                    SQLiteHelperOfReminder.deleteRemindData(reminderBeanByCyrus!!.id)
                                }
                                SQLiteHelperOfReminder.addRemindData(
                                    SQLiteHelperOfReminder.DataOfReminder(
                                        reminder_message = message_tmp.text.toString(),
                                        reminder_time = calendar.time.time,
                                            creation_time = timeC.text.toString().toLong()
                                    )
                                )
                                finish()
                            },
                            calendar[Calendar.HOUR_OF_DAY],
                            calendar[Calendar.MINUTE],
                            true
                        )
                        timeDialog.show()
                    },
                    calendar[Calendar.YEAR],
                    calendar[Calendar.MONTH],
                    calendar[Calendar.DAY_OF_MONTH]
                )
                dataDialog.show()
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("creation information")
                builder.setMessage("the reminder requires input!!")
            }
        }
        reminderBeanByCyrus = intent.getParcelableExtra(ReminderItem)
        reminderBeanByCyrus.let {
            message_tmp.setText(reminderBeanByCyrus?.reminder_message)
            timeR.setText(reminderBeanByCyrus?.reminder_time)
        }

    }

    fun RespondDelete(view: View){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("delete information")
        builder.setMessage("Do you really want to delete the reminder chosen? ")
        builder.setPositiveButton("Yes"){
            dialog, which -> SQLiteHelperOfReminder.deleteRemindData(reminderBeanByCyrus!!.id)
        }
        builder.setNegativeButton("No"){dialog, which ->  }
        val alert = builder.create()
        alert.show()
    }

    /*
    fun IconChange(view: View){
        val intent = Intent(this, IconActivity::class.java).apply {
            putExtra(ReminderItem, reminderBeanByCyrus)
            startActivity(intent)
        }
    }
*/

//method1
    fun ChangePhoto(view: View){
        takePhoto()
    }


    private fun takePhoto() {
        val state: String = Environment.getExternalStorageState() //拿到sdcard是否可用的状态码

        if (state == Environment.MEDIA_MOUNTED) {   //如果可用
            val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_CODE)
        } else {
            Toast.makeText(this, "sdcard不可用", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data == null){
            return
        }else{
            if(requestCode == CAMERA_CODE && resultCode== RESULT_OK){
                var bitmap: Bitmap = data?.extras?.get("data") as Bitmap
                val image_tmp = findViewById<ImageView>(R.id.imageView)
                image_tmp.setImageBitmap(bitmap)
            }
        }

    }


}

