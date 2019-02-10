package com.example.douglasdeleon.lab5.Activities

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.douglasdeleon.lab5.ContactsProvider
import com.example.douglasdeleon.lab5.R
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.activity_update_contact.*

class UpdateContactActivity : AppCompatActivity() {

    //Regresar al MainActivity
    fun homeButton (view: View) {
        val intent = Intent(this@UpdateContactActivity, MainActivity::class.java)
        startActivity(intent)
    }

    fun updateContactButton (view: View) {
        val values= ContentValues()
        values.put(ContactsProvider.NAME, nameUpdate.text.toString())
        values.put(ContactsProvider.NUMBER, numberUpdate.text.toString())
        values.put(ContactsProvider.MAIL, mailUpdate.text.toString())
        //Update de la base de datos
        contentResolver.update(ContactsProvider.CONTENT_URI,values,(intent.extras.getInt("position").toString()),null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_contact)

        // Desplegar la información actual.
        nameUpdate.setText( intent.extras.getString("name") )
        numberUpdate.setText( intent.extras.getString("number") )
        mailUpdate.setText( intent.extras.getString("mail") )
    }
}
