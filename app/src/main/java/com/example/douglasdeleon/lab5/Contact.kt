package com.example.douglasdeleon.lab5

class Contact (name: String, email: String, number: Int) {
    val name: String = name
    val email: String = email
    val number: Int = number

    override fun toString(): String {
        return "$name: $number"
    }
}