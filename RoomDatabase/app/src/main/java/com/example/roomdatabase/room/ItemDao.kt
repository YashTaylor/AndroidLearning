package com.example.roomdatabase.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabase.Item

@Dao
interface ItemDao
{
    @Insert
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("DELETE FROM Item")
    fun deleteAllItems()

    @Query("SELECT * FROM Item ORDER BY id DESC")
    fun getAllItemsDB(): LiveData<List<Item>>
}