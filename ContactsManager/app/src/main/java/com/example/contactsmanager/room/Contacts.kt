package com.example.contactsmanager.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("contacts_table")
data class Contacts(
    @PrimaryKey(autoGenerate = true)
    var contact_id : Int,
    var contact_name : String,
    var contact_email : String
)
