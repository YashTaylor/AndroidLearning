package com.example.roomdatabase.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase: RoomDatabase()
{
    abstract fun itemDao(): ItemDao

    companion object
    {
        @Volatile
        private var instance: ItemDatabase? = null

        fun getInstance(context: Context): ItemDatabase
        {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ItemDatabase::class.java,
                    "itemDatabase"
                ).build()
                    .also { instance = it }
            }
        }
    }

}