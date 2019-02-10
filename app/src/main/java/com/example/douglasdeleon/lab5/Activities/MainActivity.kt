package com.example.douglasdeleon.lab5.Activities

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.douglasdeleon.lab5.ContactsProvider
import com.example.douglasdeleon.lab5.R
import kotlinx.android.synthetic.main.activity_contact.*

class MainActivity : AppCompatActivity() {

    fun addContacto_Button(view: View) {
        val intent = Intent(this@MainActivity, CreateContactActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var data: ArrayList<String> = arrayListOf()

        val listView: ListView = findViewById(R.id.contactsListView)

        val URL = "content://com.example.douglasdeleon.lab5.ContactsProvider"
        val contacts = Uri.parse(URL)
        val c = contentResolver.query(contacts, null, null, null, "name")

        if (c.count == 0) {
            Toast.makeText(this@MainActivity, "No existen datos.", Toast.LENGTH_SHORT).show()
        } else {
            while (c.moveToNext()) {
                data.add(
                    "${c.getString(c.getColumnIndex(ContactsProvider.NAME))} : ${c.getString(c.getColumnIndex(ContactsProvider.NUMBER))}")
            }
        }
        c.close()// obtiene los datos de la base de datos y los almacena en una string temporal para el listview.

        val adapter = ArrayAdapter(
            this@MainActivity,
            R.layout.item,
            data
        ) //Conexión del listView y el adapter para mostrar la lista.

        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@MainActivity, ContactActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        listView.onItemLongClickListener = object : AdapterView.OnItemLongClickListener {
            override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
                val uri = contentResolver.delete(ContactsProvider.CONTENT_URI, position.toString(), null)
                listView.adapter = adapter

                if (uri == 0) {
                    Toast.makeText(this@MainActivity, "Fallo", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Exito", Toast.LENGTH_SHORT).show()
                }
                return true
            }

        }

    }
}
