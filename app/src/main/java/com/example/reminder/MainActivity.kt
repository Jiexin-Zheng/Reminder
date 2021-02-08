package com.example.reminder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

var username_g:String? = "2333"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the Intent that started this activity and extract the string
        username_g = intent.getStringExtra(USERNAME_MESSAGE)  //这两行放外面会导致mainactivity启动不正常？？？
        val password = intent.getStringExtra(PASSWORD_MESSAGE)
    }

    fun CheckReminder(view: View) {
        // Do something in response to button
        val intent = Intent(this, ReminderActivity::class.java).apply {
        }
        startActivity(intent)
    }

    fun Logout(view: View) {
        // Do something in response to button
        val intent = Intent(this, LoginActivity::class.java).apply {
        }
        startActivity(intent)
    }

    fun CheckProfile(view: View) {
        // Do something in response to button
        val intent = Intent(this, ProfileActivity::class.java).apply {
//            putExtra(USERNAME_MESSAGE, username)
//            putExtra(PASSWORD_MESSAGE, password)
        }
        startActivity(intent)
    }

    fun CreateReminder(view: View) {
        // Do something in response to button
        val intent = Intent(this, CreateReminderActivity::class.java).apply {
        }
        startActivity(intent)
    }
}