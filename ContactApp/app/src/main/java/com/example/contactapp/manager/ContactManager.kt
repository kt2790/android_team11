package com.example.contactapp.manager

import com.example.contactapp.model.Contact

interface ContactManager {

    fun createContact(name: String, phone: String, email: String, alarm: Int)
    fun updateContact(id: Int, contact: Contact)
    fun getContactById(id: Int): Contact?
    fun getContactList(): List<Contact>

}