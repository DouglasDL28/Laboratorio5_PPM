package com.example.douglasdeleon.lab5.Activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.douglasdeleon.lab5.R
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {

    fun homeButton_click (view: View){
        val intent = Intent(this@ContactActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        contactNumber.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactNumber.text))
            startActivity(intent)
        }
    }
}
