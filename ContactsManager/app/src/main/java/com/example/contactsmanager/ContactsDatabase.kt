package com.example.contactsmanager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database([Contacts::class], version = 1)
abstract class ContactsDatabase : RoomDatabase()
{
    abstract val contactDAO : ContactDAO

    companion object
    {
        @Volatile
        private var INSTANCE: ContactsDatabase? = null

        fun getInstance(context: Context): ContactsDatabase
        {
            synchronized(this)
            {
                var instance = INSTANCE
                if (instance == null)
                {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactsDatabase::class.java,
                        "contacts_db"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}