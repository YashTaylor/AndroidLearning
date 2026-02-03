package com.example.contactsmanager.repository

import com.example.contactsmanager.room.ContactDAO
import com.example.contactsmanager.room.Contacts

class ContactsRepository(private val dao: ContactDAO)
{
    val contacts = dao.getAllContacts()

    suspend fun insert(contacts: Contacts): Long
    {
        return dao.insertContact(contacts)
    }

    suspend fun update(contacts: Contacts)
    {
        dao.updateContact(contacts)
    }

    suspend fun delete(contacts: Contacts)
    {
        dao.deleteContact(contacts)
    }

    suspend fun deleteAll()
    {
        dao.deleteAll()
    }

}