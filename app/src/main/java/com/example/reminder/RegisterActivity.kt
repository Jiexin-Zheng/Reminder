package com.example.reminder

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun RespondCreate(view: View) {
        // Do something in response to button
        val username_view = findViewById<EditText>(R.id.nameR)
        val name = username_view.text.toString()
        val password_view1 = findViewById<EditText>(R.id.pwdR1)
        val password1 = password_view1.text.toString()
        val email_view = findViewById<EditText>(R.id.emailR)
        val email = email_view.text.toString()
        val password_view2 = findViewById<EditText>(R.id.pwdR2)
        val password2 = password_view2.text.toString()
        val phone_view = findViewById<EditText>(R.id.phoneR)
        val phone = phone_view.text.toString()

        val settings = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val check_name = settings.getString( name, "not found")

  //      val check_name = SPDatabase.getString( name, "not found")
        val builder = AlertDialog.Builder(this)

        if(name == check_name)
        {
            builder.setTitle("Registration information")
            builder.setMessage("the name has been used !")
        }else{

            if(!(password1 == password2))
            {
                builder.setTitle("Registration information")
                builder.setMessage("the two passwords you type should be same !")
            }else{

                val editor = settings.edit()
                editor.putString("NAME", name)
                editor.putString("PASSWORD" + name, password1)
                editor.putString("EMAIL" + name, email)
                editor.putString("PHONE"+ name, phone)
                editor.commit()

 /*
                SPDatabase.putString("NAME", name)
                SPDatabase.putString("PASSWORD" + name, password1)
                SPDatabase.putString("EMAIL" + name, email)
                SPDatabase.putString("PHONE"+ name, phone)
   */
                builder.setTitle("Registration information")
                builder.setMessage("Register Successfully !")
            }

        }

//       builder.setPositiveButton("残忍卸载") { dialog, which -> tv_alert.text = "虽然依依不舍，还是只能离开了" }
//        builder.setNegativeButton("我再想想") { dialog, which -> tv_alert.text = "让我再陪你三百六十五个日夜" }
        val alert = builder.create()
        alert.show()
    }
}