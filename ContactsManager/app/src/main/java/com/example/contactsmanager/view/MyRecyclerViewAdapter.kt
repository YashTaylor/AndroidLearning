package com.example.contactsmanager.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsmanager.R
import com.example.contactsmanager.databinding.CardItemBinding
import com.example.contactsmanager.room.Contacts

class MyRecyclerViewAdapter(private val contactsList: List<Contacts>, private val onClickListener: (Contacts) -> Unit) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>()
{

    class MyViewHolder(val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(contacts: Contacts, onClickListener: (Contacts) -> Unit)
        {
            binding.txtName.text = contacts.contact_name
            binding.txtEmail.text = contacts.contact_email
            binding.listItemLayout.setOnClickListener {
                onClickListener(contacts)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        holder.bind(contactsList[position], onClickListener)
    }

    override fun getItemCount(): Int
    {
        return contactsList.size
    }
}