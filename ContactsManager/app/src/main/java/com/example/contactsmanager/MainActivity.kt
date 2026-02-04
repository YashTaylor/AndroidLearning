package com.example.contactsmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsmanager.databinding.ActivityMainBinding
import com.example.contactsmanager.repository.ContactsRepository
import com.example.contactsmanager.room.Contacts
import com.example.contactsmanager.room.ContactsDatabase
import com.example.contactsmanager.view.MyRecyclerViewAdapter
import com.example.contactsmanager.viewmodel.ContactViewModel
import com.example.contactsmanager.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //ROOM DATABASE
        val dao = ContactsDatabase.getInstance(applicationContext).contactDAO
        val repository = ContactsRepository(dao)
        val factory = ViewModelFactory(repository)

        //ViewModel
        contactViewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java)
        binding.contactViewModel = contactViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

    }

    fun initRecyclerView()
    {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        displayContactsList()
    }

    fun displayContactsList()
    {
        contactViewModel.contacts.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it, { selectedItem: Contacts -> listItemClicked(selectedItem) })
        })
    }

    fun listItemClicked(contacts: Contacts)
    {
        contactViewModel.initUpdateAndDelete(contacts)
    }
}