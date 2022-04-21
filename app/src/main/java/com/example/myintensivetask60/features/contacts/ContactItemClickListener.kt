package com.example.myintensivetask60.features.contacts

import android.view.View
import com.example.myintensivetask60.data.Contact

interface ContactItemClickListener {
    fun onItemClick(contact: Contact)
    fun onLongPress(contact: Contact)
}