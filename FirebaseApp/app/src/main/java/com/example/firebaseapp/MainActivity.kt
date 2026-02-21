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
import com.google.firebase.database.getValue
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity()
{
//    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvOne = findViewById<TextView>(R.id.tvOne)
        //Initialize firestore
        val db = Firebase.firestore

        /*
        //Creating a collection : Users
        val user_collection = db.collection("Users")

        //Creating documents
        val user1 = hashMapOf(
            "name" to "Yash",
            "lname" to "Tailor",
            "born" to "2002"
        )
        val user2 = hashMapOf(
            "name" to "Payal",
            "lname" to "Taylor",
            "born" to "1999"
        )

        //Adding document to collection
        user_collection.document("user1").set(user1)
        user_collection.document("user2").set(user2)

        //Receive documents from collection
        val docRef = db.collection("Users").document("user1")
        docRef.get().addOnSuccessListener {
            if (it != null)
            {
//                tvOne.text = it.data.toString() // gets the entire object or get single field like below
                tvOne.text = it.data?.get("name").toString() + " " + it.data?.get("lname").toString()
            }
        }*/

        //Getting all documents from collection
        var allDocs = ""

        db.collection("Users").get().addOnSuccessListener {
            for (document in it)
            {
                allDocs += "${document.data}"
            }
            tvOne.text = allDocs
        }

        //Updating data in document
        db.collection("Users").document("user1").update("born", 1990)

        //deleting a document
        db.collection("Users").document("user2").delete()

        /*database = Firebase.database.reference

        // Custom object read and write operations

        // writing custom object to firebase
        val user1 = User("Yash", "123")
        database.child("Users").setValue(user1)

        //reading custom object from firebase
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                val user = snapshot.getValue<User>()
                tvOne.text = user.toString()
            }

            override fun onCancelled(error: DatabaseError) { }
        }
        database.child("Users").addValueEventListener(postListener)*/


        // Basic Read and Write operations
        /*//Write to firebase database
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
        database.child("price").addValueEventListener(postListener)*/
    }
}