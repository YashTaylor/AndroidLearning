package com.example.roomdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.roomdatabase.databinding.ActivityMainBinding
import com.example.roomdatabase.room.ItemDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            insertItem()
        }

        displayAllRecords()
    }

    fun insertItem()
    {
        val name = binding.etName.text.toString()
        val price = binding.etPrice.text.toString().toDouble()
        val quantity = binding.etQuantity.text.toString().toInt()

        val itemDatabase = ItemDatabase.getInstance(applicationContext)
        val itemDao = itemDatabase.itemDao()

        var item = Item(0, name, price, quantity)

        CoroutineScope(Dispatchers.IO).launch {
            itemDao.insert(item)
        }
    }

    fun displayAllRecords()
    {
        val itemDatabase = ItemDatabase.getInstance(applicationContext)
        val itemDao = itemDatabase.itemDao()

        itemDao.getAllItemsDB().observe(this, Observer{
            var result = ""
            for (item in it)
            {
                result = it.joinToString("\n")
            }
            binding.tvRecords.text = result
        })
    }
}