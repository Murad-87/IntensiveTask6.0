package com.example.myintensivetask60.features.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myintensivetask60.data.Contact
import com.example.myintensivetask60.databinding.ContactItemBinding
import com.example.myintensivetask60.ui.ContactsFragment

class ContactAdapter(private val listener: ContactsFragment) :
    ListAdapter<Contact, ContactAdapter.ContactViewHolder>(ContactComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class ContactViewHolder(
        private val binding: ContactItemBinding,
        private val listener: ContactItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.apply {
                Glide.with(itemView)
                    .load(contact.url)
                    .into(contactItemImage)
                contactItemName.text = contact.name
                contactItemLastName.text = contact.lastName
                contactItemPhoneNumber.text = contact.phoneNumber

                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    root.setOnClickListener { listener.onItemClick(contact) }
                    root.setOnLongClickListener {
                        listener.onLongPress(contact)
                        true
                    }
                }
            }
        }
    }

    class ContactComparator : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact) =
            oldItem == newItem
    }
}