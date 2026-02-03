package com.example.contactsmanager.room

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface ContactDAO
{
    @Insert
    suspend fun insertContact(contacts: Contacts): Long

    @Update
    suspend fun updateContact(contacts: Contacts)

    @Delete
    suspend fun deleteContact(contacts: Contacts)

    @Query("SELECT * FROM contacts_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM contacts_table")
    fun getAllContacts(): LiveData<List<Contacts>>

}