package com.example.reminder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

const val USERNAME_MESSAGE = "com.example.Reminder.username"
const val PASSWORD_MESSAGE = "com.example.Reminder.password"

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    /** Called when the user taps the Send button */
    fun RespondRegister(view: View) {
        // Do something in response to button
        val intent = Intent(this, RegisterActivity::class.java).apply {
        }

        startActivity(intent)
    }


    /** Called when the user taps the Send button */
    fun RespondLogin(view: View) {
        // Do something in response to button
        val username_view = findViewById<EditText>(R.id.username)
        val name = username_view.text.toString()
        val password_view = findViewById<EditText>(R.id.password)
        val password = password_view.text.toString()
        val settings = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val pwd = settings.getString( "PASSWORD" + name, "not found")
  //      val pwd = SPDatabase.getString("PASSWORD" + name, "not found")
        if(pwd == password){
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(USERNAME_MESSAGE, name)
                putExtra(PASSWORD_MESSAGE, password)
                val editor = settings.edit()
                editor.putString("USER_TEMP", name)
                editor.commit()
            }

            startActivity(intent)
        }else{

            val builder = AlertDialog.Builder(this)

            if(pwd == "not found")
            {
                builder.setTitle("Login information")
                builder.setMessage("account not found, please register first !")

            }else{
                builder.setTitle("Login information")
                builder.setMessage("the password or username may be wrong !")

            }

            val alert = builder.create()
            alert.show()
        }



    }


}