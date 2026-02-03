package com.example.contactsmanager

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsmanager.repository.ContactsRepository
import com.example.contactsmanager.room.Contacts
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactsRepository) : ViewModel(), Observable
{
    val contacts = repository.contacts
    private var isUpdateOrDelete = false
    private lateinit var contactToUpdateOrDelete: Contacts

    @Bindable
    var inputName = MutableLiveData<String?>()
    @Bindable
    var inputEmail = MutableLiveData<String?>()
    @Bindable
    var saveOrUpdateButtonText = MutableLiveData<String>()
    @Bindable
    var clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun insert(contacts: Contacts) = viewModelScope.launch {
        repository.insert(contacts)
    }

    fun delete(contacts: Contacts) = viewModelScope.launch {
        repository.delete(contacts)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun update(contacts: Contacts) = viewModelScope.launch {
        repository.update(contacts)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun saveOrUpdate()
    {
        if (isUpdateOrDelete)
        {
            contactToUpdateOrDelete.contact_name = inputName.value!!
            contactToUpdateOrDelete.contact_email = inputEmail.value!!
            update(contactToUpdateOrDelete)
        } else
        {
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(Contacts(0, name, email))
            inputName.value = null
            inputEmail.value = null
        }
    }

    fun clearAllOrDelete()
    {
        if (isUpdateOrDelete) delete(contactToUpdateOrDelete)
        else clearAll()
    }

    fun initUpdateAndDelete(contacts: Contacts)
    {
        inputName.value = contacts.contact_name
        inputEmail.value = contacts.contact_email
        isUpdateOrDelete = true
        contactToUpdateOrDelete = contacts
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?)
    {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?)
    {
        TODO("Not yet implemented")
    }

}