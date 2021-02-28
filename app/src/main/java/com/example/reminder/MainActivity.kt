package com.example.reminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        // Get the Intent that started this activity and extract the string
        if (intent.getStringExtra(USERNAME_MESSAGE) != null)
        {
            //username_g = intent.getStringExtra(USERNAME_MESSAGE)  //这两行放外面会导致mainactivity启动不正常？？？
           // val password = intent.getStringExtra(PASSWORD_MESSAGE)
        }

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
       /*
        val intent = Intent(this, CreateReminderActivity::class.java).apply {
        }
        startActivity(intent)
   */
    }

    private fun createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "reminder"
            val descriptionText = "6666"
            // 提醒式通知(横幅显示)，不过大部分需要手动授权
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("222", name, importance).apply {description = descriptionText}
            channel.setSound(Uri.parse("android.resource://"
                    + getPackageName() + "/" + R.raw.ring_custom), null)
            // 注册通道(频道)
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}