package com.example.reminder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val settings = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val password = settings.getString( "PASSWORD" + username_g, "not found")
        val phone = settings.getString( "PHONE" + username_g, "not found")
        val email = settings.getString( "EMAIL" + username_g, "not found")

        findViewById<TextView>(R.id.nameP).apply {
            text = username_g
        }
        findViewById<TextView>(R.id.emailP).apply {
            text = email
        }
        findViewById<TextView>(R.id.phoneP).apply {
            text = phone
        }
        findViewById<TextView>(R.id.pwdP).apply {
            text = password
        }
    }

    fun RespondModify(view: View) {
        // Do something in response to button
        val username_view = findViewById<EditText>(R.id.nameP)
        val name = username_view.text.toString()
        val password_view1 = findViewById<EditText>(R.id.pwdP)
        val password1 = password_view1.text.toString()
        val email_view = findViewById<EditText>(R.id.emailP)
        val email = email_view.text.toString()
        val phone_view = findViewById<EditText>(R.id.phoneP)
        val phone = phone_view.text.toString()

        val settings = getSharedPreferences("settings", Context.MODE_PRIVATE)
//        val check_name = settings.getString( name, "not found")
        val builder = AlertDialog.Builder(this)

        if(!(name == username_g))
        {
            builder.setTitle("information")
            builder.setMessage("the name cannot be modified !")
        }else{
            val editor = settings.edit()
            editor.putString("NAME", name)
            editor.putString("PASSWORD" + name, password1)
            editor.putString("EMAIL" + name, email)
            editor.putString("PHONE"+ name, phone)
            editor.commit()
            builder.setTitle("information")
            builder.setMessage("Modify Successfully !")

        }

        val alert = builder.create()
        alert.show()
    }

    fun Photo(view: View) {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(intent, 110)
    }

}
