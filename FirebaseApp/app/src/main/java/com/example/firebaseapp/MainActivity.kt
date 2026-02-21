package com.example.firebaseapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : AppCompatActivity()
{
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvOne = findViewById<TextView>(R.id.tvOne)

        database = Firebase.database.reference

        //Write to firebase database
        database.child("price").setValue("Rs 1,20,000")

        //read from firebase database
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                val gold_price =snapshot.value
                tvOne.text = gold_price.toString()
            }

            override fun onCancelled(error: DatabaseError)
            {

            }
        }
        database.child("price").addValueEventListener(postListener)
    }
}