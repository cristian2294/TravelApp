package com.example.travelapp.login.presentation.model

data class Customer(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
) {
    fun toMap(): MutableMap<String, Any> = mutableMapOf(
        "id" to this.id,
        "name" to this.name,
        "email" to this.email,
        "password" to this.password,
    )
}
