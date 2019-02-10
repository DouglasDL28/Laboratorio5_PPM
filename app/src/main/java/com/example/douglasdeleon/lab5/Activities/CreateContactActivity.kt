package com.example.douglasdeleon.lab5.Activities

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.douglasdeleon.lab5.ContactsProvider
import com.example.douglasdeleon.lab5.R


import kotlinx.android.synthetic.main.activity_create_contact.*

class CreateContactActivity : AppCompatActivity() {

    fun homeButton (view: View) {
        val intent = Intent(this@CreateContactActivity, MainActivity::class.java)
        startActivity(intent)
    }

    fun createContactButton (view: View) {
        val values = ContentValues()

        values.put(ContactsProvider.NAME, nameInput.text.toString())
        values.put(ContactsProvider.MAIL, mailInput.text.toString())
        values.put(ContactsProvider.NUMBER, numberInput.text.toString())

        val uri = contentResolver.insert(ContactsProvider.CONTENT_URI, values)

        Toast.makeText(baseContext, uri!!.toString(), Toast.LENGTH_LONG).show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_contact)
    }
}
