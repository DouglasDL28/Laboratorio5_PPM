package com.example.douglasdeleon.lab5.Activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.douglasdeleon.lab5.ContactsProvider
import com.example.douglasdeleon.lab5.R
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.activity_create_contact.*

class ContactActivity : AppCompatActivity() {

    fun homeButton_click (view: View){
        val intent = Intent(this@ContactActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val selection = intent.extras.getInt("position")

        val URL = "content://com.example.douglasdeleon.lab5.ContactsProvider"
        val contacts = Uri.parse(URL)
        val c = contentResolver.query(contacts, null, null, null, "name")

        if (c.count == 0) {
            Toast.makeText(this@ContactActivity, "No existen datos.", Toast.LENGTH_SHORT).show()
        } else {
            while (c.moveToNext()) {
                if(c.getString(c.getColumnIndex(ContactsProvider._ID)).toInt() == selection){
                    contactName.setText( c.getString(c.getColumnIndex(ContactsProvider.NAME)) )
                    contactNumber.setText( c.getString(c.getColumnIndex(ContactsProvider.NUMBER)) )
                    contactEmail.setText( c.getString(c.getColumnIndex(ContactsProvider.MAIL)) )
                }
            }
        }
        c.close()

        editButton.setOnClickListener {
            val intent = Intent(this@ContactActivity, UpdateContactActivity::class.java)
            intent.putExtra("name", contactName.text)
            intent.putExtra("number", contactNumber.text)
            intent.putExtra("mail", contactEmail.text)
            intent.putExtra("position", selection)
            startActivity(intent)
        }

        contactNumber.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactNumber.text))
            startActivity(intent)
        }
    }
}
