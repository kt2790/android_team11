package com.example.contactapp.model

data class Contact (
        val id: Int,
        val name: String,
        val phone: String,
        val email: String,
        val alarm: Int,
        val profile: String,
        var favorite: Boolean
        )


